package druyaned.corejava.vol1.ch11.p02;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonsPanel extends JPanel {
    
    public final static Color DEF = new Color(224, 224, 255);
    
    public ButtonsPanel() {
        makeButton("Red", Color.RED);
        makeButton("Green", Color.GREEN);
        makeButton("Default", DEF);
        setBackground(DEF);
    }
    
    private void makeButton(String name, Color bg) {
        JButton b = new JButton(name);
        add(b);
        b.addActionListener(event -> setBackground(bg));
    }
    
}
