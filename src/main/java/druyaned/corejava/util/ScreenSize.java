package druyaned.corejava.util;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Provides utilities of the screen size from {@link Toolkit#getScreenSize()}.
 * @author druyaned
 */
public class ScreenSize {
    
    /** Minimum allowed screen factor. */
    public static final double MIN_SCREEN_FACTOR = 0.1;
    
    /** Maximum allowed screen factor. */
    public static final double MAX_SCREEN_FACTOR = 1.0;
    
    /**
     * Returns rectangle to be set in {@link JFrame#setBounds(Rectangle)},
     * width and height are multiplied by {@code screenFactor}
     * and location is centered.
     * 
     * @param screenFactor scaling of the frame's rectangle which must be
     *      from {@link #MIN_SCREEN_FACTOR} to {@link #MAX_SCREEN_FACTOR}
     * @return rectangle to be set in {@link JFrame#setBounds(Rectangle)},
     *      width and height are multiplied by {@code screenFactor}
     *      and location is centered
     */
    public static Rectangle get(double screenFactor) {
        if (screenFactor < MIN_SCREEN_FACTOR || MAX_SCREEN_FACTOR < screenFactor) {
            throw new IllegalArgumentException(String.format(
                    "screenFactor=%f must be in [%f, %f]",
                    screenFactor,
                    MIN_SCREEN_FACTOR,
                    MAX_SCREEN_FACTOR
            ));
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = (int)(screenFactor * screenSize.width);
        int h = (int)(screenFactor * screenSize.height);
        int x = (screenSize.width - w) / 2;
        int y = (screenSize.height - h) / 2;
        return new Rectangle(x, y, w, h);
    }
    
}
