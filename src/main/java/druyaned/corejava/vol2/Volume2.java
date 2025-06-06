package druyaned.corejava.vol2;

import druyaned.corejava.App;
import java.util.ArrayList;
import java.util.List;
import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;
import druyaned.corejava.vol2.ch01.StreamAPI;
import druyaned.corejava.vol2.ch02.InputOutput;
import druyaned.corejava.vol2.ch03.XML;
import druyaned.corejava.vol2.ch04.Networking;
import druyaned.corejava.vol2.ch05.Databases;
import druyaned.corejava.vol2.ch06.DateTimeAPI;
import druyaned.corejava.vol2.ch07.Internationalization;
import druyaned.corejava.vol2.ch08.ScriptCompileAnnotate;
import druyaned.corejava.vol2.ch09.Security;
import java.nio.file.Path;
import static java.util.Collections.unmodifiableList;

/**
 * The second volume of the book.
 * @author druyaned
 * @see App
 */
public class Volume2 extends Volume {
    
    private final List<Chapter> chapters;
    
    /**
     * Creates the second volume of the book.
     * @param bookDataDir the path to the book's data-directory
     */
    public Volume2(Path bookDataDir) {
        super(bookDataDir, 2);
        chapters = new ArrayList();
        chapters.add(new StreamAPI(dataDir));
        chapters.add(new InputOutput(dataDir));
        chapters.add(new XML(dataDir));
        chapters.add(new Networking(dataDir));
        chapters.add(new Databases(dataDir));
        chapters.add(new DateTimeAPI(dataDir));
        chapters.add(new Internationalization(dataDir));
        chapters.add(new ScriptCompileAnnotate(dataDir));
        chapters.add(new Security(dataDir));
    }
    
    @Override public List<? extends Chapter> getChapters() {
        return unmodifiableList(chapters);
    }
    
}
