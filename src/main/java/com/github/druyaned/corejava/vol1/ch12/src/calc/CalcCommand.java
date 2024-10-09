package com.github.druyaned.corejava.vol1.ch12.src.calc;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

public enum CalcCommand {
    
    ALL_CLEAR("AC", "All clear", VK_ESCAPE, 0, "allClear"),
    EQUALITY("=", "Equality", VK_ENTER, 0, "equals"),
    DIVISION("÷", "Division", VK_SLASH, 0, "division"),
    MULTIPLICATION("×", "Multiplication", VK_8, SHIFT_DOWN_MASK, "multiply"),
    DIFFERENCE("-", "Difference", VK_MINUS, 0, "minus"),
    SUM("+", "Sum", VK_EQUALS, SHIFT_DOWN_MASK, "plus"),
    DOT(".", "Dot", VK_PERIOD, 0, "dot"),
    BACKSPACE("⬅", "Backspace", VK_BACK_SPACE, 0, "backspace");

    public static final String NONE = "no action";

    private final String text;
    private final String description;
    private final int keyCode;
    private final int mod;
    private final String keyText;
    private final String mapKey;

    private CalcCommand(String txt, String descr, int kCode, int mod, String mKey) {
        this.text = txt;
        this.description = descr;
        this.keyCode = kCode;
        this.mod = mod;
        keyText = KeyEvent.getKeyText(kCode);
        this.mapKey = mKey;
    }

    public String getText() {
        return text;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int getKeyCode() {
        return keyCode;
    }
    
    public int getMod() {
        return mod;
    }
    
    public String getKeyText() {
        return keyText;
    }
    
    public String getMapKey() {
        return mapKey;
    }
    
}
