package com.github.druyaned.learn_java.util.walk;

import com.github.druyaned.learn_java.vol2.Volume2;
import com.github.druyaned.learn_java.util.Strings;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.LongStream;

/**
 * Provides a walk through all strings of a file.
 * 
 * @author druyaned
 */
public class Walker {
    
    /** A regular expression for a file-name. */
    public static final String FILE_NAME_REGEX = "(.+)(\\.[^.]*)$";
    
    /** Maximum amount of threads for calculating. */
    public static final int MAX_THREAD_COUNT = 512;
    
    /** Minimum amount of threads for calculating. */
    public static final int MIN_THREAD_COUNT = 2;
    
    // is vertical whitespace
    private static boolean isVW(char ch) {
        return switch (ch) {
            case '\n' -> true;
            case '\u000B' -> true;
            case '\f' -> true;
            case '\r' -> true;
            case '\u0085' -> true;
            case '\u2028' -> true;
            case '\u2029' -> true;
            default -> false;
        };
    }
    
//-Non-static---------------------------------------------------------------------------------------
    
    private final int threadCount;
    
    /** Constructs a new walker with a 16 (default) threads for calculating. */
    public Walker() { threadCount = 16; }
    
    /**
     * Constructs a new walker with a specified thread count for calculating.
     * 
     * @param threadCount specified thread count for calculating.
     * @see #MAX_THREAD_COUNT
     * @see #MIN_THREAD_COUNT
     */
    public Walker(int threadCount) {
        if (threadCount < MIN_THREAD_COUNT || threadCount > MAX_THREAD_COUNT) {
            String message = "threadCount=" + threadCount + " which must be in [" +
                             MIN_THREAD_COUNT + ", " + MAX_THREAD_COUNT + "]";
            throw new IllegalArgumentException(message);
        }
        this.threadCount = threadCount;
    }
    
//-Getters------------------------------------------------------------------------------------------
    
    /**
     * Thread count for calculating.
     * 
     * @return thread count for calculating.
     */
    public int getThreadCount() { return threadCount; }
    
//-Methods------------------------------------------------------------------------------------------
    
    /**
     * Replaces all lines that match the {@code patterns}.
     * Always creates a copy of the file for a safe replacement
     * and always rewrites the file.
     * <p><u><b>WARNING</b></u>: each {@code pattern} from the {@code paterns}
     * must have a "<i>new line</i>" character or other <i>vertical whitespace</i> at the end;
     * also each {@code matched line} of the {@code replacer}
     * has a "<i>new line</i>" character or other <i>vertical whitespace</i> at the end.
     * 
     * @param filePath the file to walk through.
     * @param relativePath the relative path of the {@code filePath}
     *        to make safe replacement with a copy
     *        (<u>e.g.</u> a class {@code pack1.pack2.TheClass1}
     *        should have a relative path {@code pack1/pack2/TheClass1.java}).
     * @param patterns line patterns to match a text-blocks.
     * @param replacer the function to replace the {@code matched lines}
     *        that are an <i>argument</i> with the
     *        {@code lines to replace} that are a <i>returning</i>;
     *        <u>can be a null</u> then the {@code matched lines} will be removed.
     * @return the report of the replacement.
     * @throws IOException if an I/O error occurs.
     * @see Strings
     * @see Pattern
     */
    public ReplaceReport replace(Path filePath,
                                 Path relativePath,
                                 Strings patterns,
                                 Function<Strings, Strings> replacer) throws IOException {
        
        // creating a copy
        Path copyPath = pathOfCopy(relativePath);
        Path copyParent = copyPath.getParent();
        if (!Files.exists(copyParent)) {
            Files.createDirectories(copyParent);
        }
        if (!Files.exists(copyPath)) {
            Files.createFile(copyPath);
        }
        Files.copy(filePath, copyPath, StandardCopyOption.REPLACE_EXISTING); // mv file copy
        // now the copy and the file are swapped
        
        final int PATTERNS_N = patterns.size();
        final MatchTask[] tasks = new MatchTask[threadCount];
        final Thread[] threads = new Thread[threadCount];
        long matchCount = 0;
        
        try (BufferedReader reader = Files.newBufferedReader(copyPath);
             BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            
            final int TO_READ_COUNT = PATTERNS_N + threadCount - 1;
            final String[] lines = new String[TO_READ_COUNT];
            
            for (int i = 0; i < threadCount; ++i) {
                threads[i] = new Thread(tasks[i] = new MatchTask(i, lines, patterns));
                threads[i].start();
            }
            
            while (reader.ready()) {
                int readCount = 0; // reading a pack of lines
                while (readCount < TO_READ_COUNT && reader.ready()) {
                    StringBuilder builder = new StringBuilder(1024); // usually line.length < 1024
                    char ch;
                    while (reader.ready()) { // reading line till a vertical whitespace
                        if (isVW(ch = (char)reader.read())) {
                            builder.append(ch);
                            break;
                        } else {
                            builder.append(ch);
                        }
                    }
                    if (!builder.isEmpty()) {
                        lines[readCount++] = builder.toString();
                    }
                }
                final int TASKS_TO_USE = readCount - patterns.size() + 1;
                for (int i = 0; i < TASKS_TO_USE; ++i) {
                    tasks[i].unsetPaused(); // runs threaded calculating
                }
                int i = 0;
                while (i < TASKS_TO_USE) {
                    while (!tasks[i].isPaused()) {
                        Thread.sleep(0, MatchTask.DELAY); // untill calculation end
                    }
                    if (tasks[i].isMatched()) { // replacement
                        if (replacer != null) {
                            Strings linesToReplace = replacer.apply(tasks[i].getMatchedLines());
                            for (int j = 0; j < linesToReplace.size(); ++j) {
                                writer.write(linesToReplace.get(j));
                            }
                        } // else the lines are removed
                        i += PATTERNS_N; // i change: skip matched lines
                        ++matchCount;
                    } else { // writing unmatched line
                        writer.write(lines[i++]); // i change
                    }
                }
                while (i < readCount) { // remaining lines
                    writer.write(lines[i++]);
                }
            }
        } catch (InterruptedException exc) {
            throw new RuntimeException(exc);
        } finally {
            for (int i = 0; i < threadCount; ++i) {
                if (tasks[i] != null) {
                    tasks[i].setStopped(); // stop all threads
                }
            }
        }
        
        return new ReplaceReport(filePath, patterns, matchCount, LocalDateTime.now(),
                relativePath, copyPath, replacer);
    }
    
    /**
     * Matches all lines by the {@code patterns}.
     * <p><u><b>WARNING</b></u>: each {@code pattern} from the {@code paterns}
     * must have a "<i>new line</i>" character or other <i>vertical whitespace</i> at the end;
     * also each {@code matched line} of the {@code matcher}
     * has a "<i>new line</i>" character or other <i>vertical whitespace</i> at the end.
     * 
     * @param filePath the file to walk through.
     * @param patterns line patterns to match a text-blocks.
     * @param matcher a consumer of the {@code matched lines},
     *        <u>can be a null</u> then nothing happens with {@code matched lines}.
     * @return the report of the matching.
     * @throws IOException if an I/O error occurs.
     * @see Strings
     */
    public MatchReport match(Path filePath,
                             Strings patterns,
                             Consumer<Strings> matcher) throws IOException {
        
        final int PATTERNS_N = patterns.size();
        final MatchTask[] tasks = new MatchTask[threadCount];
        final Thread[] threads = new Thread[threadCount];
        long matchCount = 0;
        long readIndex = 0;
        LongStream.Builder indexesBuilder = LongStream.builder();
        
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            
            final int TO_READ_COUNT = PATTERNS_N + threadCount - 1;
            final String[] lines = new String[TO_READ_COUNT];
            
            for (int i = 0; i < threadCount; ++i) {
                threads[i] = new Thread(tasks[i] = new MatchTask(i, lines, patterns));
                threads[i].start();
            }
            
            while (reader.ready()) {
                int readCount = 0; // reading a pack of lines
                while (readCount < TO_READ_COUNT && reader.ready()) {
                    StringBuilder builder = new StringBuilder(1024); // usually line.length < 1024
                    char ch;
                    while (reader.ready()) { // reading line till a vertical whitespace
                        if (isVW(ch = (char)reader.read())) {
                            builder.append(ch);
                            break;
                        } else {
                            builder.append(ch);
                        }
                    }
                    if (!builder.isEmpty()) {
                        lines[readCount++] = builder.toString();
                    }
                }
                final int TASKS_TO_USE = readCount - patterns.size() + 1;
                for (int i = 0; i < TASKS_TO_USE; ++i) {
                    tasks[i].unsetPaused(); // runs threaded calculating
                }
                int i = 0;
                while (i < TASKS_TO_USE) {
                    while (!tasks[i].isPaused()) {
                        Thread.sleep(0, MatchTask.DELAY); // untill calculation end
                    }
                    if (tasks[i].isMatched()) { // matching actions
                        if (matcher != null) {
                            matcher.accept(tasks[i].getMatchedLines());
                        }
                        indexesBuilder.add(readIndex + i);
                        i += PATTERNS_N; // i change: skip matched lines
                        ++matchCount;
                    } else {
                        ++i; // i change
                    }
                }
                readIndex += readCount;
            }
        } catch (InterruptedException exc) {
            throw new RuntimeException(exc);
        } finally {
            for (int i = 0; i < threadCount; ++i) {
                if (tasks[i] != null) {
                    tasks[i].setStopped(); // stop all threads
                }
            }
        }
        
        LocalDateTime makeTime = LocalDateTime.now();
        return new MatchReport(filePath, patterns, matchCount, makeTime,
                indexesBuilder.build(), matcher);
    }
    
//-Private-methods----------------------------------------------------------------------------------
    
    // must be a relative path
    private static Path pathOfCopy(Path relativePath) throws IOException {
        return Volume2.getDataDirPath().resolve("util").resolve("walk-copies").resolve(relativePath);
    }
}
