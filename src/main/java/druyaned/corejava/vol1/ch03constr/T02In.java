package druyaned.corejava.vol1.ch03constr;

import druyaned.corejava.App;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;

public class T02In extends Topic {
    
    public T02In(Chapter chapter) {
        super(chapter, 2);
    }
    
    @Override public String title() {
        return "Topic 02 In";
    }
    
    @Override public void run() {
        System.out.print("Write smth in a line: ");
        String line = App.sin.nextLine();
        System.out.println("input: '" + line + "'");
        System.out.print("Now type in only one number: ");
        if (App.sin.hasNextInt()) {
            int a = App.sin.nextInt();
            System.out.println("a = " + a);
        } else {
            System.out.println("Ops! It wasn't a number.");
        }
        App.sin.nextLine(); // clean up the input stream
    }
    
}
