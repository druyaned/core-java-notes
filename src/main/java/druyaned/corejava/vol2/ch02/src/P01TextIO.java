package druyaned.corejava.vol2.ch02.src;

import static druyaned.ConsoleColors.*;
import druyaned.corejava.util.utf.UTF16Unit;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Part 1 of the chapter 2 to practice in some IO-streams with text.
 * @author druyaned
 * @see <a href="https://www.unicode.org/charts/">unicode.org/charts</a>
 */
public class P01TextIO implements Runnable {
    
    private final Path dataDir;
    
    public P01TextIO(Path dataDir) {
        this.dataDir = dataDir;
    }
    
    @Override public void run() {
        System.out.println("\n" + bold("Part 01 TextIO"));
        final char A = (char)0xd835, B = (char)0xdd46;
        System.out.printf("\"\\u%h\\u%h\": %c%c\n", (int)A, (int)B, A, B);
        // playing with UTF-16 LE encoding
        System.out.println(blueBold("Playing with UTF-16 LE encoding") + ":");
        int c = 0x1D546;
        UTF16Unit unit = new UTF16Unit(c);
        System.out.printf("toUTF16(0x%h): %s %s\n", c, unit.asString(), unit); // 𝕆
        unit = new UTF16Unit(c = 0x1f604);
        System.out.printf("toUTF16(0x%h): %s %s\n", c, unit.asString(), unit); // 😄
        unit = new UTF16Unit(c = 0x1d6d1);
        System.out.printf("toUTF16(0x%h): %s %s\n", c, unit.asString(), unit); // 𝛑
        UTF16Unit eUnit = new UTF16Unit(0x1d570);
        UTF16Unit dUnit = new UTF16Unit(0x1d56F);
        System.out.printf("(toUTF16(0x1d570) + toUTF16(0x1d56F)): ED=%s %s %s\n",
                eUnit.asString() + dUnit.asString(), eUnit, dUnit); // 𝕰𝕯
        // checking charset name "UTF-16 LE"
        Charset utf16Le = StandardCharsets.UTF_16LE;
        if (!Charset.isSupported(utf16Le.displayName())) {
            System.out.println(redBold(utf16Le.displayName() + " isn't supported"));
            return;
        }
        // reding the file in UTF-16LE encoding
        Path filePath = dataDir.resolve("UTF-16-LE-text.txt");
        try {
            if (!Files.exists(filePath)) {
                System.out.println("Creating " + blueBold(filePath.toString()) + "...");
                Files.createFile(filePath);
            }
            String text =
            """
            Some text that should be written in the UTF-16-LE encoding.
            That's the second line of the text.
            Also there is the third line.
            """;
            System.out.println("Writing " + blueBold(filePath.toString()) + "...");
            Files.writeString(filePath, text, utf16Le);
            System.out.println("Reading " + blueBold(filePath.toString()) + "...");
            try (Stream<String> streamOfLines = Files.lines(filePath, utf16Le)) {
                System.out.println("Read lines:\n\"\"\"");
                streamOfLines.forEachOrdered(System.out::println);
                System.out.println("\"\"\"");
            }
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
}
