package druyaned.corejava.vol1.ch14.src.states.blocked;

import static druyaned.corejava.vol1.ch14.src.states.Utils.*;

public class Component {
    
    public synchronized void businessLogic() {
        String methodName = "businessLogic()";
        trackEnter(methodName);
        body();
        trackExit(methodName);
    }
    
    private void body() {
        long stopTimestamp = System.currentTimeMillis() + DELAY_MILLIS;
        while (System.currentTimeMillis() < stopTimestamp) {}
    }
    
}
