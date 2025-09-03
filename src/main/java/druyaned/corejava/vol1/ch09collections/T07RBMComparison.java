package druyaned.corejava.vol1.ch09collections;

import static druyaned.ConsoleColors.bold;
import druyaned.corejava.vol1.ch09collections.t07rbm.RBM;
import druyaned.corejava.vol1.ch09collections.t07rbm.RBMIndexed;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

public class T07RBMComparison extends Topic {
    
    private final Random RANDOM = new Random();
    private final String SOME_STRING = "some string";
    
    public T07RBMComparison(Chapter chapter) {
        super(chapter, 7);
    }
    
    @Override public String title() {
        return "Topic 07 RBMComparison";
    }
    
    @Override public void run() {
        System.out.println("\n" + bold("Comparison with many repetitions") + "...");
        int testCount = 8;
        int size = 1_000_000;
        int minVal = -1_000;
        int maxVal = +1_000;
        comparison(testCount, size, minVal, maxVal);
        System.out.println("\n" + bold("Comparison with few repetitions") + "...");
        testCount = 10;
        size = 500_000;
        minVal = -1_000_000_000;
        maxVal = +1_000_000_000;
        comparison(testCount, size, minVal, maxVal);
    }
    
    private void comparison(int testCount, int size, int minVal, int maxVal) {
        long treeMapAdditionTime = 0L;
        long rbmAdditionTime = 0L;
        long rbmIndAdditionTime = 0L;
        long treeMapRemovalTime = 0L;
        long rbmRemovalTime = 0L;
        long rbmIndRemovalTime = 0L;
        for (int test = 0; test < testCount; test++) {
            // addition
            int[] arr = new int[size];
            for (int i = 0; i < size; i++)
                arr[i] = generateInt(minVal, maxVal);
            TreeMap<Integer, String> treeMap = new TreeMap<>(Integer::compare);
            RBM<Integer, String> rbm = new RBM<>(Integer::compare);
            RBMIndexed rbmInd = new RBMIndexed(Integer::compare);
            treeMapAdditionTime += additionTest(treeMap, arr);
            rbmAdditionTime += additionTest(rbm, arr);
            rbmIndAdditionTime += additionTest(rbmInd, arr);
            verify(treeMap, rbm, rbmInd);
            // removal
            shuffle(arr);
            treeMapRemovalTime += removalTest(treeMap, arr);
            rbmRemovalTime += removalTest(rbm, arr);
            rbmIndRemovalTime += removalTest(rbmInd, arr);
            verify(treeMap, rbm, rbmInd);
        }
        System.out.println("treeMapAdditionTime: " + treeMapAdditionTime);
        System.out.println("rbmAdditionTime:     " + rbmAdditionTime);
        System.out.println("rbmIndAdditionTime:  " + rbmIndAdditionTime);
        System.out.println("treeMapRemovalTime:  " + treeMapRemovalTime);
        System.out.println("rbmRemovalTime:      " + rbmRemovalTime);
        System.out.println("rbmIndRemovalTime:   " + rbmIndRemovalTime);
        double rbmAdditionDiff = (double)(rbmAdditionTime - treeMapAdditionTime)
                / treeMapAdditionTime * 100d;
        double rbmRemovalDiff = (double)(rbmRemovalTime - treeMapRemovalTime)
                / treeMapRemovalTime * 100d;
        double rbmIndAdditionDiff = (double)(rbmIndAdditionTime - treeMapAdditionTime)
                / treeMapAdditionTime * 100d;
        double rbmIndRemovalDiff = (double)(rbmIndRemovalTime - treeMapRemovalTime)
                / treeMapAdditionTime * 100d;
        System.out.printf("rbmAdditionDiff:    %+.2f%%\n", rbmAdditionDiff);
        System.out.printf("rbmRemovalDiff:     %+.2f%%\n", rbmRemovalDiff);
        System.out.printf("rbmIndAdditionDiff: %+.2f%%\n", rbmIndAdditionDiff);
        System.out.printf("rbmIndRemovalDiff:  %+.2f%%\n", rbmIndRemovalDiff);
    }
    
    private void verify(
            TreeMap<Integer, String> treeMap,
            RBM<Integer, String> rbm,
            RBMIndexed rbmInd
    ) {
        int size = treeMap.size();
        int rbmSize = rbm.size();
        int rbmIndSize = rbmInd.size();
        if (size != rbmSize)
            throw new IllegalStateException("size=" + size + " rbmSize=" + rbmSize);
        if (size != rbmIndSize)
            throw new IllegalStateException("size=" + size + " IntrbmSize=" + rbmIndSize);
        ArrayList<Integer> list = new ArrayList<>(size);
        ArrayList<RBM.Node<Integer, String>> rbmList = new ArrayList<>(size);
        ArrayList<RBMIndexed.Node> rbmIndList = new ArrayList<>(size);
        treeMap.forEach((k, v) -> list.add(k));
        rbm.forEach(rbmList::add);
        rbmInd.forEach(rbmIndList::add);
        for (int i = 0; i < size; i++) {
            int val = list.get(i);
            int valRbt = rbmList.get(i).key();
            int valRbtInt = rbmIndList.get(i).key();
            if (val != valRbt) {
                throw new IllegalStateException("val=" + val + " valRbt=" + valRbt
                        + " i=" + i);
            }
            if (val != valRbtInt) {
                throw new IllegalStateException("val=" + val + " valRbtInt=" + valRbtInt
                        + " i=" + i);
            }
        }
    }
    
    private long additionTest(TreeMap<Integer, String> tree, int[] arr) {
        long start = System.currentTimeMillis();
        for (int num : arr)
            tree.put(num, SOME_STRING);
        long stop = System.currentTimeMillis();
        return stop - start;
    }
    
    private long additionTest(RBM<Integer, String> tree, int[] arr) {
        long start = System.currentTimeMillis();
        for (int num : arr)
            tree.put(num, SOME_STRING);
        long stop = System.currentTimeMillis();
        return stop - start;
    }
    
    private long additionTest(RBMIndexed tree, int[] arr) {
        long start = System.currentTimeMillis();
        for (int num : arr)
            tree.put(num, SOME_STRING);
        long stop = System.currentTimeMillis();
        return stop - start;
    }
    
    private long removalTest(TreeMap<Integer, String> tree, int[] arr) {
        long start = System.currentTimeMillis();
        for (int num : arr)
            tree.remove(num);
        long stop = System.currentTimeMillis();
        return stop - start;
    }
    
    private long removalTest(RBM<Integer, String> tree, int[] arr) {
        long start = System.currentTimeMillis();
        for (int num : arr)
            tree.remove(num);
        long stop = System.currentTimeMillis();
        return stop - start;
    }
    
    private long removalTest(RBMIndexed tree, int[] arr) {
        long start = System.currentTimeMillis();
        for (int num : arr)
            tree.remove(num);
        long stop = System.currentTimeMillis();
        return stop - start;
    }
    
    private int generateInt(int minVal, int maxVal) {
        return minVal + RANDOM.nextInt(maxVal - minVal + 1);
    }
    
    private void shuffle(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);
            int stock = array[i];
            array[i] = array[j];
            array[j] = stock;
        }
    }
    
}
