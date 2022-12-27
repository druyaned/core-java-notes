package com.github.druyaned.learn_java.vol1.chapter12;

import java.awt.event.ActionEvent;
import java.util.function.IntSupplier;

import javax.swing.*;

public class DigitAction extends AbstractAction {
    private final StringBuilder numberBuilder;
    private final JTextField display;
    private final IntSupplier getIsStart;
    private final Runnable unsetStart;

    public DigitAction(int i, CalcPanel.AllYouNeed allYouNeed) {
        this.numberBuilder = allYouNeed.numberBuilder;
        this.display = allYouNeed.display;
        this.getIsStart = allYouNeed.getIsStart;
        this.unsetStart = allYouNeed.unsetStart;

        putValue(Action.NAME, CalcDigits.getText(i));
        putValue(Action.SHORT_DESCRIPTION, CalcDigits.getDescription(i));
    }
            
    @Override
    public void actionPerformed(ActionEvent event) {
        numberBuilder.append(event.getActionCommand());
        if (getIsStart.getAsInt() == 1) {unsetStart.run();}
        display.setText(numberBuilder.toString());
    }
}
