package druyaned.corejava.vol2.ch07international;

import druyaned.corejava.vol2.ch07international.t05app.AppFrame;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

public class T05App extends Topic {
    
    public T05App(Chapter chapter) {
        super(chapter, 5);
    }
    
    @Override public String title() {
        return "Topic 05 App";
    }
    
    @Override public void run() {
        EventQueue.invokeLater(() -> {
            AppFrame frame = new AppFrame(this.chapter());
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension frameSize = frame.getPreferredSize();
            int x = (int)(screenSize.getWidth() / 2 - frameSize.getWidth() / 2);
            int y = (int)(screenSize.getHeight() / 2 - frameSize.getHeight() / 2);
            frame.setLocation(x, y);
            frame.setVisible(true);
        });
    }
    
}
