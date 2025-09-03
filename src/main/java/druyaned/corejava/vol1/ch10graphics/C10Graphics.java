package druyaned.corejava.vol1.ch10graphics;

import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;

/**
 * Practical implementation of the Chapter#10: C10Graphics.
 * @author druyaned
 */
public class C10Graphics extends Chapter {
    
    /**
     * Creates the Chapter#10: Graphics.
     * @param volume to which the chapter belongs
     */
    public C10Graphics(Volume volume) {
        super(volume, 10);
        topics().add(new T01GoodFrame(this));
        topics().add(new T02LikeFrame(this));
    }
    
    @Override public String title() {
        return "Chapter 10 Graphics";
    }
    
}
