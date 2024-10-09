package com.github.druyaned.corejava.vol1.ch14.src;

import java.awt.Image;
import java.io.IOException;
import java.io.UncheckedIOException;
import javax.imageio.ImageIO;

public class Images {
    
    private static final String BALL_NAME = "/vol1/ch14/ball.png";
    
    public static Image makeBall() {
        try {
            return ImageIO.read(Images.class.getResource(BALL_NAME));
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
}
