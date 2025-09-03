package druyaned.corejava.vol1.ch14concurrency;

import druyaned.corejava.vol1.ch14concurrency.t11loop.Loop;
import static druyaned.ConsoleColors.bold;
import static druyaned.corejava.App.sin;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;

public class T11Loop extends Topic {
    
    public T11Loop(Chapter chapter) {
        super(chapter, 11);
    }
    
    @Override public String title() {
        return "Topic 11 Loop";
    }
    
    @Override public void run() {
        try {
            withLastRequestResume();
            withLastRequestPause();
        } catch (InterruptedException exc) {
            throw new IllegalStateException(exc);
        }
        testLoopInteractive();
    }
    
    private void withLastRequestResume() throws InterruptedException {
        System.out.println("\n" + bold("With Last Request Resume"));
        Loop loop = baseBodyForTests();
        loop.resume();
        loop.stop();
    }
    
    private void withLastRequestPause() throws InterruptedException {
        System.out.println("\n" + bold("With Last Request Pause"));
        Loop loop = baseBodyForTests();
        loop.stop();
    }
    
    private Loop baseBodyForTests() throws InterruptedException {
        Loop loop = new Loop();
        loop.start();
        loop.setTask(getTask());
        loop.resume();
        delayFor(2 * DURATION_MILLIS);
        loop.pause(DURATION_MILLIS / 2);
        delayFor(DURATION_MILLIS);
        loop.pause();
        return loop;
    }
    
    private void delayFor(long durationMillis) {
        long stopTimestamp = System.currentTimeMillis() + durationMillis;
        while (System.currentTimeMillis() < stopTimestamp) {}
    }
    
    private void testLoopInteractive() {
        System.out.println("\n" + bold("Test Loop Interactive"));
        try {
            Loop loop = new Loop();
            loop.start();
            loop.setTask(getTask());
            String menu =
            """
            Menu:
                1. "r" - resume
                2. "p" - pause
                3. "t" - timed pause
                4. "s" - stop
            """;
            String input;
            do {
                System.out.print(menu);
                switch (input = sin.nextLine()) {
                    case "r" -> System.out.println("Result: " + loop.resume());
                    case "p" -> System.out.println("Result: " + loop.pause());
                    case "t" -> System.out.println("Result: " + loop.pause(DURATION_MILLIS * 4));
                    case "s" -> System.out.println("Result: " + loop.stop());
                }
            } while (!input.equals("s"));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
    
    public static final long DURATION_MILLIS = 128L;
    
    private static Runnable getTask() {
        return () -> {
            System.out.println("  The task is simple:"
                    + " print this message and wait for a while.");
            long stopTimestamp = System.currentTimeMillis() + DURATION_MILLIS;
            while (System.currentTimeMillis() < stopTimestamp) {}
        };
    }
    
}
