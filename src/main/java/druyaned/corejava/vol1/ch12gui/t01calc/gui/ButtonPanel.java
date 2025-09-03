package druyaned.corejava.vol1.ch12gui.t01calc.gui;

import druyaned.corejava.vol1.ch09collections.t01deque.Deque;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.CalcMediator;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.CalcMemento;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.Calculator;
import druyaned.corejava.vol1.ch12gui.t01calc.cmd.Backspace;
import druyaned.corejava.vol1.ch12gui.t01calc.cmd.CalcCommand;
import druyaned.corejava.vol1.ch12gui.t01calc.cmd.ClearInputField;
import druyaned.corejava.vol1.ch12gui.t01calc.cmd.Difference;
import druyaned.corejava.vol1.ch12gui.t01calc.cmd.DigitCommand;
import druyaned.corejava.vol1.ch12gui.t01calc.cmd.Division;
import druyaned.corejava.vol1.ch12gui.t01calc.cmd.Dot;
import druyaned.corejava.vol1.ch12gui.t01calc.cmd.Modulo;
import druyaned.corejava.vol1.ch12gui.t01calc.cmd.Multiplication;
import druyaned.corejava.vol1.ch12gui.t01calc.cmd.Sum;
import druyaned.corejava.vol1.ch12gui.t01calc.cmd.Undo;
import druyaned.corejava.vol1.ch12gui.t01calc.cmd.UploadToAccumulator;
import java.awt.Font;
import static java.awt.Font.MONOSPACED;
import static java.awt.Font.PLAIN;
import java.awt.GridLayout;
import java.awt.Toolkit;
import static java.awt.event.InputEvent.SHIFT_DOWN_MASK;
import static java.awt.event.KeyEvent.VK_5;
import static java.awt.event.KeyEvent.VK_8;
import static java.awt.event.KeyEvent.VK_BACK_SPACE;
import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_EQUALS;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import static java.awt.event.KeyEvent.VK_MINUS;
import static java.awt.event.KeyEvent.VK_PERIOD;
import static java.awt.event.KeyEvent.VK_SLASH;
import static java.awt.event.KeyEvent.VK_Z;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
    
    private final JButton[] digits;
    private final JButton dot;
    private final JButton clearInputField;
    private final JButton uploadToAccumulator;
    private final JButton backspace;
    private final JButton sum;
    private final JButton difference;
    private final JButton multiplication;
    private final JButton division;
    private final JButton modulo;
    private final JButton undo;
    
    public ButtonPanel(Calculator calc, CalcMediator mediator) {
        super();
        setLayout(new GridLayout(5, 4));
        digits = new JButton[10];
        Deque<CalcMemento> history = new Deque<>(16); // capacity won't be changed
        InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();
        for (int i = 0; i < 10; i++) {
            CalcCommand command = new DigitCommand(calc, mediator, history, i);
            CalcAction action = new CalcDigitAction(command, i);
            digits[i] = new CalcButton(action.putInto(inputMap, actionMap));
        }
        dot = new CalcButton(new CalcAction(
                new Dot(calc, mediator, history),
                Dot.DOT_SIGN, "Dot", VK_PERIOD, 0, "dot"
        ).putInto(inputMap, actionMap));
        clearInputField = new CalcButton(new CalcAction(
                new ClearInputField(calc, mediator, history),
                "CIF", "Clear Input Field", VK_ESCAPE, 0, "cif"
        ).putInto(inputMap, actionMap));
        uploadToAccumulator = new CalcButton(new CalcAction(
                new UploadToAccumulator(calc, mediator, history),
                "UTA", "Upload to accumulator", VK_ENTER, 0, "uta"
        ).putInto(inputMap, actionMap));
        backspace = new CalcButton(new CalcAction(
                new Backspace(calc, mediator, history),
                "←", "Backspace", VK_BACK_SPACE, 0, "backspace"
        ).putInto(inputMap, actionMap));
        sum = new CalcButton(new CalcAction(
                new Sum(calc, mediator, history),
                "+", "Sum", VK_EQUALS, SHIFT_DOWN_MASK, "plus"
        ).putInto(inputMap, actionMap));
        difference = new CalcButton(new CalcAction(
                new Difference(calc, mediator, history),
                "-", "Difference", VK_MINUS, 0, "minus"
        ).putInto(inputMap, actionMap));
        multiplication = new CalcButton(new CalcAction(
                new Multiplication(calc, mediator, history),
                "×", "Multiplication", VK_8, SHIFT_DOWN_MASK, "multiply"
        ).putInto(inputMap, actionMap));
        division = new CalcButton(new CalcAction(
                new Division(calc, mediator, history),
                "÷", "Division", VK_SLASH, 0, "division"
        ).putInto(inputMap, actionMap));
        modulo = new CalcButton(new CalcAction(
                new Modulo(calc, mediator, history),
                "%", "Modulo", VK_5, SHIFT_DOWN_MASK, "modulo"
        ).putInto(inputMap, actionMap));
        int acceleratorKey = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
        undo = new CalcButton(new CalcAction(
                new Undo(calc, mediator, history),
                "Undo", "Undo the last action", VK_Z, acceleratorKey, "undo"
        ).putInto(inputMap, actionMap));
        add(clearInputField);
        add(uploadToAccumulator);
        add(backspace);
        add(modulo);
        add(digits[7]);
        add(digits[8]);
        add(digits[9]);
        add(multiplication);
        add(digits[4]);
        add(digits[5]);
        add(digits[6]);
        add(division);
        add(digits[1]);
        add(digits[2]);
        add(digits[3]);
        add(sum);
        add(digits[0]);
        add(dot);
        add(undo);
        add(difference);
    }
    
    public JButton digit(int i) {
        return digits[i];
    }
    
    public JButton dot() {
        return dot;
    }
    
    public JButton clearInputField() {
        return clearInputField;
    }
    
    public JButton uploadToAccumulator() {
        return uploadToAccumulator;
    }
    
    public JButton backspace() {
        return backspace;
    }
    
    public JButton sum() {
        return sum;
    }
    
    public JButton difference() {
        return difference;
    }
    
    public JButton multiplication() {
        return multiplication;
    }
    
    public JButton division() {
        return division;
    }
    
    public JButton modulo() {
        return modulo;
    }
    
    public static final Font BUTTON_FONT = new Font(MONOSPACED, PLAIN, 22);
    
}
