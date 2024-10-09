package com.github.druyaned.corejava.vol1.ch06.src;

public class Person {
    
    private final String name;

    public Person() { name = "Jhon Smith"; }
    
    public Person(String name) { this.name = name; }

    public String getName() { return name; }
    
    // To test super::show
    protected void show(String message) { System.out.println(message); }

    public void show() { System.out.println("Name: \"" + name); }
    
}
