package druyaned.corejava.vol1.ch14.p03;

import java.util.Random;

public class BankTransfer {
    
    protected final BankAbstract bank;
    protected final Random random = new Random();
    
    public BankTransfer(BankAbstract bank) {
        this.bank = bank;
    }
    
    public void randomTransfer() {
        // Generate from, to and amount
        int amount = random.nextInt(BankAbstract.INIT_BALANCE + 1);
        int to = random.nextInt(BankAbstract.ACCOUNT_COUNT), from;
        do
            from = random.nextInt(BankAbstract.ACCOUNT_COUNT);
        while (from == to);
        // Transfer
        // Here will be a mistake because of the race condition
        //   if the bank is not synchronized
        bank.subtract(from, amount);
        bank.add(to, amount);
    }
    
}
