package druyaned.corejava.vol1.ch12.src.calc;

import java.awt.event.KeyEvent;
import javax.swing.JButton;
import static java.awt.event.KeyEvent.*;

public class CalcDigits extends JButton {
    
    public static final String NONE = "no action";
    private static final int[] KEY_CODES = new int[] {
        VK_0, VK_1, VK_2, VK_3, VK_4, VK_5, VK_6, VK_7, VK_8, VK_9
    };
    private static final String[] MAP_KEYS = new String[] {
        "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"
    };
    private static final String[] TEXTS = new String[10];
    private static final String[] DESCRIPTIONS = new String[10];
    private static final String[] KEY_TEXTS = new String[10];
    
    static {
        for (int i = 0; i < 10; ++i) {
            TEXTS[i] = Integer.toString(i);
            DESCRIPTIONS[i] = "This is the number " + i;
            KEY_TEXTS[i] = KeyEvent.getKeyText(KEY_CODES[i]);
        }
    }
    
    public static String getText(int i) {
        return TEXTS[i];
    }
    
    public static String getDescription(int i) {
        return DESCRIPTIONS[i];
    }
    
    public static int getKeyCode(int i) {
        return KEY_CODES[i];
    }
    
    public static String getKeyText(int i) {
        return KEY_TEXTS[i];
    }
    
    public static String getMapKey(int i) {
        return MAP_KEYS[i];
    }
    
    public static int getMod() {
        return 0;
    }
    
}
