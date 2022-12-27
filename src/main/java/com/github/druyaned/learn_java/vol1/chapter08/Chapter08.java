package com.github.druyaned.learn_java.vol1.chapter08;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.Chapterable;

/**
 * Practice implementation of the chapter 8.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol1.Volume1
 */
public class Chapter08 implements Chapterable {

    @Override
    public void run() {
        System.out.println(bold("Running Chapter08: Generics"));
        
        BridgeMethodTester.run();
        GenericsTester.run();
        GenericsLimitationsTester.run();
        GenericArrayTester.run();
    }

    @Override
    public int getNumber() { return 8; }
    
    @Override
    public String getTitle() { return "Generics"; }
    
    @Override
    public boolean passed() { return true; }
}
/*
Pair<? extends Car>
    ^           ^
    |           |
Pair<Truck>   Pair<RullyTruck>


Pair<? extends Truck>
    ^           ^
    |           |
Pair<Truck>   Pair<RullyTruck>


   Pair<? super RullyTruck>
    ^           ^       ^
    |           |       |
Pair<Truck> Pair<Car>   Pair<RullyTruck>
*/
