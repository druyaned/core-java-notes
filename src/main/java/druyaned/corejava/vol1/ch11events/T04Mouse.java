package druyaned.corejava.vol1.ch11events;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.vol1.ch11events.t04mouse.Cursors;
import druyaned.corejava.vol1.ch11events.t04mouse.MouseFrame;
import java.awt.EventQueue;

public class T04Mouse extends Topic {
    
    public T04Mouse(Chapter chapter) {
        super(chapter, 4);
    }
    
    @Override public String title() {
        return "Topic 04 Mouse";
    }
    
    @Override public void run() {
        EventQueue.invokeLater(() -> {
            Cursors cursors = new Cursors(this);
            MouseFrame mouseFrame = new MouseFrame(cursors);
            mouseFrame.setVisible(true);
        });
    }
    
}
