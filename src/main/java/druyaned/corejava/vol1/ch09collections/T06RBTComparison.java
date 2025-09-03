package druyaned.corejava.vol1.ch09collections;

import static druyaned.ConsoleColors.bold;
import druyaned.corejava.vol1.ch09collections.t06rbt.RBT;
import druyaned.corejava.vol1.ch09collections.t06rbt.RBTIndexed;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

public class T06RBTComparison extends Topic {
    
    private final Random RANDOM = new Random();
    
    public T06RBTComparison(Chapter chapter) {
        super(chapter, 6);
    }
    
    @Override public String title() {
        return "Topic 06 RBTComparison";
    }
    
    @Override public void run() {
        System.out.println("\n" + bold("Comparison with many repetitions") + "...");
        int testCount = 8;
        int size = 1_000_000;
        int minVal = -1_000;
        int maxVal = +1_000;
        comparison(testCount, size, minVal, maxVal);
        System.out.println("\n" + bold("Comparison with few repetitions") + "...");
        testCount = 8;
        size = 500_000;
        minVal = -1_000_000_000;
        maxVal = +1_000_000_000;
        comparison(testCount, size, minVal, maxVal);
    }
    
    private void comparison(int testCount, int size, int minVal, int maxVal) {
        long treeSetAdditionTime = 0L;
        long rbtAdditionTime = 0L;
        long rbtIndAdditionTime = 0L;
        long treeSetRemovalTime = 0L;
        long rbtRemovalTime = 0L;
        long rbtIndRemovalTime = 0L;
        for (int test = 0; test < testCount; test++) {
            // addition
            int[] arr = new int[size];
            for (int i = 0; i < size; i++)
                arr[i] = generateInt(minVal, maxVal);
            TreeSet<Integer> treeSet = new TreeSet<>(Integer::compare);
            RBT<Integer> rbt = new RBT<>(Integer::compare);
            RBTIndexed rbtInd = new RBTIndexed();
            treeSetAdditionTime += additionTest(treeSet, arr);
            rbtAdditionTime += additionTest(rbt, arr);
            rbtIndAdditionTime += additionTest(rbtInd, arr);
            verify(treeSet, rbt, rbtInd);
            // removal
            shuffle(arr);
            treeSetRemovalTime += removalTest(treeSet, arr);
            rbtRemovalTime += removalTest(rbt, arr);
            rbtIndRemovalTime += removalTest(rbtInd, arr);
            verify(treeSet, rbt, rbtInd);
        }
        System.out.println("treeSetAdditionTime: " + treeSetAdditionTime);
        System.out.println("rbtAdditionTime:     " + rbtAdditionTime);
        System.out.println("rbtIndAdditionTime:  " + rbtIndAdditionTime);
        System.out.println("treeSetRemovalTime:  " + treeSetRemovalTime);
        System.out.println("rbtRemovalTime:      " + rbtRemovalTime);
        System.out.println("rbtIndRemovalTime:   " + rbtIndRemovalTime);
        double rbtAdditionDiff = (double)(rbtAdditionTime - treeSetAdditionTime)
                / treeSetAdditionTime * 100d;
        double rbtRemovalDiff = (double)(rbtRemovalTime - treeSetRemovalTime)
                / treeSetRemovalTime * 100d;
        double rbtIndAdditionDiff = (double)(rbtIndAdditionTime - treeSetAdditionTime)
                / treeSetAdditionTime * 100d;
        double rbtIndRemovalDiff = (double)(rbtIndRemovalTime - treeSetRemovalTime)
                / treeSetAdditionTime * 100d;
        System.out.printf("rbtAdditionDiff:    %+.2f%%\n", rbtAdditionDiff);
        System.out.printf("rbtRemovalDiff:     %+.2f%%\n", rbtRemovalDiff);
        System.out.printf("rbtIndAdditionDiff: %+.2f%%\n", rbtIndAdditionDiff);
        System.out.printf("rbtIndRemovalDiff:  %+.2f%%\n", rbtIndRemovalDiff);
    }
    
    private void verify(TreeSet<Integer> treeSet, RBT<Integer> rbt, RBTIndexed rbtInd) {
        int size = treeSet.size();
        int rbtSize = rbt.size();
        int rbtIndSize = rbtInd.size();
        if (size != rbtSize)
            throw new IllegalStateException("size=" + size + " rbtSize=" + rbtSize);
        if (size != rbtIndSize)
            throw new IllegalStateException("size=" + size + " IntrbtSize=" + rbtIndSize);
        ArrayList<Integer> list = new ArrayList<>(size);
        ArrayList<RBT.Node<Integer>> rbtList = new ArrayList<>(size);
        ArrayList<RBTIndexed.Node> rbtIndList = new ArrayList<>(size);
        treeSet.forEach(list::add);
        rbt.forEach(rbtList::add);
        rbtInd.forEach(rbtIndList::add);
        for (int i = 0; i < size; i++) {
            int val = list.get(i);
            int valRbt = rbtList.get(i).value();
            int valRbtInt = rbtIndList.get(i).value();
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
    
    private long additionTest(TreeSet<Integer> tree, int[] arr) {
        long start = System.currentTimeMillis();
        for (int num : arr)
            tree.add(num);
        long stop = System.currentTimeMillis();
        return stop - start;
    }
    
    private long additionTest(RBT<Integer> tree, int[] arr) {
        long start = System.currentTimeMillis();
        for (int num : arr)
            tree.add(num);
        long stop = System.currentTimeMillis();
        return stop - start;
    }
    
    private long additionTest(RBTIndexed tree, int[] arr) {
        long start = System.currentTimeMillis();
        for (int num : arr)
            tree.add(num);
        long stop = System.currentTimeMillis();
        return stop - start;
    }
    
    private long removalTest(TreeSet<Integer> tree, int[] arr) {
        long start = System.currentTimeMillis();
        for (int num : arr)
            tree.remove(num);
        long stop = System.currentTimeMillis();
        return stop - start;
    }
    
    private long removalTest(RBT<Integer> tree, int[] arr) {
        long start = System.currentTimeMillis();
        for (int num : arr)
            tree.remove(num);
        long stop = System.currentTimeMillis();
        return stop - start;
    }
    
    private long removalTest(RBTIndexed tree, int[] arr) {
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
