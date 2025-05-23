package druyaned.corejava.vol1.ch13;

import druyaned.corejava.Chapter;
import druyaned.corejava.vol1.ch13.src.TestProperties;
import java.nio.file.Path;

/**
 * Practical implementation of the Chapter#13: App Deploying.
 * @author druyaned
 */
public class AppDeploying extends Chapter {
    
    /**
     * Creates the Chapter#13: App Deploying.
     * @param volDataDir the path to the volume's data-directory
     */
    public AppDeploying(Path volDataDir) {
        super(volDataDir, 13);
    }
    
    @Override public String getTitle() {
        return "App Deploying";
    }
    
    @Override public void run() {
        TestProperties.run(getDataDir());
    }
    
}
