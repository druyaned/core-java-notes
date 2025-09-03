package druyaned.corejava.vol1.ch14concurrency.t02interrupt;

/**
 * Loop to wait for a time instead of Thread.sleep(time).
 * @author druyaned
 */
public class WaitLoop implements Runnable {
    
    private final long loopTime; // millis
    
    public WaitLoop(long loopTime) {
        this.loopTime = loopTime;
    }
    
    @Override public void run() {
        long end = System.currentTimeMillis() + loopTime;
        while (System.currentTimeMillis() < end) {}
    }
    
}
