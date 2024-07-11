package com.github.druyaned.horstmann.corejava.vol1.ch14.src.banks;

import static com.github.druyaned.ConsoleColors.bold;

public class TestBankSynchronized implements Runnable {
    
    @Override public void run() {
        BankSynchronized bank = new BankSynchronized();
        System.out.println(bold("Befor the start activeCount") + "=" + Thread.activeCount());
        bank.startTransfer();
        System.out.println(bold("At the end activeCount") + "=" + Thread.activeCount());
    }
    
}
