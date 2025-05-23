package druyaned.corejava.vol1.ch11.p04;

import static druyaned.corejava.vol1.ch11.p04.Images.*;
import java.awt.*;

public class TestMouse implements Runnable {
    
    public static final int OFFSET = 0;
    public static final Cursor CURSOR_H;
    public static final Cursor CURSOR_F;
    public static final Cursor CURSOR_HG;
    
    static {
        Point point = new Point(OFFSET, OFFSET);
        final Toolkit TK = Toolkit.getDefaultToolkit();
        CURSOR_H = TK.createCustomCursor(makeCursorH(), point, "hand cursor");
        CURSOR_F = TK.createCustomCursor(makeCursorF(), point, "finger cursor");
        CURSOR_HG = TK.createCustomCursor(makeCursorHG(), point, "green hand cursor");
    }

    @Override public void run() {
        EventQueue.invokeLater(() -> {
            MouseFrame mouseFrame = new MouseFrame();
            mouseFrame.setVisible(true);
        });
    }
    
}
