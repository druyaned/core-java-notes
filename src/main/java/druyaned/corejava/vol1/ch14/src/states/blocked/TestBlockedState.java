package druyaned.corejava.vol1.ch14.src.states.blocked;

import static druyaned.ConsoleColors.bold;
import static druyaned.corejava.vol1.ch14.src.states.Utils.*;
import java.util.ArrayList;
import java.util.List;

public class TestBlockedState implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Test Blocked State"));
        System.out.println(
                """
                Short Description:
                    1.  Component has synchronized "businessLogic()" which lasts for a while.
                    2.  Thread-1 calls "component.businessLogic()".
                    3.  Thread-2 sleeps for a while and then calls "component.businessLogic()".
                    4.  After the sleep Thread-2 is blocked and Thread-1 is running.""");
        final Component component = new Component();
        List<Thread> threads = new ArrayList<>();
        threads.add(new Thread(() -> {
            component.businessLogic();
        }, "Thread-1"));
        threads.add(new Thread(() -> {
            try {
                Thread.sleep(DELAY_MILLIS / 4);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                Thread.currentThread().interrupt();
            }
            component.businessLogic();
        }, "Thread-2"));
        threads.forEach(Thread::start);
        currentThreadSleep(DELAY_MILLIS / 2);
        resetPrintCounter();
        printStates(threads);
        currentThreadSleep(DELAY_MILLIS);
        printStates(threads);
        currentThreadJoin(threads.get(1));
    }
    
}
