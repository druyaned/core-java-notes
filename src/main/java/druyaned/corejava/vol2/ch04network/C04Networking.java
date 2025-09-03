package druyaned.corejava.vol2.ch04network;

import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;

/**
 * Practical implementation of the Chapter#04: C04Networking.
 * @author druyaned
 */
public class C04Networking extends Chapter {
    
    /**
     * Creates the Chapter#04: Networking.
     * @param volume to which the chapter belongs
     */
    public C04Networking(Volume volume) {
        super(volume, 4);
        topics().add(new T01Socket(this));
        topics().add(new T02Server(this));
        topics().add(new T03Interruptible(this));
        topics().add(new T04URL(this));
        topics().add(new T05POST(this));
        topics().add(new T06Mail(this));
        topics().add(new T07HttpGET(this));
    }
    
    @Override public String title() {
        return "Chapter 04 Networking";
    }
    
}
