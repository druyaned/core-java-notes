package druyaned.corejava.vol1.ch11.p04;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    
    private final MouseComponent mouseComponent;

    public MouseHandler(MouseComponent mc) {
        mouseComponent = mc;
    }

    @Override public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        int i = mouseComponent.findSpace(p);
        if (i == -1) {
            mouseComponent.addRect(p);
        } else {
            mouseComponent.removeRect(i);
        }
    }

    @Override public void mouseReleased(MouseEvent e) {
        mouseComponent.setCursor(TestMouse.CURSOR_H);
    }
    
}
