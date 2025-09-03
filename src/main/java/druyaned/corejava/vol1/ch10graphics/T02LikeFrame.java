package druyaned.corejava.vol1.ch10graphics;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.awt.EventQueue;

public class T02LikeFrame extends Topic {
    
    public T02LikeFrame(Chapter chapter) {
        super(chapter, 2);
    }
    
    @Override public String title() {
        return "Topic 02 LikeFrame";
    }
    
    @Override public void run() {
        EventQueue.invokeLater(() -> {
            LikeFrame likeFrame = new LikeFrame(this);
            LikeComponent likeComponent = new LikeComponent(
                    likeFrame,
                    likeFrame.likeImage()
            );
            likeFrame.getContentPane().add(likeComponent);
            likeFrame.setVisible(true);
        });
    }
    
}
