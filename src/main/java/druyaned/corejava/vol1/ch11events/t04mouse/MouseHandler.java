package druyaned.corejava.vol1.ch11events.t04mouse;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    
    private final MouseComponent mouseComponent;
    private final Cursors cursors;
    
    public MouseHandler(MouseComponent mc, Cursors cursors) {
        this.mouseComponent = mc;
        this.cursors = cursors;
    }
    
    @Override public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        int i = mouseComponent.findSpace(p);
        if (i == -1)
            mouseComponent.addRect(p);
        else
            mouseComponent.removeRect(i);
    }
    
    @Override public void mouseReleased(MouseEvent e) {
        mouseComponent.setCursor(cursors.hand());
    }
    
}
