package druyaned.corejava.vol1.ch11events;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.vol1.ch11events.t03action.ActionFrame;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class T03Action extends Topic {

    public T03Action(Chapter chapter) {
        super(chapter, 3);
    }
    
    @Override public String title() {
        return "Topic 03 Action";
    }
    
    @Override public void run() {
        EventQueue.invokeLater(() -> {
            ActionFrame actionFrame = new ActionFrame(this);
            setFrameStyle(actionFrame);
            actionFrame.setVisible(true);
        });
    }
    
    private static void setFrameStyle(JFrame f) {
        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        try {
            UIManager.setLookAndFeel(infos[0].getClassName());
            SwingUtilities.updateComponentTreeUI(f);
        } catch (
                ClassNotFoundException
                        | IllegalAccessException
                        | InstantiationException
                        | UnsupportedLookAndFeelException
                        exc
        ) {
            throw new RuntimeException(exc);
        }
    }
    
}
