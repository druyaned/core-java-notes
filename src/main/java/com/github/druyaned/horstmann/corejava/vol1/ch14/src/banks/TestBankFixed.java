package com.github.druyaned.horstmann.corejava.vol1.ch14.src.banks;

import static com.github.druyaned.ConsoleColors.bold;

public class TestBankFixed implements Runnable {
    
    @Override public void run() {
        BankFixed fixedBank = new BankFixed();
        System.out.println(bold("Befor the start activeCount") + "=" + Thread.activeCount());
        fixedBank.startTransfer();
        System.out.println(bold("At the end activeCount") + "=" + Thread.activeCount());
    }
    
}
