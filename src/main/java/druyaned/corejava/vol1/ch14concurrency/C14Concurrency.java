package druyaned.corejava.vol1.ch14concurrency;

import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;

/**
 * Practical implementation of the Chapter#14: C14Concurrency.
 * @author druyaned
 */
public class C14Concurrency extends Chapter {
    
    /**
     * Creates the Chapter#14: Concurrency.
     * @param volume to which the chapter belongs
     */
    public C14Concurrency(Volume volume) {
        super(volume, 14);
        topics().add(new T01Bounce(this));
        topics().add(new T02Interrupt(this));
        topics().add(new T03Banks(this));
        topics().add(new T04Synchronization(this));
        topics().add(new T05FileSearches(this));
        topics().add(new T06Executors(this));
        topics().add(new T07FutureTask(this));
        topics().add(new T08Collections(this));
        topics().add(new T09Synchronizer(this));
        topics().add(new T10Swing(this));
        topics().add(new T11Loop(this));
    }
    
    @Override public String title() {
        return "Chapter 14 Concurrency";
    }
    
}
