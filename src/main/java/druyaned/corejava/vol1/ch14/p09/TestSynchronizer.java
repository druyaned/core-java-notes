package druyaned.corejava.vol1.ch14.p09;

public class TestSynchronizer implements Runnable {
    
    @Override public void run() {
        System.out.println(
        """
        CyclicBarrier
            A synchronization aid that allows a set of threads to
            all wait for each other to reach a common barrier point.
            CyclicBarriers are useful in programs involving a fixed
            sized party of threads that must occasionally wait for
            each other. The barrier is called cyclic because it can
            be re-used after the waiting threads are released.
        Phaser
            A reusable synchronization barrier, similar in functionality to
            CyclicBarrier and CountDownLatch but supporting more flexible usage.
        CountDownLatch
            A synchronization aid that allows one or more threads to wait
            until a set of operations being performed in other threads completes.
        Exchanger
            A synchronization point at which threads can pair and swap elements
            within pairs.
        Semaphore
            A counting semaphore. Conceptually, a semaphore maintains a set of
            permits. Each acquire() blocks if necessary until a permit
            is available, and then takes it. Each release() adds a permit,
            potentially releasing a blocking acquirer. However, no actual permit
            objects are used; the Semaphore just keeps a count of the number
            available and acts accordingly.
        SynchronousQueue
            A blocking queue in which each insert operation must wait for
            a corresponding remove operation by another thread, and vice versa.
        """);
    }
    
}
