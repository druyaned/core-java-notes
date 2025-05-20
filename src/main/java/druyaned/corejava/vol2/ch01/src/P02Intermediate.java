package druyaned.corejava.vol2.ch01.src;

import static druyaned.ConsoleColors.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import static druyaned.ConsoleColors.bold;

/**
 * Part 2 of the chapter 1 to practice with an intermediate operations.
 * <P><i>Intermediate operations</i>:<ul>
 *  <li>methods: filter, map, flatMap;</li>
 *  <li>methods: skip (remove 1st elements; opposite to limit), concat;</li>
 *  <li>methods: distinct (without repetitions), sorted,
 *      peek (invokes at the time of extraction).</li>
 * </ul>
 * 
 * @author druyaned
 */
public class P02Intermediate implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Part 02 Intermediate"));
        // skip, limit, map, flatMap
        // necessary declarations
        final int N = 4; // amount of streams
        final List<Stream.Builder<String>> wordsBuilders = new ArrayList<>(N);
        for (int i = 0; i < N; ++i)
            wordsBuilders.add(Stream.<String>builder());
        // making stream builders
        Path textPath = WarAndPeace.TEXT_PATH;
        try (Stream<String> lines = Files.lines(textPath)) {
            lines.forEach((line) -> {
                WarAndPeace.WORD_PATTERN.splitAsStream(line)
                        .filter((word) -> !word.isEmpty())
                        .forEachOrdered((word) -> wordsBuilders.forEach((wb) -> wb.add(word)));
            });
        } catch (IOException exc) {
            exc.printStackTrace();
            return;
        }
        // making word streams
        List<Stream<String>> wordsStreams = new ArrayList<>(N);
        for (int i = 0; i < N; ++i)
            wordsStreams.add(wordsBuilders.get(i).build());
        // limit, skip
        final int SKIP_COUNT = 28, NEXT_COUNT = 48;
        System.out.print(blueBold("First " + SKIP_COUNT + " words") + ":");
        wordsStreams.get(0)
                .limit(SKIP_COUNT)
                .forEachOrdered((word) -> System.out.print(" [" + word + "]"));
        System.out.print("\n" + blueBold("Next " + NEXT_COUNT + " words") + ":");
        wordsStreams.get(1)
                .skip(SKIP_COUNT)
                .limit(NEXT_COUNT)
                .forEachOrdered((word) -> System.out.print(" [" + word + "]"));
        // flatMap, map
        System.out.print("\n" + blueBold("First " + SKIP_COUNT + " letters") + ":");
        wordsStreams.get(2)
                .flatMap((word) -> makeLettersAsStream(word))
                .limit(SKIP_COUNT)
                .forEachOrdered((letter) -> System.out.print(" [" + letter + "]"));
        System.out.print("\n" + blueBold("Letters of the 2nd word") + ":");
        wordsStreams.get(3)
                .skip(1)
                .limit(1)
                .map((word) -> makeLettersAsStream(word))
                .forEachOrdered((letterStream) ->
                        letterStream.forEachOrdered((letter) ->
                                System.out.print(" [" + letter + "]")));
        // sorted, distinct, peek, toArray(generator)
        System.out.print("\n" + blueBold("Sorted distinct stream") + ":");
        Stream.of("bcd", "abc", "cbd", "bcd")
                .sorted(Comparator.reverseOrder())
                .distinct() // without repetitions
                .forEachOrdered((s) -> System.out.print(" " + s));
        System.out.print("\n" + purpleBold("Conversion [stream -> array]") + "; ");
        System.out.print(blueBold("peeked") + ":");
        String[] stringArray = Stream.of("bcd", "abc", "cbd", "bcd")
                .peek((s) -> System.out.print(" [" + s + "]")) // do smth at the peek moment
                .toArray(String[]::new);
        System.out.print("; " + blueBold("Array") + ": ");
        System.out.println(Arrays.toString(stringArray));
    }
    
    public static Stream<Character> makeLettersAsStream(String word) {
        Stream.Builder<Character> builder = Stream.<Character>builder();
        for (char ch : word.toCharArray())
            builder.add(ch);
        return builder.build();
    }
    
}
