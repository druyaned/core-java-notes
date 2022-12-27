package com.github.druyaned.learn_java.vol1.chapter08;

public class RallyTruck extends Truck {
    private int wheels = 0;

    public RallyTruck(String brand, int weight, int wheels) {
        super(brand, weight);
        this.wheels = wheels;
    }

    public int getWheels() {return wheels;}

    @Override
    public String toString() {
        return "[brand=" + getBrand() +
               ", weight=" + getWieght() +
               ", wheels=" + wheels + "]";
    }
}
