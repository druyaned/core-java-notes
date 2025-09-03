package druyaned.corejava.vol1.ch08generics.t03cars;

public class Truck extends Car implements Comparable<Truck>, MaxSpeedable {
    
    private final int weight;

    public Truck(String brand, int weight) {
        super(brand);
        this.weight = weight;
    }

    public int getWieght() { return this.weight; }

    @Override public int compareTo(Truck anotherTruck) {
        return Integer.compare(weight, anotherTruck.weight);
    }

    @Override public int getMaxSpeed() { return 250; }

    @Override public String toString() {
        return "Truck{brand=" + getBrand() + ", weight=" + weight + "}";
    }
    
}
