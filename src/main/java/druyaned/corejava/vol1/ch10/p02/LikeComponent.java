package druyaned.corejava.vol1.ch10.p02;

import java.awt.*;
import javax.swing.*;

public class LikeComponent extends JComponent {
    
    private final JFrame likeFrame;
    private final Image likeImage;

    public LikeComponent(JFrame likeFrame, Image likeImage) {
        this.likeFrame = likeFrame;
        this.likeImage = likeImage;
    }

    @Override protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        int lineAmount = 4;
        int w = likeFrame.getWidth() / lineAmount;
        int h = likeFrame.getHeight() / lineAmount;
        g2.drawImage(likeImage, 0, 0, w, h, null);
        for (int i = 0; i < lineAmount; ++i) {
            for (int j = 0; j < lineAmount - 1; ++j) {
                g2.copyArea(0, 0, w, h, w * i, h * j);
            }
        }
    }
    
}
