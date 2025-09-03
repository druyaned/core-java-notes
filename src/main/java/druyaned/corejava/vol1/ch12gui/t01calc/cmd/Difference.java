package druyaned.corejava.vol1.ch12gui.t01calc.cmd;

import druyaned.corejava.vol1.ch09collections.t01deque.Deque;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.CalcMediator;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.CalcMemento;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.Calculator;

public class Difference extends CalcCommandAbstract {
    
    public Difference(
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
        double inputValue = Double.parseDouble(calc.input().toString());
        double result = calc.accumulator() - inputValue;
        calc.setAccumulator(result);
        mediator.informSetAccumulator(this);
        calc.input().clear();
        mediator.informUpdateInput(this);
    }
    
}
