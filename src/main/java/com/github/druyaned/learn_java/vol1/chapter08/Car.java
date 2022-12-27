package com.github.druyaned.learn_java.vol1.chapter08;

public class Car {
    private String brand;
    
    public Car(String brand) {
        this.brand = brand;
    }

    public String getBrand() {return brand;}

    @Override
    public String toString() {
        return "[brand=" + getBrand() + "]";
    }
}
