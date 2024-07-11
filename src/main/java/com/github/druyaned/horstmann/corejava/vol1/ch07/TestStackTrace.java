package com.github.druyaned.horstmann.corejava.vol1.ch07;

import static com.github.druyaned.ConsoleColors.bold;

public class TestStackTrace {
    
    public static void run() {
        System.out.println("\n" + bold("TestStackTrace"));
        long num = 5;
        System.out.printf("factorial(%d)=%d\n", num, factorial(num));
    }

    public static long factorial(long num) {
        System.out.printf("In %s; stack trace:\n", bold("factorial(" + num + ")"));
        Throwable throwable = new Throwable();
        StackTraceElement[] frames = throwable.getStackTrace();
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
