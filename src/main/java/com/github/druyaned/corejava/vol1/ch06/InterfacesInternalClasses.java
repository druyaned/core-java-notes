package com.github.druyaned.corejava.vol1.ch06;

import com.github.druyaned.corejava.vol1.ch06.src.TestLambda;
import com.github.druyaned.corejava.vol1.ch06.src.TestCloneable;
import com.github.druyaned.corejava.vol1.ch06.src.TestInners;
import com.github.druyaned.corejava.vol1.ch06.src.TestTimer;
import com.github.druyaned.corejava.Chapter;
import java.nio.file.Path;

/**
 * Practice implementation of the Chapter#06: Interfaces, Lambda and Internal Classes.
 * @author druyaned
 */
public class InterfacesInternalClasses extends Chapter {
    
    /**
     * Creates the Chapter#06: Interfaces, Lambda and Internal Classes.
     * @param volDataDir the path to the volume's data-directory
     */
    public InterfacesInternalClasses(Path volDataDir) {
        super(volDataDir, 6);
    }
    
    @Override public String getTitle() {
        return "Interfaces, Lambda and Internal Classes";
    }
    
    @Override public void run() {
        TestTimer.run();
        TestCloneable.run();
        TestLambda.run();
        TestInners.run();
    }
    
}
