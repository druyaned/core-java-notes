package druyaned.corejava.vol1.ch05inherit;

import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;

/**
 * Practical implementation of the Chapter#05: C05Inheritance.
 * @author druyaned
 */
public class C05Inheritance extends Chapter {
    
    /**
     * Creates the Chapter#05: Inheritance.
     * @param volume to which the chapter belongs
     */
    public C05Inheritance(Volume volume) {
        super(volume, 5);
        topics().add(new T01ClassAnalyzer(this));
    }
    
    @Override public String title() {
        return "Chapter 05 Inheritance";
    }
    
}
