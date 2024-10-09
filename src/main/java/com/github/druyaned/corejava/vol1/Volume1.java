package com.github.druyaned.corejava.vol1;

import com.github.druyaned.corejava.App;
import java.util.ArrayList;
import java.util.List;
import com.github.druyaned.corejava.Chapter;
import com.github.druyaned.corejava.Volume;
import com.github.druyaned.corejava.vol1.ch01.Introduction;
import com.github.druyaned.corejava.vol1.ch02.ProgEnvironment;
import com.github.druyaned.corejava.vol1.ch03.Constructs;
import com.github.druyaned.corejava.vol1.ch04.ObjectsClasses;
import com.github.druyaned.corejava.vol1.ch05.Inheritance;
import com.github.druyaned.corejava.vol1.ch06.InterfacesInternalClasses;
import com.github.druyaned.corejava.vol1.ch07.ExceptionsLogging;
import com.github.druyaned.corejava.vol1.ch08.Generics;
import com.github.druyaned.corejava.vol1.ch09.Collections;
import com.github.druyaned.corejava.vol1.ch10.Graphics;
import com.github.druyaned.corejava.vol1.ch11.EventHandling;
import com.github.druyaned.corejava.vol1.ch12.GUI;
import com.github.druyaned.corejava.vol1.ch13.AppDeploying;
import com.github.druyaned.corejava.vol1.ch14.Concurrency;
import java.nio.file.Path;
import static java.util.Collections.unmodifiableList;

/**
 * The first volume of the book.
 * @author druyaned
 * @see App
 */
public class Volume1 extends Volume {
    
    private final List<Chapter> chapters;
    
    /**
     * Creates the first volume of the book.
     * @param bookDataDir the path to the book's data-directory
     */
    public Volume1(Path bookDataDir) {
        super(bookDataDir, 1);
        chapters = new ArrayList();
        chapters.add(new Introduction(dataDir));
        chapters.add(new ProgEnvironment(dataDir));
        chapters.add(new Constructs(dataDir));
        chapters.add(new ObjectsClasses(dataDir));
        chapters.add(new Inheritance(dataDir));
        chapters.add(new InterfacesInternalClasses(dataDir));
        chapters.add(new ExceptionsLogging(dataDir));
        chapters.add(new Generics(dataDir));
        chapters.add(new Collections(dataDir));
        chapters.add(new Graphics(dataDir));
        chapters.add(new EventHandling(dataDir));
        chapters.add(new GUI(dataDir));
        chapters.add(new AppDeploying(dataDir));
        chapters.add(new Concurrency(dataDir));
    }
    
    @Override public List<? extends Chapter> getChapters() {
        return unmodifiableList(chapters);
    }
    
}
