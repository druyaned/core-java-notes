package druyaned.corejava.vol1.ch12.src;

import java.awt.Image;
import java.io.IOException;
import java.io.UncheckedIOException;
import javax.imageio.ImageIO;

public class Images {
    
    private static final String JAR_DIR = "/vol1/ch12/";
    
    public static Image makeMessage() {
        try {
            return ImageIO.read(Images.class.getResource(JAR_DIR + "message.png"));
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
    public static Image makeWarning() {
        try {
            return ImageIO.read(Images.class.getResource(JAR_DIR + "warning.png"));
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
}
