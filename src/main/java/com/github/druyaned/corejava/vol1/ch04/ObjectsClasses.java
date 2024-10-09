package com.github.druyaned.corejava.vol1.ch04;

import com.github.druyaned.corejava.Chapter;
import java.nio.file.Path;

/**
 * Practice implementation of the Chapter#04: Objects and Classes.
 * @author druyaned
 */
public class ObjectsClasses extends Chapter {
    
    /**
     * Creates the Chapter#04: Objects and Classes.
     * @param volDataDir the path to the volume's data-directory
     */
    public ObjectsClasses(Path volDataDir) {
        super(volDataDir, 4);
    }
    
    @Override public String getTitle() {
        return "Objects and Classes";
    }
    
    @Override public void run() {
        TestMethods.testAll(getDataDir());
    }
    
}
