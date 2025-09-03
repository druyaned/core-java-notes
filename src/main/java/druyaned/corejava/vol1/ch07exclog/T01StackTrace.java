package druyaned.corejava.vol1.ch07exclog;

import static druyaned.ConsoleColors.bold;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;

public class T01StackTrace extends Topic {
    
    public T01StackTrace(Chapter chapter) {
        super(chapter, 1);
    }
    
    @Override public String title() {
        return "Topic 01 StackTrace";
    }
    
    @Override public void run() {
        long num = 5;
        System.out.printf("factorial(%d)=%d\n", num, factorial(num));
    }
    
    private long factorial(long num) {
        System.out.printf("In %s; stack trace:\n", bold("factorial(" + num + ")"));
        Throwable throwable = new Throwable();
        StackTraceElement[] frames = throwable.getStackTrace();
        for (StackTraceElement frame : frames)
            System.out.println(frame);
        if (num == 1)
            return num;
        long midterm = num * factorial(num - 1);
        return midterm;
    }
    
}
