package druyaned.corejava.vol1.ch07exclog;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;

public class T02TryCatchTime extends Topic {
    
    public T02TryCatchTime(Chapter chapter) {
        super(chapter, 2);
    }
    
    @Override public String title() {
        return "Topic 02 TryCatchTime";
    }
    
    @Override public void run() {
        int count = 0;
        long prevTime = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; ++i) {
            try { // it's better to use "try" outside of the loop
                count += exceptionedFun(-i); // can be changed "-i" -> "i"
            } catch (Exception exc) {
                count += 1;
            }
        }
        long spentTime = System.currentTimeMillis() - prevTime;
        System.out.println("Spent time with try-catch: " + spentTime + " (mm);"
                + " count=" + count);
        count = 0;
        prevTime = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; ++i) {
            int d = noExceptionedFun(-i); // can be changed "-i" -> "i"
            if (d == -1)
                count += 1;
            else
                count += d;
        }
        spentTime = System.currentTimeMillis() - prevTime;
        System.out.println("Spent time with condition: " + spentTime + " (mm);"
                + " count=" + count);
    }
    
    private int exceptionedFun(int n) throws Exception {
        if (n >= 0 && n < ITERATIONS)
            return 1;
        throw new Exception();
    }
    
    private int noExceptionedFun(int n) {
        if (n >= 0 && n < ITERATIONS)
            return 1;
        return -1;
    }
    
    private static final int ITERATIONS = 2_000_000;
    
}
