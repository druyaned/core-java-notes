package com.github.druyaned.learn_java.vol2.chapter02;

import java.io.Serializable;

/**
 * Simple class of a manager which has an additional states:
 * annual bonus in Russian rubles and a secretary which is an {@link Employee employee}.
 * 
 * @author druyaned
 */
public class Manager extends Employee implements Serializable {
    
    private double bonus = 0D;
    private Employee secretary = null;
    private transient String password = "0000";
    
    /**
     * Creates a new manager as an {@link Employee employee} with unset bonus and secretary.
     * 
     * @param name the name of the manager.
     * @param salary the annual salary of the manager in Russian rubles.
     */
    public Manager(String name, double salary) { super(name, salary); }

//-Getters-and-Setters------------------------------------------------------------------------------

    /** @return the annual bonus of the manager in Russian rubles. */
    public final double getBonus() { return bonus; }
    
    /** @return the secretary of the manager or {@code null} if there is no secretary. */
    public final Employee getSecretary() { return secretary; }
    
    /**
     * @param bonus the annual bonus of the manager in Russian rubles to set.
     * @return this instance to continue invoking methods.
     */
    public final Manager setBonus(double bonus) {
        if (bonus < 0)
            throw new IllegalArgumentException("the bonus must be positive");
        this.bonus = bonus;
        return this;
    }
    
    /** @param pass a new password to be set. */
    public void setPassword(String pass) { password = pass; }
    
    /**
     * @param secretary the secretary of the manager to set.
     * @return this instance to continue invoking methods.
     */
    public final Manager setSecretary(Employee secretary) {
        this.secretary = secretary;
        return this;
    }
    
//-Methods------------------------------------------------------------------------------------------
    
    /**
     * Checks the provided password for equality with the current password.
     * <i>NOTE</i>: default password is {@code 0000}.
     * 
     * @param pass
     * @return 
     */
    public boolean matchPassword(String pass) { return password.equals(pass); }
    
    /** @return {@code true} if the secretary is not a {@code null}, otherwise {@code false}. */
    public boolean hasSecretary() { return secretary != null; }
    
    /** @return {@code true} if the bonus is not a {@code 0}, otherwise {@code false}. */
    public boolean hasBonus() { return bonus != 0; }
    
    /** @return the annual salary plus bonus of the manager in Russian rubles. */
    public double getTotalIncome() { return getSalary() + bonus; }
    
    @Override
    public String toString() { return "[name=" + getName() +
                                      ", salary=" + getSalary() +
                                      ", bonus=" + bonus +
                                      ", secretary=" + secretary +
                                      "]"; }
}
