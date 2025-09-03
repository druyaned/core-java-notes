package druyaned.corejava.vol1.ch09collections.t01deque.cmd;

import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;
import druyaned.corejava.vol1.ch09collections.t01deque.DequeInt;

public class Set extends AbstractCommand<DequeInt> {
    
    public Set(DequeInt deque) {
        super(deque);
    }
    
    @Override public String code() {
        return "set";
    }
    
    @Override public String description() {
        return "Set value by index";
    }
    
    @Override public String format() {
        return "set [IND] [INT_VAL]";
    }
    
    @Override public String run(String[] inputParts) {
        int index = Integer.parseInt(inputParts[1]);
        int value = Integer.parseInt(inputParts[2]);
        target.set(index, value);
        return VOID_RESULT;
    }
    
}
