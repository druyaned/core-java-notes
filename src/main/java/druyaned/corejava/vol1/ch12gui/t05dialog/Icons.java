package druyaned.corejava.vol1.ch12gui.t05dialog;

import druyaned.corejava.Book;
import druyaned.corejava.Topic;
import druyaned.corejava.util.FileUtil;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

public class Icons {
    
    private final Map<String, JRadioButton> buttons = new HashMap<>();
    private final Map<String, ImageIcon> map = new HashMap<>();
    
    public Icons(Topic topic) {
        buttons.put(MESSAGE, new JRadioButton(MESSAGE));
        buttons.put(WARNING, new JRadioButton(WARNING));
        buttons.put(NONE, new JRadioButton(NONE));
        int hints = Image.SCALE_SMOOTH;
        int size = 24;
        Book book = topic.chapter().volume().book();
        Image message = FileUtil.createImage(book, topic, MESSAGE);
        Image warning = FileUtil.createImage(book, topic, MESSAGE);
        map.put(MESSAGE, new ImageIcon(message.getScaledInstance(size, size, hints)));
        map.put(WARNING, new ImageIcon(warning.getScaledInstance(size, size, hints)));
    }
    
    public Map<String, JRadioButton> buttons() {
        return buttons;
    }
    
    public Map<String, ImageIcon> map() {
        return map;
    }
    
    public static final String MESSAGE = "message.png";
    public static final String WARNING = "warning.png";
    public static final String NONE = "none";
    
}
