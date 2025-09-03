package druyaned.corejava.vol1.ch01intro;

import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;

/**
 * Practical implementation of the Chapter#01: C01Introduction.
 * @author druyaned
 */
public class C01Introduction extends Chapter {
    
    /**
     * Creates the Chapter#01: Introduction.
     * @param volume to which the chapter belongs
     */
    public C01Introduction(Volume volume) {
        super(volume, 1);
    }
    
    @Override public String title() {
        return "Chpater 01 Introduction";
    }
    
}
