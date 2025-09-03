package druyaned.corejava.vol1.ch10graphics;

import druyaned.corejava.Book;
import druyaned.corejava.Topic;
import druyaned.corejava.util.FileUtil;
import druyaned.corejava.util.ScreenSize;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.JFrame;

public class LikeFrame extends JFrame {
    
    private final Image likeImage;
    
    public LikeFrame(Topic topic) {
        setTitle("Like frame");
        Book book = topic.chapter().volume().book();
        likeImage = FileUtil.createImage(book, topic, "like.png");
        Rectangle bounds = ScreenSize.get(0.5);
        double likeK = (double)likeImage.getWidth(null) / likeImage.getHeight(null);
        if (Double.compare(likeK, 1) > 0)
            setSize(bounds.width, (int)Math.round(bounds.width / likeK));
        else
            setSize((int)Math.round(bounds.height * likeK), bounds.height);
        setLocation(bounds.x + 50, bounds.y + 50);
    }
    
    public Image likeImage() {
        return likeImage;
    }
    
}
