package com.github.druyaned.learn_java.vol1.chapter09;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.Chapterable;
import com.github.druyaned.learn_java.vol1.chapter08.Truck;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Practice implementation of the chapter 9.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol1.Volume1
 */
public class Chapter09 implements Chapterable {
    
    private static String idGenerator() {
        Random generator = new Random();
        final int L = 8;

        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < L; ++i) {
            if (generator.nextBoolean()) {
                // Digit
                sBuilder.append(generator.nextInt(10));
            } else {
                // Letter
                char letter;
                if (generator.nextBoolean()) {
                    // Uppercase
                    letter = (char) ((int) 'A' + generator.nextInt(26));
                } else {
                    // Lowercase
                    letter = (char) ((int) 'a' + generator.nextInt(26));
                }
                sBuilder.append(letter);
            }
        }

        return sBuilder.toString();
    }
    
//-Non-static---------------------------------------------------------------------------------------

    @Override
    public void run() {
        System.out.println(bold("Running Chapter09: Collections"));
        
        System.out.println("\n" + bold("Testing HashMap vs TreeMap."));

        final int N = 100000;
        System.out.println("N=" + N);
        String[] ids = new String[N];
        Truck[] trucks = new Truck[N];
        final int MIN_WEIGHT = 3500;
        final int WEIGHT_SPREAD = 300;
        
        // Initializing ids and trucks
        for (int i = 0; i < N; ++i) {
            ids[i] = idGenerator();
            
            String brand = "Brand" + i;
            int weight = MIN_WEIGHT + (int) (WEIGHT_SPREAD * Math.random());
            trucks[i] = new Truck(brand, weight);
        }

        Map<String, Truck> hashMap = new HashMap<>();
        Map<String, Truck> treeMap = new TreeMap<>();
        
        // Putting time
        long spentTime = System.currentTimeMillis();
        for (int i = 0; i < N; ++i) {
            hashMap.put(ids[i], trucks[i]);
        }
        spentTime = System.currentTimeMillis() - spentTime;
        System.out.println("\nSpent time for putting into HashMap T=" + spentTime);
        
        spentTime = System.currentTimeMillis();
        for (int i = 0; i < N; ++i) {
            treeMap.put(ids[i], trucks[i]);
        }
        spentTime = System.currentTimeMillis() - spentTime;
        System.out.println("Spent time for putting into TreeMap T=" + spentTime);

        // Getting time
        spentTime = System.currentTimeMillis();
        for (int i = 0; i < N; ++i) {
            trucks[i] = hashMap.get(ids[i]);
        }
        spentTime = System.currentTimeMillis() - spentTime;
        System.out.println("\nSpent time for getting from HashMap T=" + spentTime);
        
        spentTime = System.currentTimeMillis();
        for (int i = 0; i < N; ++i) {
            trucks[i] = treeMap.get(ids[i]);
        }
        spentTime = System.currentTimeMillis() - spentTime;
        System.out.println("Spent time for getting from TreeMap T=" + spentTime);
    }

    @Override
    public int getNumber() { return 9; }
    
    @Override
    public String getTitle() { return "Collections"; }
    
    @Override
    public boolean passed() { return true; }
}
