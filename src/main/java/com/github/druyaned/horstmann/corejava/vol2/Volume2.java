package com.github.druyaned.horstmann.corejava.vol2;

import com.github.druyaned.horstmann.corejava.App;
import java.util.ArrayList;
import java.util.List;
import com.github.druyaned.horstmann.corejava.Chapter;
import com.github.druyaned.horstmann.corejava.Volume;
import com.github.druyaned.horstmann.corejava.vol2.ch01.StreamAPI;
import com.github.druyaned.horstmann.corejava.vol2.ch02.InputOutput;
import com.github.druyaned.horstmann.corejava.vol2.ch03.XML;
import com.github.druyaned.horstmann.corejava.vol2.ch04.Networking;
import com.github.druyaned.horstmann.corejava.vol2.ch05.Databases;
import com.github.druyaned.horstmann.corejava.vol2.ch06.DateTimeAPI;
import com.github.druyaned.horstmann.corejava.vol2.ch07.Internationalization;
import com.github.druyaned.horstmann.corejava.vol2.ch08.ScriptCompileAnnotate;
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
    }
    
    @Override public List<? extends Chapter> getChapters() {
        return unmodifiableList(chapters);
    }
    
}
