package druyaned.corejava.vol1.ch06;

import druyaned.corejava.vol1.ch06.src.TestLambda;
import druyaned.corejava.vol1.ch06.src.TestCloneable;
import druyaned.corejava.vol1.ch06.src.TestInners;
import druyaned.corejava.vol1.ch06.src.TestTimer;
import druyaned.corejava.Chapter;
import java.nio.file.Path;

/**
 * Practical implementation of the Chapter#06: Interfaces, Lambda and Internal Classes.
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
