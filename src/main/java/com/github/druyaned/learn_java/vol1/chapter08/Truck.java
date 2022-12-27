package com.github.druyaned.learn_java.vol1.chapter08;

public class Truck extends Car implements Comparable<Truck>, MaxSpeedable {
    private int weight;

    public Truck(String brand, int weight) {
        super(brand);
        this.weight = weight;
    }

    public int getWieght() {return this.weight;}

    @Override
    public int compareTo(Truck anotherTruck) {
        return Integer.compare(weight, anotherTruck.weight);
    }

    @Override
    public int getMaxSpeed() {
        return 250;
    }

    @Override
    public String toString() {
        return "[brand=" + getBrand() + ", weight=" + weight + "]";
    }
}
