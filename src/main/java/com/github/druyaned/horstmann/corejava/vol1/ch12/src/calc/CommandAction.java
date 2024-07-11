package com.github.druyaned.horstmann.corejava.vol1.ch12.src.calc;

import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import javax.swing.*;
import static com.github.druyaned.horstmann.corejava.vol1.ch12.src.calc.CalcCommand.*;

public class CommandAction extends AbstractAction {
    
    private final StringBuilder numberBuilder;
    private final JButton[] digits;
    private final Map<CalcCommand, JButton> commands;
    private final JTextField display;
    private final ActionMap aMap;
    private final DoubleSupplier getPrevResult;
    private final DoubleConsumer setPrevResult;
    private final Supplier<CalcCommand> getPrevCommand;
    private final Consumer<CalcCommand> setPrevCommand;
    private final IntSupplier getIsStart;
    private final IntSupplier getIsDotUsed;
    private final IntConsumer setIsDotUsed;
    private final Runnable setStart;
    private final CalcCommand command;

    public CommandAction(CalcCommand command, CalcPanel.AllYouNeed allYouNeed) {
        this.numberBuilder = allYouNeed.numberBuilder;
        this.digits = allYouNeed.digits;
        this.commands = allYouNeed.commands;
        this.display = allYouNeed.display;
        this.aMap = allYouNeed.aMap;
        this.getPrevResult = allYouNeed.getPrevResult;
        this.setPrevResult = allYouNeed.setPrevResult;
        this.getPrevCommand = allYouNeed.getPrevCommand;
        this.setPrevCommand = allYouNeed.setPrevCommand;
        this.getIsStart = allYouNeed.getIsStart;
        this.getIsDotUsed = allYouNeed.getIsDotUsed;
        this.setIsDotUsed = allYouNeed.setIsDotUsed;
        this.setStart = allYouNeed.setStart;
        this.command = command;
        putValue(Action.NAME, command.getText());
        putValue(Action.SHORT_DESCRIPTION, command.getDescription());
    }
    
    @Override public void actionPerformed(ActionEvent event) {
        double newResult = 0D;
        if (numberBuilder.length() != 0) {
            newResult = Double.parseDouble(numberBuilder.toString());
        }
        JButton dot = commands.get(DOT);
        if (command == EQUALITY) {
            for (int i = 0; i < 10; ++i) {
                aMap.get(CalcDigits.getMapKey(i)).setEnabled(false);
                digits[i].setEnabled(false);
            }
        } else if (command == ALL_CLEAR) {
            setPrevResult.accept(0D);
            setPrevCommand.accept(SUM);
            setStart.run();
            for (int i = 0; i < 10; ++i) {
                aMap.get(CalcDigits.getMapKey(i)).setEnabled(true);
                digits[i].setEnabled(true);
            }
            return;
        } else if (command == DOT && getIsDotUsed.getAsInt() == 0) {
            numberBuilder.append(DOT.getText());
            setIsDotUsed.accept(1);
            aMap.get(DOT.getMapKey()).setEnabled(false);
            dot.setEnabled(false);
            display.setText(numberBuilder.toString());
            return;
        } else if (command == BACKSPACE) {
            if (numberBuilder.length() > 1) {
                int last = numberBuilder.length() - 1;
                if (numberBuilder.charAt(last) == DOT.getText().charAt(0)) {
                    setIsDotUsed.accept(0);
                    aMap.get(DOT.getMapKey()).setEnabled(true);
                    dot.setEnabled(true);
                }
                numberBuilder.deleteCharAt(last);
                display.setText(numberBuilder.toString());
            } else if (numberBuilder.length() == 1) {
                setStart.run();
                return;
            }
            return;
        } else {
            for (int i = 0; i < 10; ++i) {
                aMap.get(CalcDigits.getMapKey(i)).setEnabled(true);
                digits[i].setEnabled(true);
            }
        }
        if (getIsStart.getAsInt() == 0) {
            calcWith(newResult);
        }
        setPrevCommand.accept(command);
        setStart.run();
    }

    private void calcWith(double newResult) {
        CalcCommand prevCommand = getPrevCommand.get();
        if (null != prevCommand) switch (prevCommand) {
            case DIVISION -> setPrevResult.accept(getPrevResult.getAsDouble() / newResult);
            case MULTIPLICATION -> setPrevResult.accept(getPrevResult.getAsDouble() * newResult);
            case DIFFERENCE -> setPrevResult.accept(getPrevResult.getAsDouble() - newResult);
            case SUM -> setPrevResult.accept(getPrevResult.getAsDouble() + newResult);
            default -> {}
        }
    }
    
}
