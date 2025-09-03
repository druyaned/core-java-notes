package druyaned.corejava.vol1.ch11events;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.util.ScreenSize;
import druyaned.corejava.vol1.ch11events.t02framestyle.ButtonsFrame;
import druyaned.corejava.vol1.ch11events.t02framestyle.ButtonsPanel;
import java.awt.EventQueue;
import java.awt.Rectangle;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class T02FrameStyle extends Topic {
    
    public T02FrameStyle(Chapter chapter) {
        super(chapter, 2);
    }
    
    @Override public String title() {
        return "Topic 02 FrameStyle";
    }
    
    @Override public void run() {
        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        int offset = 80;
        Rectangle baseBounds = ScreenSize.get(0.5);
        EventQueue.invokeLater(() -> {
            for (int i = 0; i < infos.length; ++i) {
                int x = offset * i;
                int y = offset * i;
                int w = baseBounds.width;
                int h = baseBounds.height;
                Rectangle bounds = new Rectangle(x, y, w, h);
                ButtonsFrame buttonsFrame = new ButtonsFrame(infos[i].getName(), bounds);
                ButtonsPanel buttonsPanel = new ButtonsPanel();
                buttonsFrame.getContentPane().add(buttonsPanel);
                try {
                    UIManager.setLookAndFeel(infos[i].getClassName());
                    SwingUtilities.updateComponentTreeUI(buttonsFrame);
                } catch (
                        ClassNotFoundException
                                | IllegalAccessException
                                | InstantiationException
                                | UnsupportedLookAndFeelException
                                exc
                ) {
                    throw new RuntimeException(exc);
                }
                buttonsFrame.setVisible(true);
            }
        });
    }
    
}
