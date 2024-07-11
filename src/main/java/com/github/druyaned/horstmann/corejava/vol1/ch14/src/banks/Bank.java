package com.github.druyaned.horstmann.corejava.vol1.ch14.src.banks;

import java.util.Arrays;

import static com.github.druyaned.ConsoleColors.*;
import static com.github.druyaned.horstmann.corejava.App.sin;

public class Bank {

    public static final int INITIAL_BALANCE = 1000;
    public static final int AMOUNT_OF_ACCOUNTS = 5;
    public static final int DELAY = 100;
    
    private final int[] accounts;
    private volatile boolean quit = false; // stop indicator
    
    public Bank() {
        accounts = new int[AMOUNT_OF_ACCOUNTS];
        Arrays.fill(accounts, INITIAL_BALANCE);
    }

    protected boolean getQuit() {
        return quit;
    }

    protected int[] getAccounts() {
        return accounts;
    }

    protected void preStart() {
        System.out.println("To start type in \"Enter\". To stop do the same).");
        while (!sin.nextLine().isEmpty()) {}
        new Thread(() -> {
            while (!quit) {
                if (sin.nextLine().isEmpty()) {
                    quit = true;
                }
            }
        }).start();
    }
    
    public void startTransfer() {
        preStart();
        for (int i = 0; i < AMOUNT_OF_ACCOUNTS; ++i) {
            int from = i;
            Runnable target = () -> {
                int to;
                int value;
                while (!quit) {
                    to = (int)(AMOUNT_OF_ACCOUNTS * Math.random());
                    value = (int)(INITIAL_BALANCE * Math.random());
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
    
    protected void transfer(int from, int to, int value) throws InterruptedException {
        printInTransfer("[IN_TRANSFER]", GREEN_BOLD, from, to, value);
        if (accounts[from] < value) {
            printInTransfer("[OUT_TRANSFER_RETURNED]", CYAN_BOLD, from, to, value);
            return;
        }
        System.out.print(Thread.currentThread());
        accounts[from] -= value;
        System.out.printf("; from=%d; to=%d; value=%d; ", from, to, value);
        accounts[to] += value;
        System.out.println(bold(String.format("totalBalance=%d", getTotalBalance())));
        printInTransfer("[OUT_TRANSFER]", GREEN_BOLD, from, to, value);
    }

    protected int getTotalBalance() {
        int totalBalance = 0;
        for (int account : accounts) {
            totalBalance += account;
        }
        return totalBalance;
    }

    protected void printInTransfer(String s, String color, int from, int to, int value) {
        s = colored(s, color) + " " + Thread.currentThread();
        System.out.printf(
                "%s; from=%d(%d); to=%d(%d); value=%d\n",
                s, from, accounts[from], to, accounts[to], value
        );
    }

    protected void printBeginWhile() {
        System.out.println(
                colored("[BEGIN_WHILE]", RED_BOLD) + " " + Thread.currentThread() +
                " activeCount=" + bold(Integer.toString(Thread.activeCount()))
        );
    }

    protected void printEndWhile() {
        System.out.println(
                colored("[END_WHILE]", RED_BOLD) + " " + Thread.currentThread() +
                " activeCount=" + bold(Integer.toString(Thread.activeCount()))
        );
    }
    
}
