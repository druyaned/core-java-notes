package druyaned.corejava.vol1.ch11events.t04mouse;

import druyaned.corejava.Book;
import druyaned.corejava.Topic;
import druyaned.corejava.util.FileUtil;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

public class Cursors {
    
    private final Cursor hand;
    private final Cursor finger;
    private final Cursor greenHand;
    
    public Cursors(Topic topic) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Point hotSpot = new Point(0, 0);
        Book book = topic.chapter().volume().book();
        hand = tk.createCustomCursor(FileUtil.createImage(book, topic, "cursor-hand.png"),
                hotSpot,
                "hand cursor"
        );
        finger = tk.createCustomCursor(FileUtil.createImage(book, topic, "cursor-finger.png"),
                hotSpot,
                "finger cursor"
        );
        greenHand = tk.createCustomCursor(FileUtil.createImage(book, topic, "cursor-green-hand.png"),
                hotSpot,
                "green hand cursor"
        );
    }

    public Cursor hand() {
        return hand;
    }

    public Cursor finger() {
        return finger;
    }

    public Cursor greenHand() {
        return greenHand;
    }
    
}
