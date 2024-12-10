package druyaned.corejava.vol1.ch14.src.ball;

import druyaned.corejava.vol1.ch14.src.Images;
import java.awt.*;
import javax.swing.*;

public class BallImage extends JComponent {
    
    public static final int DIAMETER = 64;
    
    private final Image image;
    private final BallPosition position;
    
    public BallImage() {
        image = Images.makeBall();
        position = new BallPosition();
    }
    
    public Image getImage() {
        return image;
    }
    
    public BallPosition getPosition() {
        return position;
    }
    
    @Override public void paintComponent(Graphics g) {
        setLocation(position.getX(), position.getY());
        g.drawImage(image, 0, 0, DIAMETER, DIAMETER, null);
    }

    @Override public Dimension getPreferredSize() {
        return new Dimension(DIAMETER, DIAMETER);
    }
    
}
