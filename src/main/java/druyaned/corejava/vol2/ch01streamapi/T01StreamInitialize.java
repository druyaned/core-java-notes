package druyaned.corejava.vol2.ch01streamapi;

import druyaned.corejava.util.WarAndPeace;
import static druyaned.ConsoleColors.blueBold;
import static druyaned.ConsoleColors.bold;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TWO;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

public class T01StreamInitialize extends Topic {
    
    private final Path textPath;
    
    public T01StreamInitialize(Chapter chapter, Path textPath) {
        super(chapter, 1);
        this.textPath = textPath;
    }
    
    @Override public String title() {
        return "Toopic 01 StreamInitialize";
    }
    
    @Override public void run() {
        // declaration of streams
        Stream<String> lineStream;
        Stream<String> wordStream;
        Stream<String> stringStream;
        Stream<Integer> intStream;
        Stream<BigInteger> bigIntStream;
        ArrayList<Stream> streams = new ArrayList<>();
        // closeAllStreams action
        Runnable closeAllStreams = () -> streams.forEach(Stream::close);
        // making streams
        try {
            // Files.lines
            streams.add(lineStream = Files.lines(textPath));
            // Stream.Builder
            Stream.Builder<String> streamBuilder;
            final String WORD_REGEX = "\\PL+";
            try (Stream<String> lines = Files.lines(textPath)) {
                streamBuilder = Stream.<String>builder();
                lines.forEach((line) -> {
                    for (String word : line.split(WORD_REGEX))
                        streamBuilder.add(word);
                });
            }
            streams.add(wordStream = streamBuilder.build());
            // Pattern.splitAsStream
            String string = "This is not the best but very good string.";
            streams.add(stringStream = WarAndPeace.WORD_PATTERN.splitAsStream(string));
            // Stream.generate
            final int INT_BOUND = 10;
            Random randomGenerator = new Random();
            streams.add(intStream = Stream.generate(() ->
                    randomGenerator.nextInt(INT_BOUND)));
            // Stream.iterate
            streams.add(bigIntStream = Stream.iterate(ONE, (bigNum) -> bigNum.add(ONE)));
        } catch (IOException ex) {
            ex.printStackTrace();
            closeAllStreams.run();
            return;
        }
        // necessary declarations
        final int BIG_LINE_LENGTH = 64;
        final int BIG_WORD_LENGTH = 8;
        final int STEP_LIM = 16;
        // display stream results
        try {
            // Files.lines
            long bigLineCount = lineStream
                    .filter((line) -> line.length() >= BIG_LINE_LENGTH)
                    .count();
            System.out.println(blueBold("Files.lines")
                    + ": bigLineCount=" + bigLineCount);
            // Stream.Builder
            long bigWordCount = wordStream
                    .filter((word) -> word.length() >= BIG_WORD_LENGTH)
                    .count();
            System.out.println(blueBold("Stream.Builder")
                    + ": bigWordCount=" + bigWordCount);
            // Pattern.splitAsStream
            long wordCount = stringStream.count();
            System.out.println(blueBold("Pattern.splitAsStream")
                    + ": wordCount=" + wordCount);
            // Stream.generate
            System.out.print(blueBold("Stream.generate") + ":");
            intStream.limit(STEP_LIM).forEach((number) -> System.out.print(" " + number));
            System.out.println();
            // Stream.iterate
            System.out.println(blueBold("Stream.iterate") + ":");
            bigIntStream.limit(STEP_LIM).forEachOrdered((bigInt) ->
                    System.out.println("  " + bold("factorial(" + bigInt + ")")
                            + "=" + factorial(bigInt).toString()));
        } finally {
            closeAllStreams.run();
        }
    }
    
    public static BigInteger factorial(BigInteger number) {
        BigInteger result = ONE;
        for (
                BigInteger factor = TWO;
                factor.compareTo(number) <= 0;
                factor = factor.add(ONE)
        ) {
            result = result.multiply(factor);
        }
        return result;
    }
    
}
