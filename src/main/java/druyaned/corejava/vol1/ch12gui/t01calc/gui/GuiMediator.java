package druyaned.corejava.vol1.ch12gui.t01calc.gui;

import druyaned.corejava.vol1.ch12gui.t01calc.calc.CalcMediator;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.Calculator;
import druyaned.corejava.vol1.ch12gui.t01calc.cmd.CalcCommand;

public class GuiMediator implements CalcMediator {
    
    private final Calculator calc;
    private final DisplayPanel displayPanel;
    
    public GuiMediator(Calculator calc, DisplayPanel displayPanel) {
        this.calc = calc;
        this.displayPanel = displayPanel;
    }
    
    @Override public void informSetAccumulator(CalcCommand action) {
        displayPanel.accumField().setText(Double.toString(calc.accumulator()));
    }
    
    @Override public void informUpdateInput(CalcCommand action) {
        displayPanel.inputField().setText(calc.input().toString());
    }
    
}
