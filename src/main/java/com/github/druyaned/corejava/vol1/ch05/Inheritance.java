package com.github.druyaned.corejava.vol1.ch05;

import com.github.druyaned.corejava.Chapter;
import java.nio.file.Path;

/**
 * Practice implementation of the Chapter#05: Inheritance.
 * @author druyaned
 */
public class Inheritance extends Chapter {
    
    /**
     * Creates the Chapter#05: Inheritance.
     * @param volDataDir the path to the volume's data-directory
     */
    public Inheritance(Path volDataDir) {
        super(volDataDir, 5);
    }
    
    @Override public String getTitle() {
        return "Inheritance";
    }
    
    @Override public void run() {
        ClassAnalyzer.showClass(Inheritance.class);
    }
    
}
