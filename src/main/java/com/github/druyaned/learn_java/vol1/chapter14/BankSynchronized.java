package com.github.druyaned.learn_java.vol1.chapter14;

import static com.github.druyaned.ConsoleColors.*;

public class BankSynchronized extends Bank {
    private final int[] accounts = getAccounts();
    
    @Override
    public void startTransfer() {
        preStart();
        
        for (int i = 0; i < AMOUNT_OF_ACCOUNTS; ++i) {
            int from = i;
            Runnable target = () -> {
                int to;
                int value;
                int energy = 1;
                while (energy != 0) {
                    if (getQuit()) { --energy; }

                    to = (int) (AMOUNT_OF_ACCOUNTS * Math.random());
                    value = (int) (INITIAL_BALANCE * Math.random());

                    printBeginWhile();

                    try {
                        transfer(from, to, value);
                        Thread.sleep(DELAY);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    printEndWhile();
                }
            };
            new Thread(target).start();
        }
    }

    /** Synchronized method. */
    @Override
    protected synchronized void transfer(int from, int to, int value) throws InterruptedException {
        printInTransfer("[IN_TRANSFER]", GREEN_BOLD, from, to, value);
        
        // The fix is here
        while (!getQuit() && accounts[from] < value) {
            printInTransfer("[BEFOR_WAIT]", PURPLE_BOLD, from, to, value);

            wait();
            
            printInTransfer("[AFTER_WAIT]", PURPLE_BOLD, from, to, value);
        }

        if (getQuit()) {
            notifyAll();

            printInTransfer("[OUT_TRANSFER_NOTIFIED]", CYAN_BOLD, from, to, value);

            return;
        }

        System.out.printf("%s; from=%d(%d); to=%d(%d); value=%d; ",
                   Thread.currentThread(), from, accounts[from], to, accounts[to], value);
        accounts[from] -= value;
        accounts[to] += value;
        System.out.println(bold(String.format("totalBalance=%d", getTotalBalance())));

        // The fix is here
        notifyAll();
        
        printInTransfer("[OUT_TRANSFER]", GREEN_BOLD, from, to, value);
    }
}
