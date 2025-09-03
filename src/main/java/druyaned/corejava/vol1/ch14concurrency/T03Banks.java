package druyaned.corejava.vol1.ch14concurrency;

import druyaned.corejava.vol1.ch14concurrency.t03bank.BankAbstract;
import druyaned.corejava.vol1.ch14concurrency.t03bank.BankNoLocksOrSynchronization;
import druyaned.corejava.vol1.ch14concurrency.t03bank.BankTransfer;
import druyaned.corejava.vol1.ch14concurrency.t03bank.BankTransferPositive;
import druyaned.corejava.vol1.ch14concurrency.t03bank.BankWithLock;
import druyaned.corejava.vol1.ch14concurrency.t03bank.BankWithSynchronization;
import static druyaned.ConsoleColors.blueBold;
import static druyaned.ConsoleColors.bold;
import static druyaned.ConsoleColors.greenBold;
import static druyaned.ConsoleColors.redBold;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class T03Banks extends Topic {
    
    public T03Banks(Chapter chapter) {
        super(chapter, 3);
    }
    
    @Override public String title() {
        return "Topic 03 Banks";
    }
    
    @Override public void run() {
        BankAbstract bankWithNothing = new BankNoLocksOrSynchronization();
        BankAbstract bankWithLock = new BankWithLock();
        BankAbstract bankWithSynchronization = new BankWithSynchronization();
        // BankTransfer
        testBank(bankWithNothing, BankTransfer::new);
        testBank(bankWithLock, BankTransfer::new);
        testBank(bankWithSynchronization, BankTransfer::new);
        // BankTransferPositive
        bankWithNothing = new BankNoLocksOrSynchronization();
        testBank(bankWithNothing, BankTransferPositive::new);
    }
    
    private void testBank(BankAbstract bank, Function<BankAbstract, BankTransfer> newTransfer) {
        String message = "\n" + bold("Test Bank") + ": " + bank.getClass().getSimpleName();
        if (newTransfer.apply(bank) instanceof BankTransferPositive)
            message += " && BankTransferPositive";
        else
            message += " && BankTransfer";
        System.out.println(message);
        final int INIT_TOTAL = bank.total();
        final Thread[] threads = new Thread[THREAD_COUNT];
        AtomicInteger transferCount = new AtomicInteger(0);
        System.out.println("bank=" + bank);
        for (int i = 0; i < THREAD_COUNT; i++) {
            BankTransfer transfer = newTransfer.apply(bank);
            threads[i] = new Thread(() -> {
                for (int j = 0; j < THREAD_ITERATIONS; j++) {
                    transfer.randomTransfer();
                    transferCount.incrementAndGet();
                }
            });
            threads[i].start();
        }
        try {
            for (Thread thread : threads)
                thread.join();
        } catch (InterruptedException exc) {
            throw new IllegalStateException(exc);
        }
        System.out.println(blueBold("Transfering is completed") + ".");
        System.out.printf("bank.total()=%d; INIT_TOTAL=%d\n", bank.total(), INIT_TOTAL);
        if (bank.total() != INIT_TOTAL)
            System.out.println(redBold("Wrong Total Balance!"));
        else
            System.out.println(greenBold("Correct Total Balance"));
        System.out.println("THREAD_COUNT=" + THREAD_COUNT);
        System.out.println("THREAD_ITERATIONS=" + THREAD_ITERATIONS);
        System.out.println("transferCount=" + transferCount.get());
        System.out.println("bank=" + bank);
    }
    
    private static final int THREAD_COUNT = 15;
    private static final int THREAD_ITERATIONS = 4000;
    
}
