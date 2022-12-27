package com.github.druyaned.learn_java.vol1.chapter14;

import static com.github.druyaned.learn_java.vol1.chapter14.Images.makeBall;
import java.awt.*;
import javax.swing.*;

public class BallImage extends JComponent {
    public static final int DIAMETER = 64;
    
    private final Image image;
    private final BallPosition position;
    
    public BallImage() {
        image = makeBall();
        position = new BallPosition();
    }
    
//-Getters------------------------------------------------------------------------------------------
    
    public Image getImage() { return image; }
    
    public BallPosition getPosition() { return position; }
    
//-Methods------------------------------------------------------------------------------------------
    
    @Override
    public void paintComponent(Graphics g) {
        setLocation(position.getX(), position.getY());
        g.drawImage(image, 0, 0, DIAMETER, DIAMETER, null);
    }

    @Override
    public Dimension getPreferredSize() { return new Dimension(DIAMETER, DIAMETER); }
}
