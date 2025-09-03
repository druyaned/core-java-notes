package druyaned.corejava.vol1.ch11events;

import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;

/**
 * Practical implementation of the Chapter#11: Event Handling.
 * @author druyaned
 */
public class C11EventHandling extends Chapter {
    
    /**
     * Creates the Chapter#11: Event Handling.
     * @param volume to which the chapter belongs
     */
    public C11EventHandling(Volume volume) {
        super(volume, 11);
        topics().add(new T01KeyPress(this));
        topics().add(new T02FrameStyle(this));
        topics().add(new T03Action(this));
        topics().add(new T04Mouse(this));
    }
    
    @Override public String title() {
        return "Chapter 11 Event Handling";
    }
    
}
