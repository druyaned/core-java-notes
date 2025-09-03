package druyaned.corejava.vol2.ch09security;

import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;

/**
 * Practical implementation of the Chapter#09: C09Security.
 * @author druyaned
 */
public class C09Security extends Chapter {
    
    /**
     * Creates the Chapter#09: Security.
     * @param volume to which the chapter belongs
     */
    public C09Security(Volume volume) {
        super(volume, 9);
        topics().add(new T01ClassLoaders(this));
    }
    
    @Override public String title() {
        return "Chapter 09 Security";
    }
    
}
