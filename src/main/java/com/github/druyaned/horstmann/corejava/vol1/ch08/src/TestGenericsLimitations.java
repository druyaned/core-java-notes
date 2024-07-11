package com.github.druyaned.horstmann.corejava.vol1.ch08.src;

import static com.github.druyaned.ConsoleColors.bold;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class TestGenericsLimitations {
    
    public static void run() {
        testGenericsLimitations();
        testDoubtfulGenericArray();
    }

    public static void testGenericsLimitations() {
        System.out.println("\n" + bold("testGenericsLimitations"));
        ArrAlg.Pair<String> stringsPair = ArrAlg.Pair.from(String::new, String::new);
        ArrAlg.Pair<Car> carsPair = ArrAlg.Pair.from(
            () -> new Car("FstCarBrand"),
            () -> new Car("SndCarBrand")
        );
        boolean cnd = stringsPair.getClass() == carsPair.getClass();
        System.out.println("{ stringsPair.getClass() == carsPair.getClass() } = " + cnd);
        cnd = stringsPair instanceof ArrAlg.Pair;
        System.out.println("{ stringsPair instanceof ArrAlg.Pair } = " + cnd);
        cnd = carsPair instanceof ArrAlg.Pair;
        System.out.println("{ carsPair instanceof ArrAlg.Pair } = " + cnd);
    }

    public static void testDoubtfulGenericArray() {
        System.out.println("\n" + bold("testDoubtfulGenericArray"));
        // Bad way
        Supplier<Car>[] unsafeCarsConstrs = DoubtfulGenericArray.make(
            () -> new Truck("FstTruckBrand", 3600),
            () -> new Truck("SndTruckBrand", 3600),
            () -> new Car("FstCarBrand"),
            () -> new Car("SndCarBrand")
        );
        // Good way
        List<Supplier<Car>> goodCarsConstrs = new ArrayList<>();
        goodCarsConstrs.add(() -> new RallyTruck("RockRally1", 3501, 6));
        goodCarsConstrs.add(() -> new RallyTruck("RockRally2", 3501, 6));
        goodCarsConstrs.add(() -> new Truck("SmithsTruck1", 3729));
        goodCarsConstrs.add(() -> new Truck("SmithsTruck2", 3729));
        ArrAlg.Pair<Car>[] carPairs = DoubtfulGenericArray.make(
            ArrAlg.Pair.from(unsafeCarsConstrs[0], unsafeCarsConstrs[1]),
            ArrAlg.Pair.from(unsafeCarsConstrs[2], unsafeCarsConstrs[3]),
            ArrAlg.Pair.from(goodCarsConstrs.get(0), goodCarsConstrs.get(1)),
            ArrAlg.Pair.from(goodCarsConstrs.get(2), goodCarsConstrs.get(3))
        );
        System.out.println("\ncarPairs:");
        for (ArrAlg.Pair<Car> carPair : carPairs) {
            System.out.println(carPair);
        }
        // The cause why generic arrays are unsafe
        Object[] objects = carPairs;
        objects[0] = ArrAlg.Pair.from(() -> "Not the 1st car", () -> "Not the 2nd car");
        System.out.println("\nObject[] objects = carsPairs");
        System.out.println("objects:");
        for (Object object : objects) {
            System.out.println(object);
        }
        System.out.println("\ncarsPairs:");
        for (ArrAlg.Pair<Car> carPair : carPairs) {
            System.out.println(carPair);
        }
        System.out.println(
                "\nSo the carsPairs's type was " + bold("ArrAlg.Pair<Car>") +
                ".\nDuring initialization the type of the objects[0] was " +
                bold("ArrAlg.Pair<String>") + ".\nGeneric arrays are not too safe."
        );
    }
    
}
