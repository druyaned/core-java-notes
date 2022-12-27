package com.github.druyaned.learn_java.vol1.chapter14;

import static com.github.druyaned.ConsoleColors.bold;

public class TestBankQueued {
    public static void run() {
        BankQueued bank = new BankQueued();
        System.out.println(bold("Befor the start activeCount") + "=" + Thread.activeCount());
        bank.startTransfer();
        System.out.println(bold("At the end activeCount") + "=" + Thread.activeCount());
    }
}
