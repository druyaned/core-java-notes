package druyaned.corejava.vol2.ch01.src;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    
    /** A pattern to split a sentence into words. */
    public static final Pattern WORD_PATTERN = Pattern.compile(REGEX);
    
    /** A pattern to split a sentence into "POSIX-character" words (<b>US-ASCII only</b>). */
    public static final Pattern WORD_PATTERN2 = Pattern.compile("\\P{L}+");
    
    /** The path of the txt-file of the "War And Peace". */
    public static final Path TEXT_PATH;
    
    static {
        try {
            TEXT_PATH = Paths.get(WarAndPeace.class
                    .getResource("/vol2/ch01/voyna-i-mir-1.txt").toURI());
        } catch (URISyntaxException exc) {
            throw new RuntimeException(exc);
        }
    }
    
}
