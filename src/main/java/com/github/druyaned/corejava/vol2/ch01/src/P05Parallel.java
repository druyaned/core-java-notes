package com.github.druyaned.corejava.vol2.ch01.src;

import com.github.druyaned.corejava.util.Stopwatch;
import static com.github.druyaned.ConsoleColors.*;
import static com.github.druyaned.corejava.vol2.ch01.src.Text.WORD_PATTERN;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Part 5 of the chapter 1 to practice with {@code parallel streams}.
 * @author druyaned
 */
public class P05Parallel implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Part 05 Parallel"));
        // changing the List
        String[] stringArray = { "ab", "bc", "cd", "de", "ef" };
        ArrayList<String> list = new ArrayList<>(Arrays.asList(stringArray));
        Stream<String> listStream = list.stream();
        list.add("fg");
        System.out.print(blueBold("listStream after adding") + ":");
        listStream.forEachOrdered((s) -> System.out.print(" " + s));
        // changing the Array
        stringArray = new String[] { "ab", "bc", "cd", "de", "ef", null };
        Stream<String> arrayStream = Stream.of(stringArray);
        stringArray[5] = "fg";
        System.out.print("\n" + blueBold("arrayStream after changing") + ":");
        arrayStream.forEachOrdered((s) -> System.out.print(" " + s));
        System.out.println();
        // creating streamsOfWords and listOfWords
        Path textPath = Text.TEXT_PATH;
        final int STREAMS_N = 2;
        ArrayList<Stream<String>> streamsOfWords = new ArrayList<>(STREAMS_N);
        final ArrayList<String> listOfWords;
        Runnable closeAllStreams = () -> streamsOfWords.forEach((stream) -> {
            if (stream != null) {
                stream.close();
            }
        });
        try {
            for (int i = 0; i < STREAMS_N; ++i) {
                Stream<String> textStream = Files.lines(textPath);
                Stream<String> streamOfWords = textStream
                        .flatMap(WORD_PATTERN::splitAsStream)
                        .filter((word) -> !word.isEmpty());
                streamsOfWords.add(streamOfWords);
            }
            Stream<String> textStream = Files.lines(textPath);
            Stream<String> streamOfWords = textStream
                    .flatMap(WORD_PATTERN::splitAsStream)
                    .filter((word) -> !word.isEmpty());
            listOfWords = new ArrayList<>(
                    Arrays.asList(streamOfWords.toArray(String[]::new))
            );
            System.out.println(bold("In " + textPath.getName(textPath.getNameCount() - 1)));
            Map<String, Integer> mapOfWords;
            // non-parallel-stream
            Stopwatch stopwatch = new Stopwatch().start();
            mapOfWords = streamsOfWords.get(0)
                    .collect(Collectors.toMap(
                            word -> word.toLowerCase(),
                            word -> 1,
                            (Integer count1, Integer count2) -> count1 + count2,
                            HashMap::new)
                    );
            stopwatch.stop();
            String wordToCount = "я";
            System.out.println(
                    blueBold("[non-parallel-stream]") +
                    " count of \"" + blueBold(wordToCount) + "\": " +
                    mapOfWords.get(wordToCount) +
                    "; spentTime=" + stopwatch.getSpent().toMillis() + "(millis)"
            );
            // ____parallel-stream
            stopwatch.start();
            mapOfWords = streamsOfWords.get(1)
                    .parallel()
                    .collect(Collectors.toMap(
                            word -> word.toLowerCase(),
                            word -> 1,
                            (Integer count1, Integer count2) -> count1 + count2,
                            HashMap::new
                    ));
            stopwatch.stop();
            System.out.println(
                    blueBold("[____parallel-stream]") +
                    " count of \"" + blueBold(wordToCount) + "\": " +
                    mapOfWords.get(wordToCount) +
                    "; spentTime=" + stopwatch.getSpent().toMillis() + "(millis)"
            );
            // non-parallel-stream-from-array
            stopwatch.start();
            mapOfWords = listOfWords
                    .stream()
                    .collect(Collectors.toMap(
                            word -> word.toLowerCase(),
                            word -> 1,
                            (Integer count1, Integer count2) -> count1 + count2,
                            HashMap::new
                    ));
            stopwatch.stop();
            System.out.println(
                    blueBold("[non-parallel-stream-from-array]") +
                    " count of \"" + blueBold(wordToCount) + "\": " +
                    mapOfWords.get(wordToCount) +
                    "; spentTime=" + stopwatch.getSpent().toMillis() + "(millis)"
            );
            // ____parallel-stream-from-array
            stopwatch.start();
            mapOfWords = listOfWords
                    .parallelStream()
                    .collect(Collectors.toMap(
                            word -> word.toLowerCase(),
                            word -> 1,
                            (Integer count1, Integer count2) -> count1 + count2,
                            HashMap::new)
                    );
            stopwatch.stop();
            System.out.println(
                    blueBold("[____parallel-stream-from-array]") +
                    " count of \"" + blueBold(wordToCount) + "\": " +
                    mapOfWords.get(wordToCount) +
                    "; spentTime=" + stopwatch.getSpent().toMillis() + "(millis)"
            );
            // non-parallel-array
            stopwatch.start();
            mapOfWords = new HashMap<>();
            for (String word : listOfWords) {
                String w = word.toLowerCase();
                if (mapOfWords.containsKey(w)) {
                    Integer count = mapOfWords.get(w);
                    mapOfWords.put(w, count + 1);
                } else {
                    mapOfWords.put(w, 1);
                }
            }
            stopwatch.stop();
            System.out.println(
                    blueBold("[non-parallel-array]") +
                    " count of \"" + blueBold(wordToCount) + "\": " +
                    mapOfWords.get(wordToCount) +
                    "; spentTime=" + stopwatch.getSpent().toMillis() + "(millis)"
            );
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        } finally {
            closeAllStreams.run();
        }
    }
    
}
