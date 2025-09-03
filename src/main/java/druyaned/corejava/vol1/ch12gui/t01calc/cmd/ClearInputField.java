package druyaned.corejava.vol1.ch12gui.t01calc.cmd;

import druyaned.corejava.vol1.ch09collections.t01deque.Deque;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.CalcMediator;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.CalcMemento;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.Calculator;

public class ClearInputField extends CalcCommandAbstract {
    
    public ClearInputField(
            Calculator calc,
            CalcMediator mediator,
            Deque<CalcMemento> history
    ) {
        super(calc, mediator, history);
    }
    
    @Override public void execute() {
        if (calc.input().isEmpty())
            return;
        historyAppend();
        calc.input().clear();
        mediator.informUpdateInput(this);
    }
    
}
