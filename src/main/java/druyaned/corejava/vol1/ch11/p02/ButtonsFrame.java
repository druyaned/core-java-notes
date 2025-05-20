package druyaned.corejava.vol1.ch11.p02;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class ButtonsFrame extends JFrame {
    
    public static final Dimension DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    
    public ButtonsFrame(String title, int x, int y) {
        int w = (int)Math.round(DIMENSION.getWidth() / 2);
        int h = (int)Math.round(DIMENSION.getHeight() / 2);
        setTitle(title);
        setBounds(x, y, w, h);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Closer closer = new Closer(this, x, y, w, h);
        addWindowListener(closer);
    }
    
}
