package druyaned.corejava.vol1.ch09collections.t01deque.cmd;

import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;
import druyaned.corejava.vol1.ch09collections.t01deque.DequeInt;

public class AddLast extends AbstractCommand<DequeInt> {
    
    public AddLast(DequeInt deque) {
        super(deque);
    }
    
    @Override public String code() {
        return "adl";
    }
    
    @Override public String description() {
        return "Add last";
    }
    
    @Override public String format() {
        return "adl [INT_VAL]";
    }
    
    @Override public String run(String[] inputParts) {
        target.addLast(Integer.parseInt(inputParts[1]));
        return VOID_RESULT;
    }
    
}
