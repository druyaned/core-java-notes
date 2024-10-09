package com.github.druyaned.corejava.vol1.ch14.src.demo;

import java.util.ArrayList;
import java.util.List;

public class TestDemo implements Runnable {
    
    @Override public void run() {
        System.out.println("Running concurrency demonstration.");
        System.out.println("Waiting for the execution...");
        final int TIMES = 4;
        long delays[] = { 100L, 200L, 300L };
        long remainders[] = { 300L, 200L, 100L };
        final int THREAD_COUNT = delays.length;
        long totalDelay = 0L;
        for (int i = 0; i < THREAD_COUNT; i++) {
            totalDelay += delays[i] + remainders[i];
        }
        StringBuilder builder = new StringBuilder();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads.add(makeThread(i + 1, builder, TIMES, delays[i], remainders[i]));
        }
        threads.forEach(Thread::start);
        try {
            Thread.sleep(totalDelay);
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }
        System.out.print(builder.toString());
    }
    
    private static Thread makeThread(
            int id, StringBuilder builder,
            int TIMES, long delay, long remainder
    ) {
        return new Thread(() -> {
            final String threadName = Thread.currentThread().getName();
            try {
                for (int i = 0; i < TIMES; i++) {
                    Thread.sleep(delay);
                    builder.append(threadName)
                            .append(": after delay#")
                            .append(i + 1)
                            .append('\n');
                    Thread.sleep(remainder);
                }
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }
        }, "Thread" + id);
    }
    
}
