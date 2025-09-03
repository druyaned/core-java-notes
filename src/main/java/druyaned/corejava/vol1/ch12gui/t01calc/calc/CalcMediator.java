package druyaned.corejava.vol1.ch12gui.t01calc.calc;

import druyaned.corejava.vol1.ch12gui.t01calc.cmd.CalcCommand;

public interface CalcMediator {
    
    void informSetAccumulator(CalcCommand action);
    
    void informUpdateInput(CalcCommand action);
    
}
