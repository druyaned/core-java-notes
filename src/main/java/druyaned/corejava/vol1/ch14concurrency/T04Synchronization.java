package druyaned.corejava.vol1.ch14concurrency;

import static druyaned.ConsoleColors.bold;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class T04Synchronization extends Topic {
    
    public T04Synchronization(Chapter chapter) {
        super(chapter, 4);
    }
    
    @Override public String title() {
        return "Topic 04 Synchronization";
    }
    
    @Override public void run() {
        monitorPrinciple();
        volatileKeyWord();
        atomicInteger();
        threadLocal();
        moreAboutLocks();
    }
    
    private static void monitorPrinciple() {
        System.out.println("\n" + bold("Monitor Principle:"));
        System.out.print(
        """
        1. Monitor has only private fields.
        2. Each object has its own blocking.
        3. Each method is blocked by the blocking.
        4. The blocking can have several conditions.
        """
        );
    }
    
    private static void volatileKeyWord() {
        System.out.println("\n" + bold("Volatile Key Word"));
        System.out.print(
        """
        Main features of volatile modificator {
            1. Read or write operations are atomic for the variable.
            2. Result of read and write operations is visible for
                all other threads that use the variable for reading.
        }
        Not good way {
            private boolean done;
            public synchronized boolean isDone() {return done;}
            public synchronized void setDone() {done = true;}
            public synchronized void unsetDone() {done = false;}
        }
        Good way {
            private volatile boolean done;
            public boolean isDone() {return done;}
            public void setDone() {done = true;}
            public void unsetDone() {done = false;}
        }
        """
        );
    }
    
    private static void atomicInteger() {
        System.out.println("\n" + bold("Atomic Integer"));
        AtomicInteger a = new AtomicInteger();
        System.out.println("a.set(5);");
        a.set(5);
        // Old way
        System.out.println("Setting a new value by the outdated approach.");
        int expectedOldValue, newValue;
        int toBeSet = 8;
        do {
            expectedOldValue = a.get();
            newValue = Math.max(expectedOldValue, toBeSet);
        } while (!a.compareAndSet(expectedOldValue, newValue));
        System.out.println("a=" + a);
        // New way
        System.out.println("a.set(5);");
        a.set(5);
        System.out.println("Updating by a convenient approach.");
        a.updateAndGet(value -> Math.max(value, toBeSet));
        System.out.println("a=" + a);
    }
    
    private static void threadLocal() {
        System.out.println("\n" + bold("ThreadLocal"));
        final ThreadLocal<LocalDateTime> timeFactory
                = ThreadLocal.withInitial(LocalDateTime::now);
        final int THREAD_COUNT = 3;
        Thread[] threads = new Thread[THREAD_COUNT];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                LocalDateTime time = timeFactory.get();
                synchronized (sb) {
                    sb
                            .append(" time=").append(time)
                            .append("; hash=").append(time.hashCode())
                            .append("; thread=").append(Thread.currentThread())
                            .append('\n');
                }
                timeFactory.remove();
            });
            threads[i].start();
        }
        try {
            for (int i = 0; i < THREAD_COUNT; i++)
                threads[i].join();
        } catch (InterruptedException exc) {
            exc.printStackTrace();
            return;
        }
        System.out.println("Threads (amount=" + THREAD_COUNT
                + ") have completed their tasks.");
        System.out.println("Result:");
        System.out.print(sb.toString());
    }
    
    private static void moreAboutLocks() {
        System.out.println("\n" + bold("More About Locks"));
        System.out.print(
        """
        Useful trick with tryLock:
            if (myLock.tryLock()) {
                // Now the thread has the blocking
                try {...}
                finally {myLock.unlock();}
            } else {
                // Do something valuable
            }
            // Also can be used with a timeout
        """
        );
        System.out.print(
        """
        Read/Write blocking:
            private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
            private Lock readLock = rwlock.readLock();
            private Lock writeLock = rwlock.writeLock();
            // Now these locks can be used in getters and setters
        """
        );
    }
    
}
