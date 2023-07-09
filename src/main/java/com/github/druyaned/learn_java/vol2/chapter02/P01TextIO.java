package com.github.druyaned.learn_java.vol2.chapter02;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.vol2.Volume2;
import com.github.druyaned.learn_java.util.utf.UTF16Unit;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Part 1 of the chapter 2 to practice in some IO-streams with text.
 * 
 * @author druyaned
 * @see <a href="https://www.unicode.org/charts/">unicode.org/charts</a>
 */
public class P01TextIO {
    
    public static void run() {
        System.out.println("\n" + bold("Part 01 TextIO"));
        
        final char A = (char)0xd835, B = (char)0xdd46;
        System.out.printf("\"\\u%h\\u%h\": %c%c\n", (int)A, (int)B, A, B);
        
        // playing with UTF-16 LE encoding
        System.out.println(blueBold("Playing with UTF-16 LE encoding") + ":");
        int c = 0x1D546;
        System.out.printf("toUTF16(0x%h): %s\n", c, new UTF16Unit(c)); // 𝕆
        System.out.printf("toUTF16(0x%h): %s\n", c = 0x1f604, new UTF16Unit(c)); // 😄
        System.out.printf("toUTF16(0x%h): %s\n", c = 0x1d6d1, new UTF16Unit(c)); // 𝛑
        System.out.printf("%s: %s (ED)\n", "(toUTF16(0x1d570) + toUTF16(0x1d56F))",
                                           new UTF16Unit(0x1d570).toString() +
                                           new UTF16Unit(0x1d56F)); // 𝕰𝕯
        
        // checking charset name "UTF-16 LE"
        final String charsetName = "UTF-16LE";
        try {
            if (!Charset.isSupported(charsetName)) {
                System.out.println(redBold(charsetName + " isn't supported"));
                return;
            }
        } catch (IllegalArgumentException ex) {
            String m = redBold("Charset name \"" + charsetName + "\" is illegal");
            Logger.getLogger(P01TextIO.class.getName()).log(Level.SEVERE, m, ex);
            return;
        }
        
        // reding the file in UTF-16LE encoding
        Path filePath = Volume2.getDataDirPath().resolve("chapter02").resolve("text-utf16le.txt");
        try (FileInputStream fileIn = new FileInputStream(filePath.toFile());
             InputStreamReader inReader = new InputStreamReader(fileIn, StandardCharsets.UTF_16LE);
             BufferedReader reader = new BufferedReader(inReader)) {
            
            System.out.println(blueBold("File with UTF-16 LE encoding") + ":");
            reader.lines().forEach((line) -> System.out.println("  " + line));
            
        } catch (IOException ex) {
            Logger.getLogger(P01TextIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
