package com.github.druyaned.learn_java.vol1.chapter14;

import static com.github.druyaned.ConsoleColors.bold;

public class TestBankFixed {
    public static void run() {
        BankFixed fixedBank = new BankFixed();
        System.out.println(bold("Befor the start activeCount") + "=" + Thread.activeCount());
        fixedBank.startTransfer();
        System.out.println(bold("At the end activeCount") + "=" + Thread.activeCount());
    }
}
