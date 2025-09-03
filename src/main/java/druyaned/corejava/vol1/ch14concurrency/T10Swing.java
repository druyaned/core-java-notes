package druyaned.corejava.vol1.ch14concurrency;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;

public class T10Swing extends Topic {
    
    public T10Swing(Chapter chapter) {
        super(chapter, 10);
    }
    
    @Override public String title() {
        return "Topic 10 Swing";
    }
    
    @Override public void run() {
        System.out.print(
        """
        Two constraints to keep in mind:
            1. Time-consuming tasks should not be run on
                the Event Dispatch Thread. Otherwise
                the application becomes unresponsive.
            2. Swing components should be accessed on
                the Event Dispatch Thread only.
        But there are some usefull thread-safe methods:
            - JTextComponent.setText()
            - JTextArea.insert()
            - JTextArea.append()
            - JTextArea.replaceRange()
            - JComponent.repaint()
            - JComponent.revalidate()
        """
        );
    }
    
}
