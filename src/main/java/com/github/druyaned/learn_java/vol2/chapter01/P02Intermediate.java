package com.github.druyaned.learn_java.vol2.chapter01;

import static com.github.druyaned.ConsoleColors.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import static com.github.druyaned.ConsoleColors.bold;

/**
 * Part 2 of the chapter 1 to practice with an intermediate operations.
 * 
 * @author druyaned
 */
public class P02Intermediate {
    /*
    Intermediate operations:
        - methods: filter, map, flatMap
        - methods: skip (remove 1st elements; opposite to limit), concat
        - methods: distinct (without repetitions), sorted, peek (invokes at the time of extraction)
    */
    
    public static void run() {
        System.out.println("\n" + bold("Part 02 Intermediate"));
        
        // skip, limit, map, flatMap
        
        // necessary declarations
        final int N = 4; // amount of streams
        final List<Stream.Builder<String>> wordsBuilders = new ArrayList<>(N);
        for (int i = 0; i < N; ++i)
            wordsBuilders.add(Stream.<String>builder());
        
        // making stream builders
        Path textPath = Text.getTextPath();
        try (Stream<String> lines = Files.lines(textPath)) {
            lines.forEach((line) -> {
                Text.WORD_PATTERN.splitAsStream(line)
                        .filter((word) -> !word.isEmpty())
                        .forEachOrdered((word) -> wordsBuilders.forEach((wb) -> wb.add(word)));
            });
        } catch (IOException ex) {
            Logger.getLogger(P02Intermediate.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        // making word streams
        List<Stream<String>> wordsStreams = new ArrayList<>(N);
        for (int i = 0; i < N; ++i)
            wordsStreams.add(wordsBuilders.get(i).build());
        
        // limit, skip
        final int SKIP_COUNT = 4, NEXT_COUNT = 32;
        System.out.print(blueBold("First " + SKIP_COUNT + " words") + ":");
        wordsStreams.get(0).limit(SKIP_COUNT)
                .forEachOrdered((word) -> System.out.print(" [" + word + "]"));
        System.out.print("\n" + blueBold("Next " + NEXT_COUNT + " words") + ":");
        wordsStreams.get(1).skip(SKIP_COUNT).limit(NEXT_COUNT)
                .forEachOrdered((word) -> System.out.print(" [" + word + "]"));
        
        // flatMap, map
        System.out.print("\n" + blueBold("First " + SKIP_COUNT + " letters") + ":");
        wordsStreams.get(2)
                .flatMap((word) -> makeLetters(word))
                .limit(SKIP_COUNT)
                .forEachOrdered((letter) -> System.out.print(" [" + letter + "]"));
        System.out.print("\n" + blueBold("Letters of the 2nd word") + ":");
        wordsStreams.get(3)
                .skip(1)
                .limit(1)
                .map((word) -> makeLetters(word))
                .forEachOrdered((letterStream) -> letterStream.forEachOrdered((letter) ->
                        System.out.print(" [" + letter + "]")));
        
        // sorted, distinct, peek, toArray(generator)
        System.out.print("\n" + blueBold("Sorted distinct stream") + ":");
        Stream.of("bcd", "abc", "cbd", "bcd")
                .sorted(Comparator.reverseOrder())
                .distinct() // without repetitions
                .forEachOrdered((s) -> System.out.print(" " + s));
        System.out.print("\n" + purpleBold("Conversion [stream -> array]") + "; ");
        System.out.print(blueBold("peaked") + ":");
        String[] stringArray = Stream.of("bcd", "abc", "cbd", "bcd")
                .peek((s) -> System.out.print(" [" + s + "]")) // do smth at the peek moment
                .toArray(String[]::new);
        System.out.print("; " + blueBold("Array") + ": ");
        System.out.println(Arrays.toString(stringArray));
    }
    
    public static Stream<String> makeLetters(String word) {
        List<String> letters = new ArrayList<>();
        for (int i = 0; i < word.length(); ++i)
            letters.add(word.substring(i, i + 1));
        return letters.stream();
    }
}
