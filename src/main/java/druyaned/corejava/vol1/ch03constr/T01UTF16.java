package druyaned.corejava.vol1.ch03constr;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;

public class T01UTF16 extends Topic {
    
    public T01UTF16(Chapter chapter) {
        super(chapter, 1);
    }
    
    @Override public String title() {
        return "Topic 01 UTF16";
    }
    
    @Override public void run() {
        String s = "\u1bfc wow!";
        System.out.println("s: " + s);
        for (int i = 0; i < s.length(); i++) {
            System.out.println("s.charAt(" + i + ") = " + s.charAt(i));
        }
        System.out.println();
        for (int i = 0; i < s.length(); i++) {
            System.out.printf("s.codePointAt(%d) = %x\n", i, s.codePointAt(i));
        }
    }
    
}
