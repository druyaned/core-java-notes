package druyaned.corejava.vol1.ch14.src.states.waiting;

import static druyaned.corejava.vol1.ch14.src.states.Utils.*;

public class Transfer {
    
    private volatile boolean send = true;
    
    public synchronized void send() throws InterruptedException {
        String methodName = "send()";
        trackEnter(methodName);
        while (!send) {
            this.wait();
        }
        send = false;
        notifyAll();
        body();
        trackExit(methodName);
    }
    
    public synchronized void receive() throws InterruptedException {
        String methodName = "receive()";
        trackEnter(methodName);
        while (send) {
            this.wait();
        }
        send = true;
        notifyAll();
        body();
        trackExit(methodName);
    }
    
    private void body() {
        long stopTimestamp = System.currentTimeMillis() + DELAY_MILLIS;
        while (System.currentTimeMillis() < stopTimestamp) {}
    }
    
}
