package druyaned.corejava.vol1.ch12gui;

import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;

/**
 * Practical implementation of the Chapter#12: C12GUI.
 * @author druyaned
 */
public class C12GUI extends Chapter {
    
    /**
     * Creates the Chapter#12: GUI.
     * @param volume to which the chapter belongs
     */
    public C12GUI(Volume volume) {
        super(volume, 12);
        topics().add(new T01Calc(this));
        topics().add(new T02Text(this));
        topics().add(new T03Menu(this));
        topics().add(new T04GridBag(this));
        topics().add(new T05Dialog(this));
        topics().add(new T06User(this));
    }
    
    @Override public String title() {
        return "Chapter 12 GUI";
    }
    
}
