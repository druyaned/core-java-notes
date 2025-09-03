package druyaned.corejava.vol1.ch11events.t04mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionHandler implements MouseMotionListener {
    
    private final MouseComponent mouseComponent;
    private final Cursors cursors;
    
    public MouseMotionHandler(MouseComponent mc, Cursors cursors) {
        this.mouseComponent = mc;
        this.cursors = cursors;
    }
    
    @Override public void mouseMoved(MouseEvent e) {
        MouseFrame f = mouseComponent.getMouseFrame();
        if (f.isSet(e.getX(), e.getY())) {
            mouseComponent.setCursor(cursors.greenHand());
        } else {
            mouseComponent.setCursor(cursors.hand());
        }
    }
    
    @Override public void mouseDragged(MouseEvent e) {
        mouseComponent.setCursor(cursors.finger());
    }
    
}
