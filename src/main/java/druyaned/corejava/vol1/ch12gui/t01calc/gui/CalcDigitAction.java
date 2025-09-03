package druyaned.corejava.vol1.ch12gui.t01calc.gui;

import druyaned.corejava.vol1.ch12gui.t01calc.cmd.CalcCommand;
import static java.awt.event.KeyEvent.VK_0;
import static java.awt.event.KeyEvent.VK_1;
import static java.awt.event.KeyEvent.VK_2;
import static java.awt.event.KeyEvent.VK_3;
import static java.awt.event.KeyEvent.VK_4;
import static java.awt.event.KeyEvent.VK_5;
import static java.awt.event.KeyEvent.VK_6;
import static java.awt.event.KeyEvent.VK_7;
import static java.awt.event.KeyEvent.VK_8;
import static java.awt.event.KeyEvent.VK_9;

public class CalcDigitAction extends CalcAction {
    
    public CalcDigitAction(CalcCommand command, int digit) {
        super(command, texts[digit], descs[digit], keyCodes[digit], 0, mapKeys[digit]);
    }
    
    private static final String[] texts = {
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
    };
    
    private static final String[] descs = {
        "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"
    };
    
    private static final int[] keyCodes = {
        VK_0, VK_1, VK_2, VK_3, VK_4, VK_5, VK_6, VK_7, VK_8, VK_9
    };
    
    private static final String[] mapKeys = {
        "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
    };
    
}
