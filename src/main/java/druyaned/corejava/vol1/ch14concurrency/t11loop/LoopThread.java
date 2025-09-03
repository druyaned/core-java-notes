package druyaned.corejava.vol1.ch14concurrency.t11loop;

import static druyaned.corejava.vol1.ch14concurrency.t11loop.LoopState.*;
import druyaned.modifyast.anno.Enexrint;
import druyaned.modifyast.anno.EnexrintAll;

@EnexrintAll(
        enterFormat = "[ENTER ${methodName}]"
                + " mainThread=[${thread}.getState]"
                + " loopThread=[${target}]",
        exitFormat = "[EXIT  ${methodName}]"
                + " mainThread=[${thread}.getState]"
                + " loopThread=[${target}]"
)
public final class LoopThread extends Thread {
    
    private final Loop loop;
    private long duration;
    
    public LoopThread(Loop loop) {
        super("LoopThread");
        this.loop = loop;
        return; // for @Enexrint
    }
    
    synchronized void requestStart() throws InterruptedException {
        start();
        loop.setLoopState(PAUSED);
        wait();
        return; // for @Enexrint
    }
    
    synchronized void requestResume() throws InterruptedException {
        loop.setLoopState(LOOPING);
        notify();
        wait();
        return; // for @Enexrint
    }
    
    synchronized void requestPause(long durationMillis) throws InterruptedException {
        duration = durationMillis;
        if (durationMillis != 0L)
            loop.setLoopState(TIMED_PAUSED);
        else
            loop.setLoopState(PAUSED);
        wait();
        return; // for @Enexrint
    }
    
    synchronized void requestStop() throws InterruptedException {
        if (loop.loopState() != LOOPING) {
            loop.setLoopState(STOPPED);
            notify();
        } else
            loop.setLoopState(STOPPED);
        wait();
        return; // for @Enexrint
    }
    
    @Override
    @Enexrint(ignore = true)
    public void run() {
        try {
            while (loop.loopState() != STOPPED) {
                loop.runTaskIfNotNull();
                stageOfChange();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
    
    private synchronized void stageOfChange() throws InterruptedException {
        if (loop.loopState() == TIMED_PAUSED) {
            notify();
            wait(duration);
            if (loop.loopState() == TIMED_PAUSED)
                loop.setLoopState(LOOPING);
            else
                notify();
        }
        if (loop.loopState() == PAUSED) {
            notify();
            wait();
            notify();
        }
        return; // for @Enexrint
    }
    
    @Override
    @Enexrint(ignore = true)
    public String toString() {
        return "LoopThread{loop=" + loop + '}';
    }
    
}
