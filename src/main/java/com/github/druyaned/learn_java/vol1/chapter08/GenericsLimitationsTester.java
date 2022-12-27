package com.github.druyaned.learn_java.vol1.chapter08;

import static com.github.druyaned.ConsoleColors.bold;
import java.util.function.Supplier;

public class GenericsLimitationsTester {
    public static void run() {
        testGenericsLimitations();
        testDoubtfulGenericArray();
    }

    public static void testGenericsLimitations() {
        System.out.println("\n" + bold("Testing generic limitations."));

        ArrAlg.Pair<String> stringsPair = ArrAlg.Pair.from(String::new, String::new);
        ArrAlg.Pair<Car> carsPair = ArrAlg.Pair.from(
            () -> new Car("FstCarBrand"),
            () -> new Car("SndCarBrand")
        );

        boolean cnd = stringsPair.getClass() == carsPair.getClass();
        System.out.println("[stringsPair.getClass() == carsPair.getClass()]=" + cnd);

        cnd = stringsPair instanceof ArrAlg.Pair;
        System.out.println("[stringsPair instanceof ArrAlg.Pair]=" + cnd);

        cnd = carsPair instanceof ArrAlg.Pair;
        System.out.println("[carsPair instanceof ArrAlg.Pair]=" + cnd);
    }

    public static void testDoubtfulGenericArray() {
        System.out.println("\n" + bold("Testing DoubtfulGenericArray."));
        // Bad way
        Supplier<Car>[] unsafeCarsConstrs = DoubtfulGenericArray.make(
            () -> new Truck("FstTruckBrand", 3600),
            () -> new Truck("SndTruckBrand", 3600),
            () -> new Car("FstCarBrand"),
            () -> new Car("SndCarBrand")
        );
        // Good way
        GenericArray<Supplier<Car>> goodCarsConstrs = GenericArray.from(
            () -> new RallyTruck("RockRally1", 3501, 6),
            () -> new RallyTruck("RockRally2", 3501, 6),
            () -> new Truck("SmithsTruck1", 3729),
            () -> new Truck("SmithsTruck2", 3729)
        );

        ArrAlg.Pair<Car>[] carsPairs = DoubtfulGenericArray.make(
            ArrAlg.Pair.from(unsafeCarsConstrs[0], unsafeCarsConstrs[1]),
            ArrAlg.Pair.from(unsafeCarsConstrs[2], unsafeCarsConstrs[3]),
            ArrAlg.Pair.from(goodCarsConstrs.get(0), goodCarsConstrs.get(1)),
            ArrAlg.Pair.from(goodCarsConstrs.get(2), goodCarsConstrs.get(3))
        );
        System.out.println("\ncarsPairs:");
        for (ArrAlg.Pair<Car> carPair : carsPairs) {
            System.out.println(carPair);
        }

        // The cause why generic arrays are unsafe
        Object[] objects = carsPairs;
        try {
            objects[0] = ArrAlg.Pair.from(() -> "Not a 1st car", () -> "Not a 2nd car");
            System.out.println("\nObject[] objects = carsPairs");
            System.out.println("objects:");
            for (Object object : objects) {
                System.out.println(object);
            }
            System.out.println("\ncarsPairs:");
            for (ArrAlg.Pair<Car> carPair : carsPairs) {
                System.out.println(carPair);
            }
            System.out.println(
                "\nSo a carsPairs's type was " + bold("ArrAlg.Pair<Car>") +
                ".\nDuring initialization the type of an objects[0] was " +
                bold("ArrAlg.Pair<String>") + ".\nGeneric arrays are not too safe."
            );
        } catch (ArrayStoreException e) {
            e.printStackTrace();
        }
    }
}
