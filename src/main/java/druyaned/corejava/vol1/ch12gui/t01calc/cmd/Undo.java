package druyaned.corejava.vol1.ch12gui.t01calc.cmd;

import druyaned.corejava.vol1.ch09collections.t01deque.Deque;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.CalcMediator;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.CalcMemento;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.Calculator;

public class Undo extends CalcCommandAbstract {
    
    public Undo(
            Calculator calc,
            CalcMediator mediator,
            Deque<CalcMemento> history
    ) {
        super(calc, mediator, history);
    }
    
    @Override public void execute() {
        if (history.isEmpty())
            return;
        CalcMemento memento = history.removeLast();
        memento.restore();
        mediator.informSetAccumulator(this);
        mediator.informUpdateInput(this);
    }
    
}
