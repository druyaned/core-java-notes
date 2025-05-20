package druyaned.corejava.vol1.ch14.p03;

public class BankNoLocksOrSynchronization extends BankAbstract {
    
    @Override public int total() {
        int total = 0;
        for (int account : accounts)
            total += account;
        return total;
    }
    
    @Override public int account(int id) {
        return accounts[id];
    }
    
    @Override public void add(int id, int amount) {
        accounts[id] += amount;
    }
    
    @Override public void subtract(int id, int amount) {
        accounts[id] -= amount;
    }
    
}
