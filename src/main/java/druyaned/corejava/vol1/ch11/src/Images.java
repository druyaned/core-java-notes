package druyaned.corejava.vol1.ch11.src;

import java.awt.Image;
import java.io.IOException;
import java.io.UncheckedIOException;
import javax.imageio.ImageIO;

public class Images {
    
    public static final String JAR_DIR = "/vol1/ch11/";
    
    public static Image makeErasing() {
        try {
            return ImageIO.read(Images.class.getResource(JAR_DIR + "erasing.png"));
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
    public static Image makeHeart() {
        try {
            return ImageIO.read(Images.class.getResource(JAR_DIR + "heart.png"));
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
    public static Image makeSpot() {
        try {
            return ImageIO.read(Images.class.getResource(JAR_DIR + "spot.png"));
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
    public static Image makeCursorF() {
        try {
            return ImageIO.read(Images.class.getResource(JAR_DIR + "cursor_f.png"));
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
    public static Image makeCursorH() {
        try {
            return ImageIO.read(Images.class.getResource(JAR_DIR + "cursor_h.png"));
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
    public static Image makeCursorHG() {
        try {
            return ImageIO.read(Images.class.getResource(JAR_DIR + "cursor_hg.png"));
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
}
