package druyaned.corejava.vol1.ch04obj;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class T03LocalDate extends Topic {
    
    public T03LocalDate(Chapter chapter) {
        super(chapter, 3);
    }
    
    @Override public String title() {
        return "Topic 03 LocalDate";
    }
    
    @Override public void run() {
        LocalDate today = LocalDate.now();
        System.out.println("Day: " + today);
        System.out.println(" Mon Tue Wed Thu Fri Sat Sun");
        DayOfWeek fstDay = today.minusDays(today.getDayOfMonth() - 1).getDayOfWeek();
        for (int i = 1; i <= 7; ++i) {
            if (fstDay.getValue() == i)
                { break; }
            System.out.printf(" %3s", " ");
        }
        int curDay = fstDay.getValue();
        for (int i = 1; i <= today.lengthOfMonth(); ++i, ++curDay) {
            System.out.printf(" %3d", i);
            if (curDay % 7 == 0)
                { System.out.println(); }
        }
        if ((curDay - 1) % 7 != 0) {
            System.out.println();
        }
    }
    
}
