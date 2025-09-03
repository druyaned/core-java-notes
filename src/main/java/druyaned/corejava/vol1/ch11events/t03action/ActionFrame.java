package druyaned.corejava.vol1.ch11events.t03action;

import druyaned.corejava.Book;
import druyaned.corejava.Topic;
import druyaned.corejava.util.FileUtil;
import druyaned.corejava.util.ScreenSize;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ActionFrame extends JFrame {
    
    public static final Color DEF_COLOR = new Color(224, 224, 255);
    public static final int FONT_SIZE = 18;
    
    public ActionFrame(Topic topic) {
        setTitle("ActionFrame");
        setBounds(ScreenSize.get(0.5));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBackground(DEF_COLOR);
        int hints = Image.SCALE_SMOOTH;
        Book book = topic.chapter().volume().book();
        ImageIcon erasing = new ImageIcon(FileUtil
                .createImage(book, topic, "erasing.png")
                .getScaledInstance(FONT_SIZE, FONT_SIZE, hints));
        ImageIcon heart = new ImageIcon(FileUtil
                .createImage(book, topic, "heart.png")
                .getScaledInstance(FONT_SIZE, FONT_SIZE, hints));
        ImageIcon spot = new ImageIcon(FileUtil
                .createImage(book, topic, "spot.png")
                .getScaledInstance(FONT_SIZE, FONT_SIZE, hints));
        Action rAction = new ColorAction(panel, "Red", heart, Color.RED);
        Action gAction = new ColorAction(panel, "Green", erasing, Color.GREEN);
        Action dAction = new ColorAction(panel, "Default", spot, DEF_COLOR);
        panel.add(new JButton(rAction));
        panel.add(new JButton(gAction));
        panel.add(new JButton(dAction));
        InputMap iMap = panel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        iMap.put(KeyStroke.getKeyStroke("meta R"), "panel.red");
        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.SHIFT_DOWN_MASK), "panel.green");
        iMap.put(KeyStroke.getKeyStroke("ctrl D"), "panel.default");
        ActionMap aMap = panel.getActionMap();
        aMap.put("panel.red", rAction);
        aMap.put("panel.green", gAction);
        aMap.put("panel.default", dAction);
        getContentPane().add(panel);
        setVisible(true);
    }
    
}
