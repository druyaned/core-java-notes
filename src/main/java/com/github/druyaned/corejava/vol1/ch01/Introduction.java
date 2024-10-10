package com.github.druyaned.corejava.vol1.ch01;

import com.github.druyaned.corejava.Chapter;
import java.nio.file.Path;

/**
 * Practical implementation of the Chapter#01: Introduction.
 * @author druyaned
 */
public class Introduction extends Chapter {
    
    /**
     * Creates the Chapter#01: Introduction.
     * @param volDataDir the path to the volume's data-directory
     */
    public Introduction(Path volDataDir) {
        super(volDataDir, 1);
    }
    
    @Override public String getTitle() {
        return "Introduction";
    }
    
    @Override public void run() {
        System.out.println("There were nothing to practice with.");
    }
    
}
