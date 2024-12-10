package druyaned.corejava.vol1.ch14.src.banks;

import static druyaned.ConsoleColors.bold;

public class TestBankQueued implements Runnable {
    
    @Override public void run() {
        BankQueued bank = new BankQueued();
        System.out.println(bold("Befor the start activeCount") + "=" + Thread.activeCount());
        bank.startTransfer();
        System.out.println(bold("At the end activeCount") + "=" + Thread.activeCount());
    }
    
}
