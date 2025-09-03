package druyaned.corejava.vol1.ch08generics;

import static druyaned.ConsoleColors.bold;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.vol1.ch08generics.t03cars.Car;
import druyaned.corejava.vol1.ch08generics.t03cars.RallyTruck;
import druyaned.corejava.vol1.ch08generics.t03cars.Truck;
import druyaned.corejava.vol1.ch08generics.t05badarray.DoubtfulGenericArray;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class T05DoubtfulGenericArray extends Topic {
    
    public T05DoubtfulGenericArray(Chapter chapter) {
        super(chapter, 5);
    }
    
    @Override public String title() {
        return "Topic 05 DoubtfulGenericArray";
    }
    
    @Override public void run() {
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
        for (ArrAlg.Pair<Car> carPair : carPairs)
            System.out.println(carPair);
        // The cause why generic arrays are unsafe
        Object[] objects = carPairs;
        objects[0] = ArrAlg.Pair.<String>from(
                () -> "Not the 1st car",
                () -> "Not the 2nd car"
        );
        System.out.println("\nObject[] objects = carsPairs");
        System.out.println("objects:");
        for (Object object : objects)
            System.out.println(object);
        System.out.println("\ncarsPairs:");
        for (ArrAlg.Pair<Car> carPair : carPairs)
            System.out.println(carPair);
        System.out.println("\nSo the carsPairs's type was " + bold("ArrAlg.Pair<Car>")
                + ".\nDuring initialization the type of the objects[0] was "
                + bold("ArrAlg.Pair<String>")
                + ".\nGeneric arrays are not too safe.");
    }
    
}
