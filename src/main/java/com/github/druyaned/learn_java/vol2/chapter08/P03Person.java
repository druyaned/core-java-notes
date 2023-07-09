package com.github.druyaned.learn_java.vol2.chapter08;

/**
 * This simple class of a person helps to verify the work of the
 * {@link P03Gettered} annotation interface.
 * 
 * @author druyaned
 */
@P03Gettered
public class P03Person {
    
    private String name;
    private int age;

    public P03Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
