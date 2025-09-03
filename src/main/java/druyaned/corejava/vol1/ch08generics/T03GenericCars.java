package druyaned.corejava.vol1.ch08generics;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.vol1.ch05inherit.ClassAnalyzer;
import druyaned.corejava.vol1.ch08generics.t03cars.Car;
import druyaned.corejava.vol1.ch08generics.t03cars.RallyTruck;
import druyaned.corejava.vol1.ch08generics.t03cars.Truck;

/**
 * <pre>
 *     Pair&lt;? extends Car&gt;
 *        |          |
 * Pair&lt;Truck&gt;    Pair&lt;RullyTruck&gt;
 * 
 *   Pair&lt;? extends Truck&gt;
 *        |         |
 * Pair&lt;Truck&gt;   Pair&lt;RullyTruck&gt;
 * 
 *        Pair&lt;? super RullyTruck&gt;
 *        |         |           |
 * Pair&lt;Car&gt;    Pair&lt;Truck&gt;    Pair&lt;RullyTruck&gt;
 * </pre>
 * 
 * @author druyaned
 */
public class T03GenericCars extends Topic {

    public T03GenericCars(Chapter chapter) {
        super(chapter, 3);
    }
    
    @Override public String title() {
        return "Topic 03 GenericCars";
    }
    
    @Override public void run() {
        final int SIZE = 8;
        final int MIN_WEIGHT = 3500;
        final int WEIGHT_SPREAD = 200;
        Truck[] trucks = new Truck[SIZE];
        for (int i = 0; i < SIZE; ++i) {
            int weight = MIN_WEIGHT + (int)(Math.random() * WEIGHT_SPREAD);
            if (Math.random() < 0.5) {
                String brand = "T" + (i + 1);
                trucks[i] = new Truck(brand, weight);
            } else {
                String brand = "RT" + (i + 1);
                int wheels = Math.random() < 0.5 ? 4 : 6;
                trucks[i] = new RallyTruck(brand, weight, wheels);
            }
        }
        for (Car rallyTruck : trucks) { System.out.println(rallyTruck); }
        ArrAlg.Pair<Car> carsPair = ArrAlg.Pair.from(
                () -> new Car("FstBrand"),
                () -> new Car("SndBrand")
        );
        // way 1
        ArrAlg.<Truck>maxmin(trucks, carsPair);
        // way 2
        ArrAlg.minmax(trucks, carsPair);
        System.out.println("min: " + carsPair.getFst());
        System.out.println("max: " + carsPair.getSnd());
        ClassAnalyzer.showClass(ArrAlg.class);
        ClassAnalyzer.showClass(carsPair.getClass());
    }
    
}
