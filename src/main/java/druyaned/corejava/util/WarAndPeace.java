package druyaned.corejava.util;

import druyaned.corejava.Book;
import druyaned.corejava.Volume;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.regex.Pattern;

/**
 * Provides the file of the "War And Peace".
 * @author druyaned
 */
public class WarAndPeace {
    
    private static final String PUNCT = "[.,:;!?\"'()\\[\\]{}-]";
    private static final String REGEX1 = String.format("^%s+", PUNCT);
    private static final String REGEX2 = String.format("%s+$", PUNCT);
    private static final String REGEX3 = String.format("%s*[\\s]+%s*", PUNCT, PUNCT);
    private static final String REGEX = String.format("(%s)|(%s)|(%s)", REGEX1, REGEX2, REGEX3);
    private static final String FILE_NAME = "voyna-i-mir-1.txt";
    
    /** A pattern to split a sentence into words. */
    public static final Pattern WORD_PATTERN = Pattern.compile(REGEX);
    
    /** A pattern to split a sentence into "POSIX-character" words (<b>US-ASCII only</b>). */
    public static final Pattern WORD_PATTERN2 = Pattern.compile("\\P{L}+");
    
    public static Path textPath(Volume volume) {
        Path textPath = volume.dataDir().resolve(FILE_NAME);
        if (!Files.exists(textPath)) {
            FileUtil.createFileOnDemand(textPath);
            Book book = volume.book();
            try (InputStream fin = FileUtil.stream(book, volume, FILE_NAME)) {
                Files.copy(fin, textPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException exc) {
                throw new UncheckedIOException(exc);
            }
        }
        return textPath;
    }
    
}
