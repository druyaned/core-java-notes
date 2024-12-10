package druyaned.corejava.vol2.ch09;

import druyaned.corejava.Chapter;
import java.nio.file.Path;

/**
 * Practical implementation of the Chapter#09: Security.
 * @author druyaned
 */
public class Security extends Chapter {
    
    /**
     * Creates the Chapter#09: Security.
     * @param volDataDir the path to the volume's data-directory
     */
    public Security(Path volDataDir) {
        super(volDataDir, 9);
    }
    
    @Override public String getTitle() {
        return "Security";
    }
    
    @Override public void run() {
    }
    
}
