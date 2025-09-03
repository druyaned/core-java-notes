package druyaned.corejava.vol1.ch10graphics;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;

public class T01GoodFrame extends Topic {
    
    public T01GoodFrame(Chapter chapter) {
        super(chapter, 1);
    }
    
    @Override public String title() {
        return "Topic 01 GoodFrame";
    }
    
    @Override public void run() {
        GraphicsEnvironment locGrEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontFamilyNames = locGrEnv.getAvailableFontFamilyNames();
        int ind = Arrays.binarySearch(fontFamilyNames, "Monospaced");
        System.out.println("Monospaced's index=" + ind);
        EventQueue.invokeLater(() -> {
            GoodFrame goodFrame = new GoodFrame("Good frame");
            GoodComponent component = new GoodComponent(
                    goodFrame.getWidth(),
                    goodFrame.getHeight()
            );
            goodFrame.getContentPane().add(component);
            goodFrame.pack();
            goodFrame.setVisible(true);
        });
    }
    
}
