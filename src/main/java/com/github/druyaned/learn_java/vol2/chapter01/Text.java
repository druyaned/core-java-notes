package com.github.druyaned.learn_java.vol2.chapter01;

import com.github.druyaned.learn_java.vol2.Volume2;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.regex.Pattern;

/**
 * Provides the file of the "War And Peace" which is located in the Google Docs.
 * 
 * @author druyaned
 */
public class Text {
    private static final String PUNCT = "[.,:;!?\"'()\\[\\]{}-]";
    private static final String REGEX1 = String.format("^%s+", PUNCT);
    private static final String REGEX2 = String.format("%s+$", PUNCT);
    private static final String REGEX3 = String.format("%s*[\\s]+%s*", PUNCT, PUNCT);
    private static final String REGEX = String.format("(%s)|(%s)|(%s)", REGEX1, REGEX2, REGEX3);
    
    /** A pattern to split a sentence into words. */
    public static final Pattern WORD_PATTERN = Pattern.compile(REGEX);
    
    /** A pattern to split a sentence into "POSIX-character" words (<b>US-ASCII only</b>). */
    public static final Pattern WORD_PATTERN2 = Pattern.compile("\\P{L}+");
    
    private static final Path TEXT_PATH = Volume2.getDataDirPath()
            .resolve("chapter01").resolve("voyna-i-mir-1.txt");
    
    /**
     * Returns the path to the file (TXT format) of the "War And Peace".
     * 
     * @return the path to the file (TXT format) of the "War And Peace".
     */
    public static final Path getTextPath() {
        if (!Files.exists(TEXT_PATH)) {
            try {
                Path dirPath = TEXT_PATH.getParent();
                if (!Files.exists(dirPath)) {
                    Files.createDirectories(dirPath);
                }
                Files.createFile(TEXT_PATH);
                String fileName = "/vol2/chapter01/voyna-i-mir-1.txt";
                InputStream inputStream = Text.class.getResourceAsStream(fileName);
                Files.copy(inputStream, TEXT_PATH, StandardCopyOption.REPLACE_EXISTING);
                System.out.println(TEXT_PATH + " has been written.");
            } catch (IOException exc) {
                throw new UncheckedIOException(exc);
            }
        }
        return TEXT_PATH;
    }

}
