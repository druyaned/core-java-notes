package druyaned.corejava.vol1.ch11.p02;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Closer extends WindowAdapter {
    
    private final ButtonsFrame buttonsFrame;
    private final int x, y, w, h;
    
    public Closer(ButtonsFrame buttonsFrame, int x, int y, int w, int h) {
        this.buttonsFrame = buttonsFrame;
        this.x = x + w / 4;
        this.y = y + h / 4;
        this.w = w / 2;
        this.h = h / 2;
    }
    
    @Override public void windowClosing(WindowEvent event) {
        JFrame askFrame = new JFrame();
        askFrame.setBounds(x, y, w, h);
        JPanel p = new JPanel();
        JButton closeButton = new JButton("Close");
        JButton reconsiderButton = new JButton("I reconsidered");
        p.add(closeButton);
        p.add(reconsiderButton);
        closeButton.addActionListener(e -> {
            buttonsFrame.dispose();
            askFrame.dispose();
        });
        reconsiderButton.addActionListener(e -> askFrame.dispose());
        p.setBounds(0, 0, w, h / 2);
        askFrame.getContentPane().add(p);
        askFrame.setVisible(true);
    }
    
    public int getW() {
        return w;
    }
    
    public int getH() {
        return h;
    }
    
}
