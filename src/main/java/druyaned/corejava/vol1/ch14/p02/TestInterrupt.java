package druyaned.corejava.vol1.ch14.p02;

import static druyaned.ConsoleColors.blueBold;
import static druyaned.ConsoleColors.bold;
import static druyaned.ConsoleColors.greenBold;

public class TestInterrupt implements Runnable {
    
    private static final long LOOP_TIME = 100L; // millis
    private static final long SLEEP_TIME = LOOP_TIME; // millis
    private static final ThreadLocal<Runnable> WAIT_LOOP_MAKER
            = ThreadLocal.withInitial(() -> {
                // Loop to wait for a time instead of Thread.sleep(time)
                return () -> {
                    long end = System.currentTimeMillis() + LOOP_TIME;
                    long curr;
                    do
                        curr = System.currentTimeMillis();
                    while (curr < end);
                };
            });
    
    @Override public void run() {
        interruptionWithNoSleep();
        interruptionWithSleep();
        System.out.print(
        """
        
        Interruption flag is cleared:
            1. In 'Thread.interrupted()'
            2. In 'Thread.sleep(time)' when InterruptedException is thrown
            3. In 'Thread.join(time)' when InterruptedException is thrown
        Instead 'someThread.isInterrupted()' doesn't affect the flag.
        """);
    }
    
    private void interruptionWithNoSleep() {
        System.out.println("\n" + bold("Interruption With No Sleep"));
        // Runnable task for infThread, which uses waitLoop
        Runnable infTask = () -> {
            final Runnable waitLoop = WAIT_LOOP_MAKER.get();
            final Thread infThread = Thread.currentThread();
            System.out.printf("  In %s starting 'while(true)'...\n",
                    greenBold("infThread"));
            for (int i = 0; true; i++) {
                System.out.printf("  #%02d %s run 'waitLoop.run()'...\n",
                        i, greenBold("infThread"));
                waitLoop.run();
                System.out.printf("    state=%s interruption=%s\n",
                        infThread.getState(), infThread.isInterrupted());
                if (infThread.isInterrupted()) {
                    System.out.printf("    %s: Breaking the loop 'while(true)'\n",
                            greenBold("infThread"));
                    break;
                }
            }
        };
        final Runnable waitLoop = WAIT_LOOP_MAKER.get();
        // Execution
        final Thread infThread = new Thread(infTask);
        //   1. Start infThread
        System.out.printf("In %s starting 'infThread.start()'...\n",
                blueBold("mainThread"));
        infThread.start();
        //   2. WaitLoop several times
        int waitCount = 4;
        System.out.printf("In %s running %d times 'waitLoop.run()'...\n",
                blueBold("mainThread"), waitCount);
        for (int i = 0; i < waitCount; i++)
            waitLoop.run();
        //   3. Interrupt infThread
        System.out.printf("In %s interrupt 'infThread.interrupt()'...\n",
                blueBold("mainThread"));
        infThread.interrupt();
        //   4. Join infThread
        try {
            System.out.printf("In %s joining 'infThread.join()'...\n",
                blueBold("mainThread"));
            infThread.join();
        } catch (InterruptedException exc) {
            throw new IllegalStateException(exc);
        }
        //   5. Show termination and interruption
        System.out.printf("%s: state=%s interruption=%s\n",
                blueBold("infThread"), infThread.getState(), infThread.isInterrupted());
    }
    
    private void interruptionWithSleep() {
        System.out.println("\n" + bold("Interruption With Sleep"));
        // Runnable task for infThread, which uses Thread.sleep(time)
        Runnable infTask = () -> {
            final Thread infThread = Thread.currentThread();
            System.out.printf("  In %s starting 'while(true)'...\n",
                    greenBold("infThread"));
            try {
                for (int i = 0; true; i++) {
                    System.out.printf("  #%02d %s sleep 'Thread.sleep(time)'...\n",
                            i, greenBold("infThread"));
                    Thread.sleep(SLEEP_TIME);
                    System.out.printf("    state=%s interruption=%s\n",
                            infThread.getState(), infThread.isInterrupted());
                }
            } catch (InterruptedException exc) {
                System.out.printf("  %s: In catch-block so 'while(true)' has been left\n",
                        greenBold("infThread"));
                System.out.printf("  state=%s interruption=%s\n",
                        infThread.getState(), infThread.isInterrupted());
            }
        };
        // Execution
        final Thread infThread = new Thread(infTask);
        //   1. Start infThread
        System.out.printf("In %s starting 'infThread.start()'...\n",
                blueBold("mainThread"));
        infThread.start();
        //   2. WaitLoop several times
        int sleepCount = 4;
        System.out.printf("In %s sleep %d times 'Thread.sleep(time)'...\n",
                blueBold("mainThread"), sleepCount);
        try {
            for (int i = 0; i < sleepCount; i++)
                Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException exc) {
            throw new IllegalArgumentException(exc);
        }
        //   3. Interrupt infThread
        System.out.printf("In %s interrupt 'infThread.interrupt()'...\n",
                blueBold("mainThread"));
        infThread.interrupt();
        //   4. Join infThread
        try {
            System.out.printf("In %s joining 'infThread.join()'...\n",
                blueBold("mainThread"));
            infThread.join();
        } catch (InterruptedException exc) {
            throw new IllegalStateException(exc);
        }
        //   5. Show termination and interruption
        System.out.printf("%s: state=%s interruption=%s\n",
                blueBold("infThread"), infThread.getState(), infThread.isInterrupted());
    }
    
}
