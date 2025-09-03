package druyaned.corejava.vol1.ch12gui.t01calc.cmd;

import druyaned.corejava.vol1.ch09collections.t01deque.Deque;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.CalcMediator;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.CalcMemento;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.Calculator;

public class Dot extends CalcCommandAbstract {
    
    public Dot(
            Calculator calc,
            CalcMediator mediator,
            Deque<CalcMemento> history
    ) {
        super(calc, mediator, history);
    }
    
    @Override public void execute() {
        if (calc.input().isFull() || calc.input().dotCount() == 1)
            return;
        if (calc.input().isEmpty())
            calc.input().append('0');
        calc.input().append(DOT_CHAR);
        mediator.informUpdateInput(this);
    }
    
    public static final String DOT_SIGN = ".";
    public static final char DOT_CHAR = '.';
    
}
