package druyaned.corejava.vol1.ch14.src.states.base;

import static druyaned.ConsoleColors.bold;

public class TestBaseStates implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Test Base States"));
        // NEW
        Thread emptyThread = new Thread();
        System.out.println("\"emptyThread = new Thread()\": " + emptyThread.getState());
        // RUNNING
        emptyThread.start();
        System.out.println("\"emptyThread.start()\":        " + emptyThread.getState());
        // TERMINATED
        emptyThread.interrupt();
        System.out.println("\"emptyThread.interrupt()\":    " + emptyThread.getState());
    }
    
}
