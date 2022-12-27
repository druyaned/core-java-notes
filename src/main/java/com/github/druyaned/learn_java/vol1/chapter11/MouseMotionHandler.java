package com.github.druyaned.learn_java.vol1.chapter11;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionHandler implements MouseMotionListener {
    private MouseComponent mouseComponent;

    public MouseMotionHandler(MouseComponent mc) {
        mouseComponent = mc;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MouseFrame f = mouseComponent.getMouseFrame();
        if (f.isSet(e.getX(), e.getY())) {
            mouseComponent.setCursor(TestMouse.CURSOR_HG);
        } else {
            mouseComponent.setCursor(TestMouse.CURSOR_H);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseComponent.setCursor(TestMouse.CURSOR_F);
    }
}
