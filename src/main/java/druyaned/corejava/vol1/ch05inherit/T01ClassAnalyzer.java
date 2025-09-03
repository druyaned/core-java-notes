package druyaned.corejava.vol1.ch05inherit;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;

public class T01ClassAnalyzer extends Topic {
    
    public T01ClassAnalyzer(Chapter chapter) {
        super(chapter, 1);
    }
    
    @Override public String title() {
        return "Topic 01 ClassAnalyzer";
    }
    
    @Override public void run() {
        ClassAnalyzer.showClass(C05Inheritance.class);
    }
    
}
