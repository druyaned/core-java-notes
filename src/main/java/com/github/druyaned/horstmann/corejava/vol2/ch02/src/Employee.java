package com.github.druyaned.horstmann.corejava.vol2.ch02.src;

import java.io.Serializable;

/**
 * Simple class of an employee which has a {@code name} and
 * an annual {@code salary} in Russian rubles.
 * 
 * @author druyaned
 */
public class Employee implements Serializable {
    
    private static final long serialVersionUID = 1L; // version
    
    /** Max length of the employee's name. */
    public static final int MAX_NAME_LENGTH = 64;
    
    /** The size of an {@link Employee employee} in {@code bytes}. */
    public static final int DATA_SIZE = Employee.MAX_NAME_LENGTH * 2 + 8;
    
    public static void checkName(String name) {
        if (name == null) {
            throw new NullPointerException("name mustn't be a null");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    "name length mustn't be longer than " + MAX_NAME_LENGTH
            );
        }
    }
    
    private final String name;
    private double salary;
    
    /**
     * Creates a new employee with the {@code name} and
     * the annual {@code salary} in Russian rubles.
     * 
     * @param name the name of the employee
     * @param salary the annual salary of the employee in Russian rubles
     */
    public Employee(String name, double salary) {
        checkName(name);
        this.name = name;
        this.salary = salary;
    }
    
    /**
     * The name of the employee.
     * @return name of the employee
     */
    public final String getName() {
        return name;
    }
    
    /**
     * The annual salary of the employee in Russian rubles.
     * @return annual salary of the employee in Russian rubles
     */
    public final double getSalary() {
        return salary;
    }
    
    /**
     * Sets the annual salary in Russian rubles.
     * @param salary the annual salary of the employee to set in Russian rubles
     * @return this instance to continue invoking methods
     */
    public final Employee setSalary(double salary) {
        if (salary < 0)
            throw new IllegalArgumentException("the salary must be positive");
        this.salary = salary;
        return this;
    }
    
    @Override public String toString() {
        return "Employee{name=" + name + ", salary=" + salary + "}";
    }
    
}
