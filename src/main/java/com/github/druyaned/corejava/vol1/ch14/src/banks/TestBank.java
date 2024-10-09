package com.github.druyaned.corejava.vol1.ch14.src.banks;

import static com.github.druyaned.ConsoleColors.bold;

public class TestBank implements Runnable {
    
    @Override public void run() {
        Bank bank = new Bank();
        System.out.println(bold("Befor the start activeCount") + "=" + Thread.activeCount());
        bank.startTransfer();
        System.out.println(bold("At the end activeCount") + "=" + Thread.activeCount());
    }
    
}
