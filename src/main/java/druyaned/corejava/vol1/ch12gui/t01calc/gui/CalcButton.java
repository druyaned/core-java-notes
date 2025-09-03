package druyaned.corejava.vol1.ch12gui.t01calc.gui;

import java.awt.Font;
import static java.awt.Font.MONOSPACED;
import static java.awt.Font.PLAIN;
import javax.swing.JButton;

public class CalcButton extends JButton {
    
    public CalcButton(CalcAction action) {
        setAction(action);
        setFont(BUTTON_FONT);
        setText(action.text());
    }
    
    public static Font BUTTON_FONT = new Font(MONOSPACED, PLAIN, 22);
    
}
