package com.github.druyaned.learn_java.vol1.chapter07;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.Chapterable;

/**
 * Practice implementation of the chapter 7.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol1.Volume1
 */
public class Chapter07 implements Chapterable {

    @Override
    public void run() {
        System.out.println(bold("Running Chapter07: Exceptions, Assertions, and Logging"));
        
        StackTraceTester.run();
        ExceptionTimeTester.run();
        AssertionsTester.run();
    }

    @Override
    public int getNumber() { return 7; }
    
    @Override
    public String getTitle() { return "Exceptions, Assertions, and Logging"; }
    
    @Override
    public boolean passed() { return true; }
}

class StackTraceTester {
    public static void run() {
        System.out.println("\n" + bold("Testing stack trace."));
        
        long num = 5;
        System.out.printf("factorial(%d)=%d\n", num, factorial(num));
    }

    public static long factorial(long num) {
        System.out.printf("In %s; stack trace:\n", bold("factorial(" + num + ")"));
        Throwable t = new Throwable();
        StackTraceElement[] frames = t.getStackTrace();
        for (StackTraceElement frame : frames) {
            System.out.println(frame);
        }

        if (num == 1) {
            return num;
        }
        long midterm = num * factorial(num - 1);
        return midterm;
    }
}

class ExceptionTimeTester {
    private static final int ITERATIONS = 2000000;

    public static void run() {
        System.out.println("\n" + bold("Testing try-catch time."));

        int count;

        count = 0;
        long prevTime = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; ++i) {
            try { // it's better to use "try" outside of the loop
                count += exceptionedFun(-i); // can be changed "-i" -> "i"
            } catch (Exception e) {
                count += 1;
            }
        }
        long spentTime = System.currentTimeMillis() - prevTime;
        System.out.println("Spent time with try-catch: " + spentTime + " (mm);" +
                           " count=" + count);

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
        System.out.println("Spent time with condition: " + spentTime + " (mm);" +
                           " count=" + count);
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

/**
 * Testing assertions in Java. To run write in Terminal with option
 * "{@code -ea:com.github.druyaned.learn_java.vol1.chapter07...}".
 */
class AssertionsTester {
    public static void run() {
        System.out.println("\n" + bold("Testing assertions."));

        int L = 24;
        System.out.printf("%" + L + "s: %s\n", "SystemClassLoader",
                          ClassLoader.getSystemClassLoader());
        System.out.printf("%" + L + "s: %s\n", "AssertionsTester",
                          AssertionsTester.class.getClassLoader());

        String message = "Negative value is detected. It's time to solve it.";
        for (int i = 8; i >= -2; --i) {
            int remainder = i % 3;
            assert remainder >= 0 : "remainder=" + remainder + "; remainder>=0. " + message;
            System.out.printf("i = %d; i %% 3 = %d\n", i, remainder);
        }
    }
}
