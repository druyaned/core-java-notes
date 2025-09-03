package druyaned.corejava.vol1.ch09collections;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.vol1.ch08generics.t03cars.Truck;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class T02HashMapVsTreeMap extends Topic {
    
    private final Random RANDOM = new Random();
    
    public T02HashMapVsTreeMap(Chapter chapter) {
        super(chapter, 2);
    }
    
    @Override public String title() {
        return "Topic 02 HashMapVsTreeMap";
    }
    
    @Override public void run() {
        final int N = 800000;
        System.out.println("N=" + N);
        String[] ids = new String[N];
        Truck[] trucks = new Truck[N];
        final int MIN_WEIGHT = 3500;
        final int WEIGHT_SPREAD = 300;
        // Initializing ids and trucks
        for (int i = 0; i < N; ++i) {
            ids[i] = generateId();
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
    
    private String generateId() {
        final int SIZE = 8;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < SIZE; ++i) {
            if (RANDOM.nextBoolean()) {
                builder.append(RANDOM.nextInt(10));
            } else {
                char letter = RANDOM.nextBoolean()
                        ? (char)((int)'A' + RANDOM.nextInt(26))
                        : (char)((int)'a' + RANDOM.nextInt(26));
                builder.append(letter);
            }
        }
        return builder.toString();
    }
    
}
