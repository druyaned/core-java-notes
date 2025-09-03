package druyaned.corejava.vol1.ch11events.t04mouse;

import druyaned.corejava.util.ScreenSize;
import java.awt.Rectangle;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MouseFrame extends JFrame {
    
    private final boolean[][] bits;
    
    public MouseFrame(Cursors cursors) {
        Rectangle bounds = ScreenSize.get(0.5);
        bits = new boolean[bounds.width][bounds.height];
        setTitle("MouseFrame");
        setBounds(bounds);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(new MouseComponent(this, bounds, cursors));
        pack(); // not too necessary
    }
    
    /**
     * Sets width and height bits of the frame.
     * @param xFrom x index to start setting (including)
     * @param xTo x index to end setting (excluding)
     * @param yFrom y index to start setting (including)
     * @param yTo y index to end setting (excluding)
     */
    public void setBits(int xFrom, int xTo, int yFrom, int yTo) {
        for (int i = xFrom; i <= xTo; ++i) {
            for (int j = yFrom; j <= yTo; ++j) {
                bits[i][j] = true;
            }
        }
    }
    
    /** Unsets width and height bits of the frame. */
    public void unsetBits() {
        for (int i = 0; i < bits.length; ++i) {
            for (int j = 0; j < bits[0].length; ++j)
                bits[i][j] = false;
        }
    }
    
    public boolean isSet(int x, int y) {
        return bits[x][y];
    }
    
}
