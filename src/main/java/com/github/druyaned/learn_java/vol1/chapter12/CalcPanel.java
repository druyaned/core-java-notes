package com.github.druyaned.learn_java.vol1.chapter12;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.*;

import javax.swing.*;

import static com.github.druyaned.learn_java.vol1.chapter12.CalcCommand.*;
import static java.awt.Font.MONOSPACED;
import static java.awt.Font.PLAIN;
import static javax.swing.KeyStroke.getKeyStroke;
import static javax.swing.text.DefaultEditorKit.deletePrevCharAction;

public class CalcPanel extends JPanel {
    
    // Static part

    /** Helps to change private fields in a CalcPanel. */
    public static class AllYouNeed {
        public StringBuilder numberBuilder;
        public JButton[] digits;
        public Map<CalcCommand, JButton> commands;
        public JTextField display;
        public InputMap iMap;
        public ActionMap aMap;
        public DoubleSupplier getPrevResult;
        public DoubleConsumer setPrevResult;
        public Supplier<CalcCommand> getPrevCommand;
        public Consumer<CalcCommand> setPrevCommand;
        public IntSupplier getIsStart;
        public IntSupplier getIsDotUsed;
        public IntConsumer setIsDotUsed;
        public Runnable setStart;
        public Runnable unsetStart;

        public AllYouNeed(
            StringBuilder numberBuilder, JButton[] digits, Map<CalcCommand, JButton> commands,
            JTextField display, InputMap iMap, ActionMap aMap,
            DoubleSupplier getPrevResult, DoubleConsumer setPrevResult,
            Supplier<CalcCommand> getPrevCommand, Consumer<CalcCommand> setPrevCommand,
            IntSupplier getIsStart, IntSupplier getIsDotUsed, IntConsumer setIsDotUsed,
            Runnable setStart, Runnable unsetStart
        ) {
            this.numberBuilder = numberBuilder; this.digits = digits; this.commands = commands;
            this.getPrevResult = getPrevResult; this.setPrevResult = setPrevResult;
            this.getPrevCommand = getPrevCommand; this.setPrevCommand = setPrevCommand;
            this.getIsStart = getIsStart;
            this.getIsDotUsed = getIsDotUsed; this.setIsDotUsed = setIsDotUsed;
            this.setStart = setStart; this.unsetStart = unsetStart;
            this.display = display; this.iMap = iMap; this.aMap = aMap;
        }
    }

    // Frame bounds and fonts
    public static final int WIDTH;
    public static final int HEIGHT;
    public static final int X;
    public static final int Y;
    private static final float HEIGHT_WIDTH_RATIO = 1.25F;
    private static final int SCREEN_PART = 3;
    public static final int DISPLAY_FONT_SIZE;
    public static final int BUTTON_FONT_SIZE;
    private static final Font DISPLAY_FONT;
    private static final Font BUTTON_FONT;
    static {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        WIDTH = (int)(d.getWidth() / SCREEN_PART);
        HEIGHT = (int)(WIDTH * HEIGHT_WIDTH_RATIO);
        X = (int)(d.getWidth() / 2) - WIDTH / 2;
        Y = (int)(d.getHeight() / 2) - HEIGHT / 2;
        DISPLAY_FONT_SIZE = WIDTH / 16;
        BUTTON_FONT_SIZE = WIDTH / 16;
        DISPLAY_FONT = new Font(MONOSPACED, PLAIN, DISPLAY_FONT_SIZE);
        BUTTON_FONT = new Font(MONOSPACED, PLAIN, BUTTON_FONT_SIZE);
    }

    // Non-static part

    private JTextField display;
    private JPanel buttonsPanel;
    private JButton[] digits;
    private Map<CalcCommand, JButton> commands;
    private double prevResult;
    private CalcCommand prevCommand;
    private boolean isStart;
    private boolean isDotUsed;
    private StringBuilder numberBuilder;
    private InputMap iMap;
    private ActionMap aMap;
    private AllYouNeed allYouNeed;

    public CalcPanel() {
        display = new JTextField(Double.toString(0D));
        buttonsPanel = new JPanel(new GridLayout(5, 4));
        prevResult = 0D;
        prevCommand = SUM;
        numberBuilder = new StringBuilder();
        
        JPanel textAndButtons = new JPanel(new BorderLayout());
        iMap = textAndButtons.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        aMap = textAndButtons.getActionMap();

        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.CENTER);

        display.setFont(DISPLAY_FONT);
        display.getActionMap().get(deletePrevCharAction).setEnabled(false);

        // Creating buttons
        commands = new HashMap<>();
        commands.put(EQUALITY, new JButton(EQUALITY.getText()));
        commands.put(DIVISION, new JButton(DIVISION.getText()));
        commands.put(MULTIPLICATION, new JButton(MULTIPLICATION.getText()));
        commands.put(DIFFERENCE, new JButton(DIFFERENCE.getText()));
        commands.put(SUM, new JButton(SUM.getText()));
        commands.put(DOT, new JButton(DOT.getText()));
        commands.put(BACKSPACE, new JButton(BACKSPACE.getText()));
        commands.put(ALL_CLEAR, new JButton(ALL_CLEAR.getText()));
        digits = new JButton[10];
        for (int i = 0; i < 10; ++i) {
            digits[i] = new JButton(Integer.toString(i));
        }
        allYouNeed = new AllYouNeed(
            numberBuilder, digits, commands, display, iMap, aMap,
            this::getPrevResult, this::setPrevResult,
            this::getPrevCommand, this::setPrevCommand,
            this::getIsStart, this::getIsDotUsed, this::setIsDotUsed,
            this::setStart, this::unsetStart
        );
        
        // Adding buttons in a correct order
        JButton emptyButton1 = new JButton();
        JButton emptyButton2 = new JButton();
        emptyButton1.setEnabled(false);
        emptyButton2.setEnabled(false);
        buttonsPanel.add(emptyButton1);
        buttonsPanel.add(emptyButton2);
        setCommand(ALL_CLEAR);
        setCommand(BACKSPACE);
        setDigit(7);
        setDigit(8);
        setDigit(9);
        setCommand(DIVISION);
        setDigit(4);
        setDigit(5);
        setDigit(6);
        setCommand(MULTIPLICATION);
        setDigit(1);
        setDigit(2);
        setDigit(3);
        setCommand(DIFFERENCE);
        setDigit(0);
        setCommand(DOT);
        setCommand(EQUALITY);
        setCommand(SUM);
        
        setStart();
        textAndButtons.add(display, BorderLayout.NORTH);
        textAndButtons.add(buttonsPanel, BorderLayout.CENTER);
        setLayout(new BorderLayout());
        add(textAndButtons, BorderLayout.CENTER);
    }

    // Getters
    private double getPrevResult() {return prevResult;}
    private CalcCommand getPrevCommand() {return prevCommand;}
    /** @return {@code 1} if {@code isStart=true}, else {@code 0}.*/
    private int getIsStart() {return isStart ? 1 : 0;}
    /** @return {@code 1} if {@code isDotUsed=true}, else {@code 0}.*/
    private int getIsDotUsed() {return isDotUsed ? 1 : 0;}

    // Setters
    private void setPrevResult(double prevResult) {this.prevResult = prevResult;}
    private void setPrevCommand(CalcCommand prevCommand) {this.prevCommand = prevCommand;}
    /** @param isDotUsed {@code 1} if {@code isDotUsed=true}, else {@code 0}.*/
    private void setIsDotUsed(int isDotUsed) {this.isDotUsed = (isDotUsed == 1) ? true : false;}

    // Setting digits, commands, start and unsetting start
    
    private DigitAction setDigit(int i) {
        digits[i].setFont(BUTTON_FONT);
        DigitAction digitAction = new DigitAction(i, allYouNeed);
        digits[i].setAction(digitAction);
        buttonsPanel.add(digits[i]);

        KeyStroke keyStroke = getKeyStroke(CalcDigits.getKeyCode(i), CalcDigits.getMod());
        iMap.put(keyStroke, CalcDigits.getMapKey(i));
        aMap.put(CalcDigits.getMapKey(i), digitAction);
        return digitAction;
    }

    private CommandAction setCommand(CalcCommand command) {
        JButton button = commands.get(command);
        button.setFont(BUTTON_FONT);
        CommandAction commandAction = new CommandAction(command, allYouNeed);
        button.setAction(commandAction);
        buttonsPanel.add(button);
        
        KeyStroke keyStroke = getKeyStroke(command.getKeyCode(), command.getMod());
        iMap.put(keyStroke, command.getMapKey());
        aMap.put(command.getMapKey(), commandAction);
        return commandAction;
    }

    private void setStart() {
        int exponent = Math.getExponent(prevResult);
        if (prevResult != 0D && (exponent < -30 || exponent > 30)) {
            display.setText(String.format("%.15e", prevResult));
        } else {
            display.setText(Double.toString(prevResult));
        }
        if (numberBuilder.length() != 0) {numberBuilder.delete(0, numberBuilder.length());}
        isStart = true;
        aMap.get(DOT.getMapKey()).setEnabled(false);
        commands.get(DOT).setEnabled(false);
        aMap.get(BACKSPACE.getMapKey()).setEnabled(false);
        commands.get(BACKSPACE).setEnabled(false);
        isDotUsed = false;
    }
    
    private void unsetStart() {
        isStart = false;
        aMap.get(DOT.getMapKey()).setEnabled(true);
        commands.get(DOT).setEnabled(true);
        aMap.get(BACKSPACE.getMapKey()).setEnabled(true);
        commands.get(BACKSPACE).setEnabled(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
}
