package druyaned.corejava.vol2.ch02io;

import static druyaned.ConsoleColors.blueBold;
import static druyaned.ConsoleColors.redBold;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.util.FileUtil;
import druyaned.corejava.util.utf.UTF16Unit;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class T01TextIO extends Topic {
    
    public T01TextIO(Chapter chapter) {
        super(chapter, 1);
    }
    
    @Override public String title() {
        return "Topic 01 TextIO";
    }
    
    @Override public void run() {
        // https://www.unicode.org/charts/
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
        Path filePath = dataDir().resolve("UTF-16-LE-text.txt");
        try {
            FileUtil.createFileOnDemand(filePath);
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
