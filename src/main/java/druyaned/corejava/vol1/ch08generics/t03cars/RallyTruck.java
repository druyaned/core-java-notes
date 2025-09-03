package druyaned.corejava.vol1.ch08generics.t03cars;

public class RallyTruck extends Truck {
    
    private final int wheels;

    public RallyTruck(String brand, int weight, int wheels) {
        super(brand, weight);
        this.wheels = wheels;
    }

    public int getWheels() { return wheels; }

    @Override
    public String toString() {
        return "RallyTruck{brand=" + getBrand() +
                ", weight=" + getWieght() +
                ", wheels=" + wheels + "}";
    }
    
}
