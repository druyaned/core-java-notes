package druyaned.corejava.vol1.ch08generics;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.vol1.ch08generics.t03cars.Car;

public class T04GenericsLimitations extends Topic {
    
    public T04GenericsLimitations(Chapter chapter) {
        super(chapter, 4);
    }
    
    @Override public String title() {
        return "Topic 04 GenericsLimitations";
    }
    
    @Override public void run() {
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
    
}
