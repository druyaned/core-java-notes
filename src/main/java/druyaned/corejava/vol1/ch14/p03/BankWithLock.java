package druyaned.corejava.vol1.ch14.p03;

import java.util.concurrent.locks.ReentrantLock;

public class BankWithLock extends BankAbstract {
    
    private final ReentrantLock LOCK = new ReentrantLock();
    
    @Override public int total() {
        LOCK.lock();
        try {
            int total = 0;
            for (int account : accounts)
                total += account;
            return total;
        } finally {
            LOCK.unlock();
        }
    }
    
    @Override public int account(int id) {
        LOCK.lock();
        try {
            return accounts[id];
        } finally {
            LOCK.unlock();
        }
    }
    
    @Override public void add(int id, int amount) {
        LOCK.lock();
        try {
            accounts[id] += amount;
        } finally {
            LOCK.unlock();
        }
    }
    
    @Override public void subtract(int id, int amount) {
        LOCK.lock();
        try {
            accounts[id] -= amount;
        } finally {
            LOCK.unlock();
        }
    }
    
}
