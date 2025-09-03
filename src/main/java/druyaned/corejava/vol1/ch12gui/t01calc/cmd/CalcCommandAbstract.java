package druyaned.corejava.vol1.ch12gui.t01calc.cmd;

import druyaned.corejava.vol1.ch09collections.t01deque.Deque;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.CalcMediator;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.CalcMemento;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.Calculator;

public abstract class CalcCommandAbstract implements CalcCommand {
    
    protected final Calculator calc;
    protected final CalcMediator mediator;
    protected final Deque<CalcMemento> history;
    
    public CalcCommandAbstract(
            Calculator calc,
            CalcMediator mediator,
            Deque<CalcMemento> history
    ) {
        this.calc = calc;
        this.mediator = mediator;
        this.history = history;
    }
    
    /**
     * This method should be used before each command usage
     * to store calculator memento for undo-purpose;
     * the history capacity will not be changed, so if it was reached
     * the first element is removed and in any case the last element
     * is appended.
     */
    protected final void historyAppend() {
        if (history.size() == history.capacity())
            history.removeFirst();
        history.addLast(calc.save());
    }
    
}
