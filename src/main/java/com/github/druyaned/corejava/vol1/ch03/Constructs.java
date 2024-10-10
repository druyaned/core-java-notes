package com.github.druyaned.corejava.vol1.ch03;

import com.github.druyaned.corejava.Chapter;
import java.nio.file.Path;

/**
 * Practical implementation of the Chapter#03: Basic Constructs.
 * @author druyaned
 */
public class Constructs extends Chapter {
    
    /**
     * Creates the Chapter#03: Basic Constructs.
     * @param volDataDir the path to the volume's data-directory
     */
    public Constructs(Path volDataDir) {
        super(volDataDir, 3);
    }
    
    @Override public String getTitle() {
        return "Basic Constructs";
    }
    
    @Override public void run() {
        TestMethods.testAll(getDataDir());
    }
    
}
