package com.github.druyaned.corejava.vol2.ch03.src;

import static com.github.druyaned.ConsoleColors.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Part 1 of the chapter 3 to practice with a DTD file.
 * @author druyaned
 */
public class P01WriteDTD {
    
    public static void run(Path dtdPath) throws IOException {
        System.out.println("\n" + bold("Running P01 WriteDTD"));
        String text = """
                      <!ELEMENT activeTimes (item+)>
                      <!ELEMENT item (start, stop, mode, descr)>
                      <!ELEMENT start (year, month, day, hour, minute, second)>
                      <!ELEMENT stop (year, month, day, hour, minute, second)>
                      <!ELEMENT year (#PCDATA)>
                      <!ELEMENT month (#PCDATA)>
                      <!ELEMENT day (#PCDATA)>
                      <!ELEMENT hour (#PCDATA)>
                      <!ELEMENT minute (#PCDATA)>
                      <!ELEMENT second (#PCDATA)>
                      <!ELEMENT mode (#PCDATA)>
                      <!ELEMENT descr (#PCDATA)>
                      """;
        Files.writeString(dtdPath, text, StandardOpenOption.WRITE);
    }
    
}
