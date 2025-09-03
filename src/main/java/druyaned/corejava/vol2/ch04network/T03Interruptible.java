package druyaned.corejava.vol2.ch04network;

import druyaned.corejava.Chapter;
import druyaned.corejava.vol2.ch04network.t03interruptible.AppFrame;
import druyaned.corejava.Topic;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class T03Interruptible extends Topic {
    
    public T03Interruptible(Chapter chapter) {
        super(chapter, 3);
    }
    
    @Override public String title() {
        return "Topic 03 Interruptible";
    }
    
    @Override public void run() {
        EventQueue.invokeLater(() -> {
            JFrame frame = new AppFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
    
}
