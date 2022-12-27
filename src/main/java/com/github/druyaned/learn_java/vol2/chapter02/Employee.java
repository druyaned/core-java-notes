package com.github.druyaned.learn_java.vol2.chapter02;

import java.io.Serializable;

/**
 * Simple class of an employee which has a name and an annual salary in Russian rubles.
 * 
 * @author druyaned
 */
public class Employee implements Serializable {
    
    /** A size limit for name of the employee. */
    public static final int NAME_SIZE = 64;
    
    private final String name;
    private double salary;
    
    /**
     * Creates a new employee with the name and the annual salary in Russian rubles.
     * 
     * @param name the name of the employee.
     * @param salary the annual salary of the employee in Russian rubles.
     */
    public Employee(String name, double salary) {
        if (name == null)
            throw new NullPointerException("name mustn't be a null");
        if (name.length() > NAME_SIZE)
            throw new IllegalArgumentException("name length mustn't be longer than " + NAME_SIZE);
        this.name = name;
        this.salary = salary;
    }
    
//-Getters-and-Setters------------------------------------------------------------------------------

    /** @return the name of the employee. */
    public final String getName() { return name; }

    /** @return the annual salary of the employee in Russian rubles. */
    public final double getSalary() { return salary; }

    /**
     * @param salary the annual salary of the employee to set in Russian rubles.
     * @return this instance to continue invoking methods.
     */
    public final Employee setSalary(double salary) {
        if (salary < 0)
            throw new IllegalArgumentException("the salary must be positive");
        this.salary = salary;
        return this;
    }
    
//-Methods------------------------------------------------------------------------------------------
    
    @Override
    public String toString() {
        return "[name=" + name + ", salary=" + salary + "]";
    }
}
