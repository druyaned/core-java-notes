package druyaned.corejava.vol1.ch11.p02;

import java.awt.EventQueue;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TestFrameStyle implements Runnable {
    
    @Override public void run() {
        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        int offset = 80;
        EventQueue.invokeLater(() -> {
            for (int i = 0; i < infos.length; ++i) {
                ButtonsFrame buttonsFrame = new ButtonsFrame(
                        infos[i].getName(), offset * i, offset * i
                );
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
