package druyaned.corejava.vol2.ch09;

import druyaned.corejava.Chapter;
import druyaned.corejava.vol2.ch09.src.P01ClassLoaders;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
        List<Runnable> parts = new ArrayList<>();
        parts.add(new P01ClassLoaders());
        choosePartAndRun(parts);
    }
    
}
