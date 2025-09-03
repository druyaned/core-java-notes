package druyaned.corejava.vol1.ch03constr;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.util.Date;

public class T03Out extends Topic {
    
    public T03Out(Chapter chapter) {
        super(chapter, 3);
    }
    
    @Override public String title() {
        return "Topic 03 Out";
    }
    
    @Override public void run() {
        double d = 10_000.0 / 3d;
        System.out.println("d: " + d);
        System.out.printf("d: %010.2f\n", d);
        System.out.printf("date: %tc\n", new Date());
        System.out.printf("%tF; [%1$tT] or [%<tr]\n", new Date());
    }
    
}
