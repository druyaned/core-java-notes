package com.github.druyaned.learn_java.vol1.chapter14;

import static com.github.druyaned.ConsoleColors.bold;

public class TestBankSynchronized {
    public static void run() {
        BankSynchronized bank = new BankSynchronized();
        System.out.println(bold("Befor the start activeCount") + "=" + Thread.activeCount());
        bank.startTransfer();
        System.out.println(bold("At the end activeCount") + "=" + Thread.activeCount());
    }
}
