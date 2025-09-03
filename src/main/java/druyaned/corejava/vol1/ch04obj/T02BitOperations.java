package druyaned.corejava.vol1.ch04obj;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;

public class T02BitOperations extends Topic {
    
    public T02BitOperations(Chapter chapter) {
        super(chapter, 2);
    }
    
    @Override public String title() {
        return "Topic 02 Bit Operations";
    }
    
    @Override public void run() {
        int a = 13, b = 10;
        String s;
        s = Integer.toBinaryString(a);
        System.out.println("a: " + s + " = " + a);
        s = Integer.toBinaryString(b);
        System.out.println("b: " + s + " = " + b);
        s = Integer.toBinaryString(a ^ b);
        System.out.println("a ^ b: " + s + " = " + (a ^ b));
        s = Integer.toBinaryString(a & b);
        System.out.println("a & b: " + s + " = " + (a & b));
        s = Integer.toBinaryString(a | b);
        System.out.println("a | b: " + s + " = " + (a | b));
        s = Integer.toBinaryString(~a);
        System.out.println("~a: " + s + " = " + (~a));
        s = Integer.toBinaryString(a >> 2);
        System.out.println("a >> 2: " + s + " = " + (a >> 2));
        s = Integer.toBinaryString(a << 3);
        System.out.println("a << 3: " + s + " = " + (a << 3));
        s = Integer.toBinaryString(~a >> 2);
        System.out.println("~a >> 2: " + s + " = " + (~a >> 2));
    }
    
}
