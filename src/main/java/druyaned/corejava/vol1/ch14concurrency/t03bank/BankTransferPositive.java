package druyaned.corejava.vol1.ch14concurrency.t03bank;

public class BankTransferPositive extends BankTransfer {
    
    public BankTransferPositive(BankAbstract bank) {
        super(bank);
    }
    
    @Override public void randomTransfer() {
        // Generate amount
        int amount = random.nextInt(BankAbstract.INIT_BALANCE + 1);
        // Generate from, to and complete transfer
        synchronized (bank) {
            // The bank must not have synchronization in it,
            //   cause the block gets the bank as a parameter,
            //   so there shouldn't be synchronized block
            //   in an other synchronized block
            int from, to;
            do {
                from = random.nextInt(BankAbstract.ACCOUNT_COUNT);
                to = random.nextInt(BankAbstract.ACCOUNT_COUNT);
            } while (from == to || bank.account(from) < amount);
            bank.subtract(from, amount);
            bank.add(to, amount);
        }
    }
    
}
