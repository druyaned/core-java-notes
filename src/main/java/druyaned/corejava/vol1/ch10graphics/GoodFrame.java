package druyaned.corejava.vol1.ch10graphics;

import druyaned.corejava.util.ScreenSize;
import java.awt.Color;
import javax.swing.JFrame;

public class GoodFrame extends JFrame {
    
    public GoodFrame(String title) {
        setBounds(ScreenSize.get(0.5));
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        getContentPane().setBackground(new Color(224, 224, 255));
    }
    
}
