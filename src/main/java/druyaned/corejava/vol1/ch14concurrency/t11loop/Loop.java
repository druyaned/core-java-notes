package druyaned.corejava.vol1.ch14concurrency.t11loop;

import static druyaned.corejava.vol1.ch14concurrency.t11loop.LoopState.*;
import druyaned.modifyast.anno.Enexrint;
import druyaned.modifyast.anno.EnexrintAll;

@EnexrintAll(
        enterFormat = "[ENTER ${methodName}]"
                + " mainThread=[${thread}.getState]"
                + " loopString=[${target}]",
        exitFormat = "[EXIT  ${methodName}]"
                + " mainThread=[${thread}.getState]"
                + " loopString=[${target}]"
)
public final class Loop {
    
    private volatile LoopState loopState = NOT_STARTED;
    private final LoopThread loopThread = new LoopThread(this);
    private volatile Runnable task = null;
    
    public synchronized boolean start() throws InterruptedException {
        if (loopState != NOT_STARTED)
            return false;
        loopThread.requestStart();
        return true;
    }
    
    public synchronized void setTask(Runnable task) throws InterruptedException {
        this.task = task;
        return; // for @Enexrint
    }
    
    public synchronized boolean resume() throws InterruptedException {
        if (loopState == NOT_STARTED || loopState == STOPPED || loopState == LOOPING) {
            return false;
        }
        loopThread.requestResume();
        return true;
    }
    
    public synchronized boolean pause(long durationMillis) throws InterruptedException {
        if (loopState != LOOPING || durationMillis < 0L) {
            return false;
        }
        loopThread.requestPause(durationMillis);
        return true;
    }
    
    public synchronized boolean pause() throws InterruptedException {
        return pause(0L);
    }
    
    public synchronized boolean stop() throws InterruptedException {
        if (loopState == NOT_STARTED || loopState == STOPPED) {
            return false;
        }
        loopThread.requestStop();
        return true;
    }
    
    @Enexrint(ignore = true)
    public LoopState loopState() {
        return loopState;
    }
    
    @Enexrint(ignore = true)
    void setLoopState(LoopState loopState) {
        this.loopState = loopState;
    }
    
    @Enexrint(ignore = true)
    void runTaskIfNotNull() {
        if (task != null) {
            task.run();
        }
    }
    
    @Override
    @Enexrint(ignore = true)
    public String toString() {
        return "Loop{"
                + "state=" + loopState
                + ", loopThread.getState=" + loopThread.getState()
                + '}';
    }
    
}
