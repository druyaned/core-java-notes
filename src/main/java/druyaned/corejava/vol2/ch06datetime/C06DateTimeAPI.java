package druyaned.corejava.vol2.ch06datetime;

import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;

/**
 * Practical implementation of the Chapter#06: Date-Time API.
 * @author druyaned
 */
public class C06DateTimeAPI extends Chapter {
    
    /**
     * Creates the Chapter#06: Date-Time API.
     * @param volume to which the chapter belongs
     */
    public C06DateTimeAPI(Volume volume) {
        super(volume, 6);
        topics().add(new T01DateTime(this));
    }
    
    @Override public String title() {
        return "Chapter 06 Date-Time API";
    }
    
}
