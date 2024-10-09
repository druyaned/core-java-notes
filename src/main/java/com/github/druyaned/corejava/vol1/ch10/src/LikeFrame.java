package com.github.druyaned.corejava.vol1.ch10.src;

import java.awt.*;
import java.io.IOException;
import java.io.UncheckedIOException;
import javax.imageio.ImageIO;

import javax.swing.*;

public class LikeFrame extends JFrame {
    
    private final Image likeImage;

    public LikeFrame(JFrame frame) {
        setTitle("Like frame");
        String likeName = "/vol1/ch10/like.png";
        try {
            likeImage = ImageIO.read(LikeFrame.class.getResourceAsStream(likeName));
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
        double likeK = (double)likeImage.getWidth(null) / likeImage.getHeight(null);
        if (Double.compare(likeK, 1) > 0) {
            setSize(frame.getWidth(), (int)Math.round(frame.getWidth() / likeK));
        } else {
            setSize((int)Math.round(frame.getHeight() * likeK), frame.getHeight());
        }
        setLocation(frame.getX() + 50, frame.getY() + 50);
    }

    public Image getLikeImage() {
        return likeImage;
    }
    
}
