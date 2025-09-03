package druyaned.corejava.vol1.ch14concurrency.t03bank;

public abstract class BankAbstract {
    
    public static final int ACCOUNT_COUNT = 10;
    public static final int INIT_BALANCE = 100;
    
    protected final int[] accounts = new int[ACCOUNT_COUNT];
    
    public BankAbstract() {
        for (int id = 0; id < ACCOUNT_COUNT; id++)
            accounts[id] = INIT_BALANCE;
    }
    
    public abstract int total();
    
    public abstract int account(int id);
    
    public abstract void add(int id, int amount);
    
    public abstract void subtract(int id, int amount);
    
    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bank{");
        sb.append(accounts[0]);
        for (int i = 1; i < ACCOUNT_COUNT; i++)
            sb.append(", ").append(accounts[i]);
        sb.append('}');
        return sb.toString();
    }
    
}
