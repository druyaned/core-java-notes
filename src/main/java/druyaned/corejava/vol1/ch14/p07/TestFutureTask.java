package druyaned.corejava.vol1.ch14.p07;

import static druyaned.ConsoleColors.bold;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class TestFutureTask implements Runnable {
    
    private static final long LOOP_TIME = 5L; // millis
    
    @Override public void run() {
        final int iterations = 200;
        final AtomicInteger iterationsDone = new AtomicInteger(0);
        // Callable - Runnable with returning
        Callable<Boolean> additionAndRemoval = () -> {
            Runnable waitLoop = () -> {
                long end = System.currentTimeMillis() + LOOP_TIME;
                long curr;
                do
                    curr = System.currentTimeMillis();
                while (curr < end);
            };
            for (int i = 0; i < iterations; i++) {
                waitLoop.run();
                iterationsDone.incrementAndGet();
            }
            return iterationsDone.get() == iterations;
        };
        // FutureTask makes Callable be Runnable and provides get(), cancel() and so on
        FutureTask<Boolean> task = new FutureTask<>(additionAndRemoval);
        Thread thread = new Thread(task);
        System.out.println("Starting thread...");
        thread.start();
        System.out.println("thread.isInterrupted()=" + thread.isInterrupted());
        System.out.println("Interrupting thread...");
        thread.interrupt();
        System.out.println("thread.isInterrupted()=" + thread.isInterrupted());
        try {
            System.out.println("Task result (do all iterations completed?): " + task.get());
        } catch (InterruptedException | ExecutionException exc) {
            System.out.println("Bad usage of task.get()");
            exc.printStackTrace();
        }
        System.out.println("Iterations:      " + iterations);
        System.out.println("Iterations Done: " + iterationsDone);
        System.out.println("Now using " + bold("Future.cancel(true)"));
        iterationsDone.set(0);
        task = new FutureTask<>(additionAndRemoval);
        thread = new Thread(task);
        System.out.println("Starting thread...");
        thread.start();
        System.out.println("task.isCancelled()=" + task.isCancelled());
        System.out.println("Sleeping in main-thread...");
        try {
            Thread.sleep(100L);
        } catch (InterruptedException exc) {
            throw new IllegalStateException(exc);
        }
        System.out.println("Cancelling thread...");
        task.cancel(true);
        System.out.println("task.isCancelled()=" + task.isCancelled());
        try {
            // Future.get() makes the invoker-thread wait
            System.out.println("Task result (do all iterations completed?): " + task.get());
        } catch (InterruptedException | ExecutionException | CancellationException exc) {
            System.out.println("In a catch-block");
            exc.printStackTrace();
            System.out.println("Bad usage of task.get()");
        }
        System.out.println("Iterations:      " + iterations);
        System.out.println("Iterations Done: " + iterationsDone);
    }
    
}
