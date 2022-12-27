package com.github.druyaned.learn_java.util.walk;

import com.github.druyaned.learn_java.util.Strings;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.stream.LongStream;

/**
 * Provides a report for the {@link Walker#match matching}.
 * <p> Additional to {@link Report report's} states:
 * <ol>
 *   <li>indexesOfMatchedLines;</li>
 *   <li>matcher.</li>
 * </ol>
 * 
 * @author druyaned
 * @see Walker#match
 */
public class MatchReport extends Report {
    
    private final LongStream indexesOfMatchedLines;
    private final Consumer<Strings> matcher;
    
    /**
     * Constructs a report for the {@link Walker#match matching}.
     * 
     * @param filePath the file to walk through.
     * @param patterns line patterns to match a text-blocks.
     * @param matchCount the amount of matches with the {@code patterns}.
     * @param makeTime the time of making the report.
     * @param indexesOfMatchedLines the indexes (<u>starting from 0</u>) of matched lines.
     * @param matcher a consumer of the {@code matched lines},
     *        <u>can be a null</u> then nothing happens with {@code matched lines}.
     */
    public MatchReport(Path filePath,
                       Strings patterns,
                       long matchCount,
                       LocalDateTime makeTime,
                       LongStream indexesOfMatchedLines,
                       Consumer<Strings> matcher) {
        
        super(filePath, patterns, matchCount, makeTime);
        this.indexesOfMatchedLines = indexesOfMatchedLines;
        this.matcher = matcher;
    }
    
//-Getters------------------------------------------------------------------------------------------
    
    /**
     * The indexes (<u>starting from 0</u>) of matched lines.
     * 
     * @return the indexes (<u>starting from 0</u>) of matched lines.
     */
    public LongStream getIndexesOfMatchedLines() { return indexesOfMatchedLines; }
    
    /**
     * The consumer of the {@code matched lines},
     * <u>can be a null</u> then nothing happens with {@code matched lines}.
     * 
     * @return the consumer of the {@code matched lines},
     *         <u>can be a null</u> then nothing happens with {@code matched lines}.
     */
    public Consumer<Strings> getMatcher() { return matcher; }
//-Methods------------------------------------------------------------------------------------------
    
    @Override
    public String toString() {
        return "[filePath=" + getFilePath() +
               ", patterns=" + getPatterns() +
               ", matchCount=" + getMatchCount() +
               ", makeTime=" + getMakeTime() +
               ", matchedLines=" + indexesOfMatchedLines +
               ", matcher=" + matcher + "]";
    }
}
