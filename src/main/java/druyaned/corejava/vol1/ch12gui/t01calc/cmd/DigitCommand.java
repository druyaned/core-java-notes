package druyaned.corejava.vol1.ch12gui.t01calc.cmd;

import druyaned.corejava.vol1.ch09collections.t01deque.Deque;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.CalcMediator;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.CalcMemento;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.Calculator;

public class DigitCommand extends CalcCommandAbstract {
    
    private final int digit;
    private final char digitChar;
    
    public DigitCommand(
            Calculator calc,
            CalcMediator mediator,
            Deque<CalcMemento> history,
            int digit
    ) {
        super(calc, mediator, history);
        this.digit = digit;
        this.digitChar = (char)('0' + digit);
    }
    
    @Override public void execute() {
        if (calc.input().isFull())
            return;
        if (calc.input().size() == 1 && calc.input().charAt(0) == '0') {
            if (digit == 0)
                return;
            else
                calc.input().removeLast(); // remove first '0'
        }
        calc.input().append(digitChar);
        mediator.informUpdateInput(this);
    }
    
}
