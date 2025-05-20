package druyaned.corejava.vol1.ch11.p04;

import static druyaned.corejava.vol1.ch11.p03.ActionFrame.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.*;

public class MouseComponent extends JComponent {
    
    private static final int SPACE_L = 32;
    private static final int RECT_L = SPACE_L / 2;
    private static final int HALF_L = RECT_L / 2;
    
    private final MouseFrame frame;
    private final ArrayList<Rectangle> rects;
    private final ArrayList<Rectangle> spaces;

    public MouseComponent(MouseFrame frame) {
        this.frame = frame;
        setCursor(TestMouse.CURSOR_H);
        spaces = new ArrayList<>();
        rects = new ArrayList<>();
        addMouseListener(new MouseHandler(this));
        addMouseMotionListener(new MouseMotionHandler(this));
    }

    public Rectangle getRect(int i) {
        return rects.get(i);
    }

    public MouseFrame getMouseFrame() {
        return frame;
    }

    /**
     * Searching for is there rectangle in this point.
     * @param p point to check.
     * @return {@code index} of found rectangle or {@code -1} if there is no rectangle.
     */
    public int findSpace(Point p) {
        for (int i = 0; i < spaces.size(); ++i) {
            // if (spaces.get(i).contains(p)) {return i;}
            int x1 = (int) spaces.get(i).getX();
            int x2 = (int) spaces.get(i).getWidth() + x1;
            int y1 = (int) spaces.get(i).getY();
            int y2 = (int) spaces.get(i).getHeight() + y1;
            int px = (int) p.getX();
            int py = (int) p.getY();
            if (px >= x1 && px <= x2 && py >= y1 && py <= y2) {return i;}
        }
        return -1;
    }

    /**
     * Adds new rectangle to the point.
     * @param p point to add a rectangle.
     */
    public void addRect(Point p) {
        int x = (int) p.getX();
        int y = (int) p.getY();
        int spaceX = x - RECT_L, spaceY = y - RECT_L;
        Rectangle newSpace = new Rectangle(spaceX, spaceY, SPACE_L, SPACE_L);
        Rectangle newRect = new Rectangle(x - HALF_L, y - HALF_L, RECT_L, RECT_L);
        spaces.add(newSpace);
        rects.add(newRect);
        frame.setBits(spaceX, spaceX + SPACE_L, spaceY, spaceY + SPACE_L);
        repaint();
    }

    public void removeRect(int i) {
        if (i >= spaces.size()) {return;}
        spaces.remove(i);
        rects.remove(i);
        frame.unsetBits();
        for (Rectangle space : spaces) {
            int x = (int) space.getX();
            int y = (int) space.getY();
            frame.setBits(x, x + SPACE_L, y, y + SPACE_L);
        }
        repaint();
    }

    @Override public Dimension getPreferredSize() {
        return new Dimension(FRAME_W, FRAME_H);
    }

    @Override public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (Rectangle2D r : rects) {
            g2.draw(r);
        }
    }
    
}
