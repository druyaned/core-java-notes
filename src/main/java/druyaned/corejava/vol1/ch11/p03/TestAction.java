package druyaned.corejava.vol1.ch11.p03;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TestAction implements Runnable {
    
    @Override public void run() {
        EventQueue.invokeLater(() -> {
            ActionFrame actionFrame = new ActionFrame();
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
