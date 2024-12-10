package druyaned.corejava.vol1.ch14.src.states.waiting;

import static druyaned.ConsoleColors.bold;
import static druyaned.corejava.vol1.ch14.src.states.Utils.*;
import java.util.ArrayList;
import java.util.List;

public class TestWaitingState implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Test Waiting State"));
        System.out.println(
                """
                Short Description:
                    1.  Transfer has two synchronized methods which last for a while:
                        1.  "send()".
                        2.  "receive()".
                    2.  ReceiveThread calls "transger.receive()" and starts waiting.
                    3.  SendThread calls "transfer.send()".
                    4.  "send()" awakes ReceiveThread which becomes blocked due to synchronization.
                    5.  SendThread finishes execution.
                    6.  Then ReceiveThread does the same.""");
        final Transfer transfer = new Transfer();
        Thread sendThread = new Thread(() -> {
            try {
                transfer.send();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }, "SendThread");
        Thread receiveThread = new Thread(() -> {
            try {
                transfer.receive();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }, "ReceiveThread");
        List<Thread> threads = new ArrayList<>();
        threads.add(sendThread);
        threads.add(receiveThread);
        receiveThread.start();
        currentThreadSleep(DELAY_MILLIS / 2);
        printStates(threads);
        sendThread.start();
        currentThreadSleep(DELAY_MILLIS / 2);
        printStates(threads);
        currentThreadSleep(DELAY_MILLIS);
        printStates(threads);
        currentThreadJoin(threads.get(1));
    }
    
}
