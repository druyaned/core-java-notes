package com.github.druyaned.learn_java.vol1.chapter12;

import static com.github.druyaned.learn_java.vol1.chapter12.Images.*;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class PaneIcon extends PaneButton {
    public static final String MESSAGE = "message.png";
    public static final String WARNING = "warning.png";
    public static final String NONE = "none";

    private static final Map<String, JRadioButton> ICON_BUTTONS = new HashMap<>();
    private static final Map<String, ImageIcon> ICONS = new HashMap<>();

    static {
        ICON_BUTTONS.put(MESSAGE, new JRadioButton(MESSAGE));
        ICON_BUTTONS.put(WARNING, new JRadioButton(WARNING));
        ICON_BUTTONS.put(NONE, new JRadioButton(NONE));
        
        int hints = Image.SCALE_SMOOTH;
        int fs = 24;
        ICONS.put(MESSAGE, new ImageIcon(makeMessage().getScaledInstance(fs, fs, hints)));
        ICONS.put(WARNING, new ImageIcon(makeWarning().getScaledInstance(fs, fs, hints)));
    }

    public PaneIcon() {
        super("Icon",
              ICON_BUTTONS.get(NONE).getModel(),
              ICON_BUTTONS, new String[] { MESSAGE, WARNING, NONE });
    }

    /** Accepted values: {@link PaneIcon#MESSAGE}, {@link PaneIcon#WARNING}. */
    public ImageIcon getImageIcon(String key) { return ICONS.get(key); }

    public String getSelectedKey() {
        if (isSelected(MESSAGE)) { return MESSAGE; }
        else if (isSelected(WARNING)) { return WARNING; }
        else { return NONE; }
    }
}
