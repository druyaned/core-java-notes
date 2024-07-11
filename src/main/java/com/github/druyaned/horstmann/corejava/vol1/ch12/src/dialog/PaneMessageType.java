package com.github.druyaned.horstmann.corejava.vol1.ch12.src.dialog;

import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class PaneMessageType extends PaneButton {
    
    public static final String ERROR_MESSAGE = "ERROR_MESSAGE";
    public static final String INFORMATION_MESSAGE = "INFORMATION_MESSAGE";
    public static final String WARNING_MESSAGE = "WARNING_MESSAGE";
    public static final String QUESTION_MESSAGE = "QUESTION_MESSAGE";
    public static final String PLAIN_MESSAGE = "PLAIN_MESSAGE";
    private static final Map<String, JRadioButton> buttons = new HashMap<>();
    
    static {
        buttons.put(ERROR_MESSAGE, new JRadioButton(ERROR_MESSAGE));
        buttons.put(INFORMATION_MESSAGE, new JRadioButton(INFORMATION_MESSAGE));
        buttons.put(WARNING_MESSAGE, new JRadioButton(WARNING_MESSAGE));
        buttons.put(QUESTION_MESSAGE, new JRadioButton(QUESTION_MESSAGE));
        buttons.put(PLAIN_MESSAGE, new JRadioButton(PLAIN_MESSAGE));
    }

    public PaneMessageType() {
        super(
                "Message type",
                buttons.get(PLAIN_MESSAGE).getModel(),
                buttons,
                new String[] {
                    ERROR_MESSAGE,
                    INFORMATION_MESSAGE,
                    WARNING_MESSAGE,
                    QUESTION_MESSAGE,
                    PLAIN_MESSAGE
                }
        );
    }

    public int getSelectedMessageType() {
        if (isSelected(ERROR_MESSAGE)) {
            return JOptionPane.ERROR_MESSAGE;
        } else if (isSelected(INFORMATION_MESSAGE)) {
            return JOptionPane.INFORMATION_MESSAGE;
        } else if (isSelected(WARNING_MESSAGE)) {
            return JOptionPane.WARNING_MESSAGE;
        } else if (isSelected(QUESTION_MESSAGE)) {
            return JOptionPane.QUESTION_MESSAGE;
        } else {
            return JOptionPane.PLAIN_MESSAGE;
        }
    }
    
}
