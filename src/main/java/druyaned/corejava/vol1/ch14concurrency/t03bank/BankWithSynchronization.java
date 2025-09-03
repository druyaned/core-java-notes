package druyaned.corejava.vol1.ch14concurrency.t03bank;

public class BankWithSynchronization extends BankAbstract {
    
    @Override public synchronized int total() {
        int total = 0;
        for (int account : accounts)
            total += account;
        return total;
    }
    
    @Override public synchronized int account(int id) {
        return accounts[id];
    }
    
    @Override public synchronized void add(int id, int amount) {
        accounts[id] += amount;
    }
    
    @Override public synchronized void subtract(int id, int amount) {
        accounts[id] -= amount;
    }
    
}
