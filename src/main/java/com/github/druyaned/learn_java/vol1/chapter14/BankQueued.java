package com.github.druyaned.learn_java.vol1.chapter14;

import static com.github.druyaned.ConsoleColors.*;

public class BankQueued extends Bank {
    private final TransferQueue queue = new TransferQueue(this);
    
    @Override
    public void startTransfer() {
        preStart();

        Runnable transferSupplier = () -> {
            for (int i = 0; i < AMOUNT_OF_ACCOUNTS; ++i) {
                int from = i;
                Runnable transactionAdder = () -> {
                    int to;
                    int value;
                    while (!getQuit()) {
                        to = (int) (AMOUNT_OF_ACCOUNTS * Math.random());
                        value = (int) (INITIAL_BALANCE * Math.random());
    
                        printBeginWhile(BLUE_BOLD);
    
                        queue.addLast(from, to, value);
                        try {
                            Thread.sleep(DELAY);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
    
                        printEndWhile(BLUE_BOLD);
                    }
                };
                new Thread(transactionAdder).start();
            }
        };
        Runnable transferConsumer = () -> {
            for (int i = 0; i < AMOUNT_OF_ACCOUNTS; ++i) {
                Runnable transferer = () -> {
                    while (!getQuit()) {
                        printBeginWhile(RED_BOLD);
    
                        try {
                            queue.toTransfer();
                            Thread.sleep(DELAY);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
    
                        printEndWhile(RED_BOLD);
                    }
                };
                new Thread(transferer).start();
            }
        };
        new Thread(transferSupplier).start();
        new Thread(transferConsumer).start();

        try {
            // To pause the main thread
            while (Thread.activeCount() > 1) { Thread.sleep(200); }
            // To make all remaining transactions
            while (queue.toTransfer()) { Thread.sleep(DELAY); }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void printBeginWhile(String color) {
        String s = colored("[BEGIN_WHILE]", color) +
                   " " + Thread.currentThread() +
                   " activeCount=" + bold(Integer.toString(Thread.activeCount()));
        System.out.println(s);
    }

    protected void printEndWhile(String color) {
        String s = colored("[END_WHILE]", color) +
                   " " + Thread.currentThread() +
                   " activeCount=" + bold(Integer.toString(Thread.activeCount()));
        System.out.println(s);
    }
}
