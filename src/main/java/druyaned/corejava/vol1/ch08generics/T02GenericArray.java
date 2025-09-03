package druyaned.corejava.vol1.ch08generics;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.vol1.ch08generics.t02array.GenericArray;

public class T02GenericArray extends Topic {
    
    public T02GenericArray(Chapter chapter) {
        super(chapter, 2);
    }
    
    @Override public String title() {
        return "Topic 02 GenericArray";
    }
    
    @Override public void run() {
        try {
            GenericArray<String> strings = new GenericArray<>();
            System.out.println("strings: " + strings);
            strings = GenericArray.from("str1", "str2", "str3");
            System.out.println("strings: " + strings);
            System.out.println("strings.get(0)=" + strings.get(0));
            System.out.println("strings.get(2)=" + strings.get(3));
            strings = new GenericArray<>();
            for (int i = 1; i <= GenericArray.MIN_CAPACITY; i++) {
                strings.add("str" + i);
            }
            System.out.println("strings: " + strings);
            System.out.println("strings.forEach:");
            strings.forEach(s -> System.out.println("  " + s));
            strings.add("str" + (GenericArray.MIN_CAPACITY + 1));
            System.out.println("strings: " + strings);
            // An exception will be thrown
            strings = new GenericArray<>(GenericArray.MAX_CAPACITY + 1);
            System.out.println("strings: " + strings);
        } catch (RuntimeException exc) {
            exc.printStackTrace();
        }
        System.out.println("Exiting from TopicGenericArray...");
    }
    
}
