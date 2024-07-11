package com.github.druyaned.horstmann.corejava.vol1.ch09.src;

import static com.github.druyaned.ConsoleColors.bold;
import com.github.druyaned.horstmann.corejava.vol1.ch09.src.rbm.Node;
import com.github.druyaned.horstmann.corejava.vol1.ch09.src.rbm.RedBlackMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class TestRedBlackMap {
    
    public static void comparisonTest() {
        System.out.println("\n" + bold("TestRedBlackMap.ComparisonTest"));
        String mistakeMessage = "mistake in the Red-Black Map";
        System.out.println(bold("ComparisonWithRepetitions") + "...");
        int testCount = 4;
        int baseSize = (int)5e6;
        int sizeGap = 200;
        int baseVal = (int)1e3;
        comparison(testCount, baseSize, sizeGap, baseVal, mistakeMessage);
        System.out.println(bold("ComparisonWithBigValues") + "...");
        testCount = 8;
        baseSize = (int)5e5;
        sizeGap = 200;
        baseVal = (int)1e9;
        comparison(testCount, baseSize, sizeGap, baseVal, mistakeMessage);
    }
    
    private static void comparison(
            int testCount, int baseSize, int sizeGap, int baseVal, String mistakeMessage
    ) {
        long totalInsertionCount = 0L;
        long totalRemovalCount = 0L;
        long langMapInsertionTime = 0L;
        long langMapRemovalTime = 0L;
        long rbtInsertionTime = 0L;
        long rbtRemovalTime = 0L;
        Random random = new Random();
        for (int test = 0; test < testCount; test++) {
            int size = generateInteger(random, baseSize - sizeGap, baseSize + sizeGap);
            int[] values = new int[size];
            for (int i = 0; i < size; i++) {
                values[i] = generateInteger(random, -baseVal, baseVal);
            }
            TreeMap<Integer, Integer> langMap = new TreeMap<>((i1, i2) -> i1.compareTo(i2));
            RedBlackMap<Integer, Integer> rbm = new RedBlackMap<>((i1, i2) -> i1.compareTo(i2));
            long t1, t2;
            t1 = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                langMap.put(values[i], values[i]);
                totalInsertionCount++;
            }
            t2 = System.currentTimeMillis();
            langMapInsertionTime += t2 - t1;
            t1 = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                rbm.put(values[i], values[i]);
                totalInsertionCount++;
            }
            t2 = System.currentTimeMillis();
            rbtInsertionTime += t2 - t1;
            checkMaps(rbm, langMap, mistakeMessage);
            shuffleArray(random, values, size);
            t1 = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                langMap.remove(values[i]);
                totalRemovalCount++;
            }
            t2 = System.currentTimeMillis();
            langMapRemovalTime += t2 - t1;
            t1 = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                rbm.remove(values[i]);
                totalRemovalCount++;
            }
            t2 = System.currentTimeMillis();
            rbtRemovalTime += t2 - t1;
            checkMaps(rbm, langMap, mistakeMessage);
        }
        System.out.println("  totalInsertionCount=" + totalInsertionCount);
        System.out.println("  totalRemovalCount=" + totalRemovalCount);
        System.out.println("  langMapInsertionTime=" + langMapInsertionTime + "(ms)");
        System.out.println("  langMapRemovalTime=" + langMapRemovalTime + "(ms)");
        System.out.println("  rbtInsertionTime=" + rbtInsertionTime + "(ms)");
        System.out.println("  rbtRemovalTime=" + rbtRemovalTime + "(ms)");
        double insertionDifference = 100d * rbtInsertionTime / langMapInsertionTime - 100d;
        double removalDifference = 100d * rbtRemovalTime / langMapRemovalTime - 100d;
        System.out.printf("  insertionDifference: %.2f%%\n", insertionDifference);
        System.out.printf("  removalDifference: %.2f%%\n", removalDifference);
    }
    
    private static int generateInteger(Random random, int minVal, int maxVal) {
        return minVal + random.nextInt(maxVal + 1 - minVal);
    }
    
    private static void shuffleArray(Random random, int[] array, int size) {
        for (int i = size - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            swap(array, i, j);
        }
    }
    
    private static void swap(int[] array, int i, int j) {
        int stock = array[i];
        array[i] = array[j];
        array[j] = stock;
    }
    
    private static void checkMaps(
            RedBlackMap<Integer, Integer> rbm, TreeMap<Integer, Integer> langMap,
            String mistakeMessage
    ) {
        checkSizes(rbm.size(), langMap.size(), mistakeMessage);
        List<Node<Integer, Integer>> rbmNodes = new ArrayList<>(rbm.size());
        List<Map.Entry<Integer, Integer>> langEntries = new ArrayList<>(rbm.size());
        for (Map.Entry<Integer, Integer> entry : langMap.entrySet()) {
            langEntries.add(entry);
        }
        rbm.forEach(node -> rbmNodes.add(node));
        for (int i = 0; i < rbm.size(); i++) {
            Node<Integer, Integer> n = rbmNodes.get(i);
            Map.Entry<Integer, Integer> e = langEntries.get(i);
            if (!n.getKey().equals(e.getKey()) || !n.getValue().equals(e.getValue())) {
                throw new IllegalStateException(
                        mistakeMessage +
                        ": rbKey=" + n.getKey() +
                        " langKey=" + e.getKey() +
                        " rbVal=" + n.getValue() +
                        " langVal=" + e.getValue() +
                        " index=" + i
                );
            }
        }
    }
    
    private static void checkSizes(int rbmSize, int langMapSize, String mistakeMessage) {
        if (rbmSize != langMapSize) {
            throw new IllegalStateException(
                    mistakeMessage +
                    ": rbmSize=" + rbmSize +
                    " langMapSize=" + langMapSize
            );
        }
    }
    
}
