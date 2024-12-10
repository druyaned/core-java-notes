package druyaned.corejava.vol1.ch08.src;

import static druyaned.ConsoleColors.bold;
import druyaned.corejava.vol1.ch05.ClassAnalyzer;

/**
 * <pre>
 *     Pair&lt;? extends Car>
 *        |          |
 * Pair&lt;Truck>    Pair&lt;RullyTruck>
 * 
 *   Pair&lt;? extends Truck>
 *        |         |
 * Pair&lt;Truck>   Pair&lt;RullyTruck>
 * 
 *        Pair&lt;? super RullyTruck>
 *        |         |           |
 * Pair&lt;Car>    Pair&lt;Truck>    Pair&lt;RullyTruck>
 * </pre>
 * 
 * @author druyaned
 */
public class TestGenericCars {
    
    public static void run() {
        System.out.println("\n" + bold("TestGenericCars"));
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
