package com.github.druyaned.corejava.vol1.ch09.src;

import static com.github.druyaned.ConsoleColors.blueBold;
import static com.github.druyaned.ConsoleColors.bold;
import static com.github.druyaned.corejava.App.sin;
import com.github.druyaned.corejava.vol1.ch09.src.rbt.RedBlackTree;
import com.github.druyaned.corejava.vol1.ch09.src.rbt.Printer;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

public class TestRedBlackTree {
    
    public static void run() {
        comparisonTest();
        interactiveTest();
    }
    
    private static void interactiveTest() {
        System.out.println("\n" + bold("TestRedBlackTree.InteractiveTest"));
        RedBlackTree<Integer> tree = new RedBlackTree<>((i1, i2) -> i1.compareTo(i2));
        String menu =
                """
                    Menu description:
                        Add value:    \"a [INT_VAL]\"
                        Remove value: \"r [INT_VAL]\"
                        Quit:         \"q\"
                """;
        String inputPrompt = menu + blueBold("Input") + "(e.g. q): ";
        System.out.print(inputPrompt);
        String inputLine;
        while (!(inputLine = sin.nextLine()).equals("q")) {
            String[] parts = inputLine.split(" ");
            String command = parts[0];
            String output;
            try {
                Integer val = Integer.valueOf(parts[1]);
                switch (command) {
                    case "a" -> { output = Boolean.toString(tree.add(val)); }
                    case "r" -> { output = Boolean.toString(tree.remove(val)); }
                    default -> {
                        System.out.print(inputPrompt);
                        continue;
                    }
                }
            } catch (RuntimeException exc) {
                System.out.print(inputPrompt);
                continue;
            }
            System.out.println("output: " + output);
            System.out.println("tree.size=" + tree.size() + "; printing the tree...");
            Printer.print(tree, "_", System.out);
            System.out.print(inputPrompt);
        }
    }
    
    private static void comparisonTest() {
        System.out.println("\n" + bold("TestRedBlackTree.ComparisonTest"));
        String mistakeMessage = "mistake in the Red-Black Tree";
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
        long langTreeInsertionTime = 0L;
        long langTreeRemovalTime = 0L;
        long rbtInsertionTime = 0L;
        long rbtRemovalTime = 0L;
        Random random = new Random();
        for (int test = 0; test < testCount; test++) {
            int size = generateInteger(random, baseSize - sizeGap, baseSize + sizeGap);
            int[] values = new int[size];
            for (int i = 0; i < size; i++) {
                values[i] = generateInteger(random, -baseVal, baseVal);
            }
            TreeSet<Integer> langTree = new TreeSet<>((i1, i2) -> i1.compareTo(i2));
            RedBlackTree<Integer> rbt = new RedBlackTree<>((i1, i2) -> i1.compareTo(i2));
            long t1, t2;
            t1 = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                langTree.add(values[i]);
                totalInsertionCount++;
            }
            t2 = System.currentTimeMillis();
            langTreeInsertionTime += t2 - t1;
            t1 = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                rbt.add(values[i]);
                totalInsertionCount++;
            }
            t2 = System.currentTimeMillis();
            rbtInsertionTime += t2 - t1;
            checkTrees(rbt, langTree, mistakeMessage);
            shuffleArray(random, values, size);
            t1 = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                langTree.remove(values[i]);
                totalRemovalCount++;
            }
            t2 = System.currentTimeMillis();
            langTreeRemovalTime += t2 - t1;
            t1 = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                rbt.remove(values[i]);
                totalRemovalCount++;
            }
            t2 = System.currentTimeMillis();
            rbtRemovalTime += t2 - t1;
            checkTrees(rbt, langTree, mistakeMessage);
        }
        System.out.println("  totalInsertionCount=" + totalInsertionCount);
        System.out.println("  totalRemovalCount=" + totalRemovalCount);
        System.out.println("  langTreeInsertionTime=" + langTreeInsertionTime + "(ms)");
        System.out.println("  langTreeRemovalTime=" + langTreeRemovalTime + "(ms)");
        System.out.println("  rbtInsertionTime=" + rbtInsertionTime + "(ms)");
        System.out.println("  rbtRemovalTime=" + rbtRemovalTime + "(ms)");
        double insertionDifference = 100d * rbtInsertionTime / langTreeInsertionTime - 100d;
        double removalDifference = 100d * rbtRemovalTime / langTreeRemovalTime - 100d;
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
    
    private static void checkTrees(
            RedBlackTree<Integer> rbm, TreeSet<Integer> langTree,
            String mistakeMessage
    ) {
        checkSizes(rbm.size(), langTree.size(), mistakeMessage);
        Iterator<Integer> langIter = langTree.iterator();
        rbm.forEach(node -> {
            Integer rbVal = node.getValue();
            Integer langVal = langIter.next();
            if (!rbVal.equals(langVal)) {
                throw new IllegalStateException(
                        mistakeMessage +
                        ": rbVal=" + rbVal +
                        " langVal=" + langVal
                );
            }
        });
    }
    
    private static void checkSizes(int rbtSize, int langTreeSize, String mistakeMessage) {
        if (rbtSize != langTreeSize) {
            throw new IllegalStateException(
                    mistakeMessage +
                    ": rbTreeSize=" + rbtSize +
                    " langTreeSize=" + langTreeSize
            );
        }
    }
    
}
