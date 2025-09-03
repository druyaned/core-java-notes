package druyaned.corejava.vol1.ch13deploy;

import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;

/**
 * Practical implementation of the Chapter#13: App Deploying.
 * @author druyaned
 */
public class C13AppDeploying extends Chapter {
    
    /**
     * Creates the Chapter#13: App Deploying.
     * @param volume to which the chapter belongs
     */
    public C13AppDeploying(Volume volume) {
        super(volume, 13);
        topics().add(new T01Properties(this));
    }
    
    @Override public String title() {
        return "Chapter 13 App Deploying";
    }
    
}
