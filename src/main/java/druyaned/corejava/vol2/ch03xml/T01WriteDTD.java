package druyaned.corejava.vol2.ch03xml;

import static druyaned.ConsoleColors.blueBold;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class T01WriteDTD extends Topic {
    
    private final Path dtdPath;
    
    public T01WriteDTD(Chapter chapter, Path dtdPath) {
        super(chapter, 1);
        this.dtdPath = dtdPath;
    }
    
    @Override public String title() {
        return "Topic 01 WriteDTD";
    }
    
    @Override public void run() {
        String text =
        """
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
        try {
            Files.writeString(dtdPath, text, StandardOpenOption.WRITE);
            System.out.printf("File %s was written\n", blueBold(dtdPath.toString()));
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
}
