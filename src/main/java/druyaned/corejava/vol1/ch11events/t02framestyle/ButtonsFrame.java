package druyaned.corejava.vol1.ch11events.t02framestyle;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class ButtonsFrame extends JFrame {
    
    public static final Dimension DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    
    public ButtonsFrame(String title, Rectangle bounds) {
        setTitle(title);
        setBounds(bounds);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Closer closer = new Closer(this, bounds.x, bounds.y, bounds.width, bounds.height);
        addWindowListener(closer);
    }
    
}
