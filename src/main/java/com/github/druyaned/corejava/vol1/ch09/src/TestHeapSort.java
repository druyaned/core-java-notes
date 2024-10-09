package com.github.druyaned.corejava.vol1.ch09.src;

import static com.github.druyaned.ConsoleColors.bold;
import com.github.druyaned.corejava.vol1.ch09.src.heap.IntHeap;
import java.util.Arrays;
import java.util.Random;

public class TestHeapSort {
    
    public static void run() {
        System.out.println("\n" + bold("TestHeapSort"));
        Random random = new Random();
        final int SIZE_GAP = 400;
        final int MIN_SIZE = (int)1e7 - SIZE_GAP;
        final int MAX_SIZE = (int)1e7 + SIZE_GAP;
        int size = generateInteger(random, MIN_SIZE, MAX_SIZE);
        int minVal = -size;
        int maxVal = +size;
        int[] values = new int[size];
        for (int i = 0; i < size; i++) {
            values[i] = generateInteger(random, minVal, maxVal);
        }
        Object[] langResults = langSorting(size, values);
        long langTime = (Long)langResults[0];
        int[] langValues = (int[])langResults[1];
        Object[] heapResults = heapSorting(size, values);
        long heapTime = (Long)heapResults[0];
        int[] heapValues = (int[])heapResults[1];
        System.out.println("  size=" + size);
        System.out.println("  minVal=" + minVal);
        System.out.println("  maxVal=" + maxVal);
        checkArrays(size, langValues, heapValues);
        System.out.println("  langTime=" + langTime + "(ms)");
        System.out.println("  heapTime=" + heapTime + "(ms)");
        double difference = (double)heapTime / langTime * 100d - 100d;
        System.out.printf("  difference=%.2f%%\n", difference);
    }
    
    private static Object[] langSorting(int size, int[] values) {
        int[] sorted = new int[size];
        long t1 = System.currentTimeMillis();
        {   // sorting starts
            System.arraycopy(values, 0, sorted, 0, size);
            Arrays.sort(sorted, 0, size);
        } // sorting ends
        long t2 = System.currentTimeMillis();
        return new Object[] { t2 - t1, sorted };
    }
    
    private static Object[] heapSorting(int size, int[] values) {
        int[] sorted = new int[size];
        long t1 = System.currentTimeMillis();
        { // sorting starts
            IntHeap heap = new IntHeap(size);
            for (int i = 0; i < size; i++) {
                heap.add(values[i]);
            }
            for (int i = 0; i < size; i++) {
                sorted[i] = heap.remove();
            }
        } // sorting ends
        long t2 = System.currentTimeMillis();
        return new Object[] { t2 - t1, sorted };
    }
    
    private static void checkArrays(int size, int[] langValues, int[] heapValues) {
        for (int i = 0; i < size; i++) {
            if (langValues[i] != heapValues[i]) {
                throw new IllegalStateException(String.format(
                        "langValues[%d]=%d heapValues[%d]=%d\n",
                        i, langValues[i], i, heapValues[i]
                ));
            }
        }
    }
    
    private static int generateInteger(Random random, int minVal, int maxVal) {
        return minVal + random.nextInt(maxVal + 1 - minVal);
    }
    
}
