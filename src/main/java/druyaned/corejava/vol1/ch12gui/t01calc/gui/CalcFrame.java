package druyaned.corejava.vol1.ch12gui.t01calc.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalcFrame extends JFrame {
    
    public CalcFrame() {
        super("Calculator");
        setResizable(false);
        CalcPanel calcPanel = new CalcPanel();
        setContentPane(calcPanel);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - CalcPanel.PANEL_WIDTH) / 2;
        int y = (screenSize.height - CalcPanel.PANEL_HEIGHT) / 2;
        setLocation(x, y);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Additional actions
        requestFocusFor(calcPanel);
    }
    
    private void requestFocusFor(JPanel panel) {
        addWindowFocusListener(new WindowAdapter() {
            @Override public void windowGainedFocus(WindowEvent event) {
                panel.requestFocusInWindow();
            }
        });
    }
    
}
