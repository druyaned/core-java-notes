package com.github.druyaned.horstmann.corejava.vol1.ch14.src.banks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import static com.github.druyaned.ConsoleColors.*;

public class BankFixed extends Bank {
    
    private final int[] accounts = getAccounts();
    // The fix is here
    private final ReentrantLock bankLock = new ReentrantLock();
    private final Condition sufficientFunds = bankLock.newCondition();
    
    @Override public void startTransfer() {
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
                    // The fix is here
                    bankLock.lock();
                    try {
                        transfer(from, to, value);
                        Thread.sleep(DELAY);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        bankLock.unlock();
                    }
                    printEndWhile();
                }
            };
            new Thread(target).start();
        }
    }

    @Override protected void transfer(int from, int to, int value) throws InterruptedException {
        printInTransfer("[IN_TRANSFER]", GREEN_BOLD, from, to, value);
        // The fix is here
        while (!getQuit() && accounts[from] < value) {
            printInTransfer("[BEFOR_AWAIT]", PURPLE_BOLD, from, to, value);
            sufficientFunds.await();
            printInTransfer("[AFTER_AWAIT]", PURPLE_BOLD, from, to, value);
        }
        if (getQuit()) {
            sufficientFunds.signalAll();
            printInTransfer("[OUT_TRANSFER_SIGNALED]", CYAN_BOLD, from, to, value);
            return;
        }
        System.out.printf(
                "%s; from=%d(%d); to=%d(%d); value=%d; ",
                Thread.currentThread(), from, accounts[from], to, accounts[to], value
        );
        accounts[from] -= value;
        accounts[to] += value;
        System.out.println(bold(String.format("totalBalance=%d", getTotalBalance())));
        // The fix is here
        sufficientFunds.signalAll();
        printInTransfer("[OUT_TRANSFER]", GREEN_BOLD, from, to, value);
    }
    
}
