package com.github.druyaned.horstmann.corejava.vol1.ch09.src;

import static com.github.druyaned.ConsoleColors.bold;
import com.github.druyaned.horstmann.corejava.vol1.ch08.src.Truck;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class TestHashMapVsTreeMap {
    
    private static final Random random = new Random();
    
    private static String idGenerator() {
        final int SIZE = 8;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < SIZE; ++i) {
            if (random.nextBoolean()) {
                builder.append(random.nextInt(10));
            } else {
                char letter;
                if (random.nextBoolean()) {
                    letter = (char) ((int) 'A' + random.nextInt(26));
                } else {
                    letter = (char) ((int) 'a' + random.nextInt(26));
                }
                builder.append(letter);
            }
        }
        return builder.toString();
    }
    
    public static void run() {
        System.out.println("\n" + bold("TestHashMapVsTreeMap"));
        final int N = 800000;
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
        System.out.println("Spent time for putting into HashMap: " + spentTime + "(ms)");
        spentTime = System.currentTimeMillis();
        for (int i = 0; i < N; ++i) {
            treeMap.put(ids[i], trucks[i]);
        }
        spentTime = System.currentTimeMillis() - spentTime;
        System.out.println("Spent time for putting into TreeMap: " + spentTime + "(ms)");
        // Getting time
        spentTime = System.currentTimeMillis();
        for (int i = 0; i < N; ++i) {
            trucks[i] = hashMap.get(ids[i]);
        }
        spentTime = System.currentTimeMillis() - spentTime;
        System.out.println("Spent time for getting from HashMap: " + spentTime + "(ms)");
        spentTime = System.currentTimeMillis();
        for (int i = 0; i < N; ++i) {
            trucks[i] = treeMap.get(ids[i]);
        }
        spentTime = System.currentTimeMillis() - spentTime;
        System.out.println("Spent time for getting from TreeMap: " + spentTime + "(ms)");
    }
    
}
