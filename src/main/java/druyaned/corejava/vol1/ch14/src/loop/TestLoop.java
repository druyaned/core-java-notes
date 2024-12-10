package druyaned.corejava.vol1.ch14.src.loop;

import static druyaned.ConsoleColors.bold;

public class TestLoop implements Runnable {
    
    public static final long DURATION_MILLIS = 128L;
    
    public static Runnable getTask() {
        return () -> {
            System.out.println("  The task is simple:"
                    + " print this message and wait for a while.");
            long stopTimestamp = System.currentTimeMillis() + DURATION_MILLIS;
            while (System.currentTimeMillis() < stopTimestamp) {}
        };
    }
    
    @Override public void run() {
        System.out.println("\n" + bold("Test Loop"));
        try {
            testWithLastRequestResume();
            testWithLastRequestPause();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
    
    public Loop baseBodyForTests() throws InterruptedException {
        Loop loop = new Loop();
        loop.start();
        loop.setTask(getTask());
        loop.resume();
        currentThreadDelay(2 * DURATION_MILLIS);
        loop.pause(DURATION_MILLIS / 2);
        currentThreadDelay(DURATION_MILLIS);
        loop.pause();
        return loop;
    }
    
    public void testWithLastRequestResume() throws InterruptedException {
        System.out.println("\n" + bold("Test With Last Request Resume"));
        Loop loop = baseBodyForTests();
        loop.resume();
        loop.stop();
    }
    
    public void testWithLastRequestPause() throws InterruptedException {
        System.out.println("\n" + bold("Test With Last Request Pause"));
        Loop loop = baseBodyForTests();
        loop.stop();
    }
    
    public void currentThreadDelay(long durationMillis) {
        long stopTimestamp = System.currentTimeMillis() + durationMillis;
        while (System.currentTimeMillis() < stopTimestamp) {}
    }
    
}
