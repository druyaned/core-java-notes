package com.github.druyaned.horstmann.corejava.vol1.ch07;

import static com.github.druyaned.ConsoleColors.bold;

public class TestTryCatchTime {
    
    private static final int ITERATIONS = 2000000;
    
    public static void run() {
        System.out.println("\n" + bold("TestTryCatchTime"));
        int count = 0;
        long prevTime = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; ++i) {
            try { // it's better to use "try" outside of the loop
                count += exceptionedFun(-i); // can be changed "-i" -> "i"
            } catch (Exception e) {
                count += 1;
            }
        }
        long spentTime = System.currentTimeMillis() - prevTime;
        System.out.println(
                "Spent time with try-catch: " + spentTime + " (mm);" + " count=" + count
        );
        count = 0;
        prevTime = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; ++i) {
            int d = noExceptionedFun(-i); // can be changed "-i" -> "i"
            if (d == -1) {
                count += 1;
            } else {
                count += d;
            }            
        }
        spentTime = System.currentTimeMillis() - prevTime;
        System.out.println(
                "Spent time with condition: " + spentTime + " (mm);" + " count=" + count
        );
    }

    public static int exceptionedFun(int n) throws Exception {
        if (n >= 0 && n < ITERATIONS) {
            return 1;
        }
        throw new Exception();
    }

    public static int noExceptionedFun(int n) {
        if (n >= 0 && n < ITERATIONS) {
            return 1;
        }
        return -1;
    }
    
}
