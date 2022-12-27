package com.github.druyaned.learn_java.vol1.chapter08;

import static com.github.druyaned.ConsoleColors.bold;
import com.github.druyaned.learn_java.vol1.chapter05.ClassAnalyzer;

public class GenericsTester {
    public static void run() {
        System.out.println("\n" + bold("Testing generics."));

        final int L = 8;
        final int MIN_WEIGHT = 3500;
        final int WEIGHT_SPREAD = 200;

        Truck[] rallyTrucks = new RallyTruck[L];
        for (int i = 0; i < L; ++i) {
            String brand = "RallyTruck" + (i + 1);
            int weight = MIN_WEIGHT + (int)(Math.random() * WEIGHT_SPREAD);
            int wheels = Math.random() < 0.5 ? 4 : 6;
            rallyTrucks[i] = new RallyTruck(brand, weight, wheels);
        }
        for (Car rallyTruck : rallyTrucks) {System.out.println(rallyTruck);}
        
        ArrAlg.Pair<Car> carsPair = ArrAlg.Pair.from(
            () -> new Car("FstBrand"),
            () -> new Car("SndBrand")
        );
        
        // way 1
        ArrAlg.<Truck>maxmin(rallyTrucks, carsPair);
        // way 2
        ArrAlg.minmax(rallyTrucks, carsPair);

        System.out.println("min: " + carsPair.getFst());
        System.out.println("max: " + carsPair.getSnd());

        ClassAnalyzer.showClass(ArrAlg.class);
        ClassAnalyzer.showClass(carsPair.getClass());
    }
}
