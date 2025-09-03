package druyaned.corejava.vol1.ch06interface;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;

public class T02Cloneable extends Topic {
    
    public T02Cloneable(Chapter chapter) {
        super(chapter, 2);
    }
    
    @Override public String title() {
        return "Topic 02 Cloneable";
    }
    
    @Override public void run() {
        AgedPerson person = new AgedPerson("Nick", 19);
        AgedPerson link = person;
        AgedPerson clone;
        try {
            clone = person.clone();
        } catch (CloneNotSupportedException exc) {
            exc.printStackTrace();
            return;
        }
        System.out.println("person: " + person + " [hash=" + person.hashCode() + "]");
        System.out.println("link:   " + link + " [hash=" + link.hashCode() + "]");
        System.out.println("clone:  " + clone + " [hash=" + clone.hashCode() + "]");
    }
    
}
