package com.github.druyaned.corejava.vol1.ch02;

import com.github.druyaned.corejava.Chapter;
import java.nio.file.Path;

/**
 * Practical implementation of the Chapter#02: Programming Environment.
 * @author druyaned
 */
public class ProgEnvironment extends Chapter {
    
    /**
     * Creates the Chapter#02: Programming Environment.
     * @param volDataDir the path to the volume's data-directory
     */
    public ProgEnvironment(Path volDataDir) {
        super(volDataDir, 2);
    }
    
    @Override public String getTitle() {
        return "Programming Environment";
    }
    
    @Override public void run() {
        System.out.println("There were nothing to practice with.");
    }
    
}
