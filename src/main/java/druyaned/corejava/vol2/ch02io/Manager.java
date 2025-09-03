package druyaned.corejava.vol2.ch02io;

import java.io.Serializable;

/**
 * Simple class of a manager which has an additional states:
 * annual {@code bonus} in Russian rubles and a {@code secretary}
 * which is an {@link Employee employee}.
 * 
 * @author druyaned
 */
public class Manager extends Employee implements Serializable {
    
    private static final long serialVersionUID = 475865099776429932L;
    
    private double bonus = 0D;
    private Employee secretary = null;
    private transient String password = "0000";
    
    /**
     * Creates a new manager as an {@link Employee employee} with unset bonus and secretary.
     * @param name the name of the manager
     * @param salary the annual salary of the manager in Russian rubles
     */
    public Manager(String name, double salary) {
        super(name, salary);
    }
    
    /**
     * Returns the annual bonus of the manager in Russian rubles.
     * @return annual bonus of the manager in Russian rubles
     */
    public final double getBonus() {
        return bonus;
    }
    
    /**
     * Returns the secretary of the manager or {@code null} if there is no secretary.
     * @return secretary of the manager or {@code null} if there is no secretary
     */
    public final Employee getSecretary() {
        return secretary;
    }
    
    /**
     * Sets the annual bonus in Russian rubles.
     * @param bonus the annual bonus of the manager in Russian rubles
     * @return this instance to continue invoking methods
     */
    public final Manager setBonus(double bonus) {
        if (bonus < 0)
            throw new IllegalArgumentException("the bonus must be positive");
        this.bonus = bonus;
        return this;
    }
    
    /**
     * Sets the password.
     * @param pass a new password to be set
     */
    public void setPassword(String pass) {
        password = pass;
    }
    
    /**
     * Sets the secretary.
     * @param secretary the secretary of the manager to set
     * @return this instance to continue invoking methods
     */
    public final Manager setSecretary(Employee secretary) {
        this.secretary = secretary;
        return this;
    }
    
    /**
     * Checks the provided password for equality with the current password.<br>
     * <i>NOTE</i>: default password is {@code 0000}.
     * 
     * @param pass to be checked
     * @return {@code true} if the given password matches the actual one
     */
    public boolean matchPassword(String pass) {
        return password.equals(pass);
    }
    
    /**
     * Returns {@code true} if the secretary is not a {@code null}, otherwise {@code false}.
     * @return {@code true} if the secretary is not a {@code null}, otherwise {@code false}
     */
    public boolean hasSecretary() {
        return secretary != null;
    }
    
    /**
     * Returns {@code true} if the bonus is not a {@code 0}, otherwise {@code false}.
     * @return {@code true} if the bonus is not a {@code 0}, otherwise {@code false}
     */
    public boolean hasBonus() {
        return bonus != 0;
    }
    
    /**
     * Returns the annual salary plus bonus of the manager in Russian rubles.
     * @return annual salary plus bonus of the manager in Russian rubles
     */
    public double getTotalIncome() {
        return getSalary() + bonus;
    }
    
    @Override public String toString() {
        return "Maanger{name=" + getName() +
                ", salary=" + getSalary() +
                ", bonus=" + bonus +
                ", secretary=" + secretary +
                "}";
    }
    
}
