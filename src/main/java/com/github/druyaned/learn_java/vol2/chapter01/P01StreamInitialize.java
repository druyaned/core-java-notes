package com.github.druyaned.learn_java.vol2.chapter01;

import static com.github.druyaned.ConsoleColors.*;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TWO;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Part 1 of the chapter 1 to practice in some stream creations.
 * 
 * @author druyaned
 */
public class P01StreamInitialize {
    
    public static void run() {
        System.out.println("\n" + bold("Part 01 StreamInitialize"));
        
        // initializing streams
        Stream<String> lineStream;
        Stream<String> wordStream;
        Stream<String> stringStream;
        Stream<Integer> intStream;
        Stream<BigInteger> bigIntStream;
        
        // make closeAllStreams action
        ArrayList<Stream> streams = new ArrayList<>();
        Runnable closeAllStreams = () -> streams.forEach((stream) -> {
            if (stream != null)
                stream.close();
        });
        
        // making streams
        Path textPath = Text.getTextPath();
        try {
            
            // Files.lines
            streams.add(lineStream = Files.lines(textPath));
            
            // Stream.of
            Stream.Builder<String> streamBuilder;
            final String WORD_REGEX = "\\PL+";
            try (Stream<String> lines = Files.lines(textPath)) {
                streamBuilder = Stream.<String>builder();
                lines.forEach((line) -> {
                    for (String word : line.split(WORD_REGEX)) {
                        streamBuilder.add(word);
                    }
                });
            }
            streams.add(wordStream = streamBuilder.build());
            
            // Pattern.splitAsStream
            String string = "This is not the best but very good string.";
            streams.add(stringStream = Text.WORD_PATTERN.splitAsStream(string));
            
            // Stream.generate
            final int INT_BOUND = 10;
            Random randomGenerator = new Random();
            streams.add(intStream = Stream.generate(() -> randomGenerator.nextInt(INT_BOUND)));
            
            // Stream.iterate
            streams.add(bigIntStream = Stream.iterate(ONE, (bigNum) -> bigNum.add(ONE)));
            
        } catch (IOException ex) {
            Logger.getLogger(P01StreamInitialize.class.getName()).log(Level.SEVERE, null, ex);
            closeAllStreams.run();
            return;
        }
        
        // necessary declarations
        final int BIG_LINE_LENGTH = 64;
        final int BIG_WORD_LENGTH = 8;
        final int STEP_LIM = 16;
        String m;
        
        // display stream results
        try {
            
            // Files.lines
            long bigLineCount = lineStream
                    .filter((line) -> line.length() >= BIG_LINE_LENGTH)
                    .count();
            m = blueBold("Files.lines") + ": bigLineCount=" + bigLineCount;
            System.out.println(m);
            
            // Stream.of
            long bigWordCount = wordStream
                    .filter((word) -> word.length() >= BIG_WORD_LENGTH)
                    .count();
            m = blueBold("Stream.of") + ": bigWordCount=" + bigWordCount;
            System.out.println(m);
            
            // Pattern.splitAsStream
            long wordCount = stringStream
                    .count();
            m = blueBold("Pattern.splitAsStream") + ": wordCount=" + wordCount;
            System.out.println(m);
            
            // Stream.generate
            m = blueBold("Stream.generate") + ":";
            System.out.print(m);
            intStream.limit(STEP_LIM).forEach((number) -> System.out.print(" " + number));
            System.out.println();
            
            // Stream.iterate
            System.out.println(blueBold("Stream.iterate") + ":");
            bigIntStream.limit(STEP_LIM).forEachOrdered((bigInt) ->
                    System.out.println("  " + bold("factorial(" + bigInt + ")") +
                                       "=" + factorial(bigInt).toString()));
            
        } finally {
            closeAllStreams.run();
        }
    }
    
    public static BigInteger factorial(BigInteger number) {
        BigInteger result = ONE;
        for (BigInteger factor = TWO; factor.compareTo(number) < 0; factor = factor.add(ONE)) {
            result = result.multiply(factor);
        }

        return result;
    }

}
