package druyaned.corejava.vol1.ch09collections.t01deque.cmd;

import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;
import druyaned.corejava.vol1.ch09collections.t01deque.DequeInt;

public class AddFirst extends AbstractCommand<DequeInt> {
    
    public AddFirst(DequeInt deque) {
        super(deque);
    }
    
    @Override public String code() {
        return "adf";
    }
    
    @Override public String description() {
        return "Add first";
    }
    
    @Override public String format() {
        return "adf [INT_VAL]";
    }
    
    @Override public String run(String[] inputParts) {
        target.addFirst(Integer.parseInt(inputParts[1]));
        return VOID_RESULT;
    }
    
}
