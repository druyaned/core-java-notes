package druyaned.corejava.vol1.ch09collections.t01deque.cmd;

import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;
import druyaned.corejava.vol1.ch09collections.t01deque.DequeInt;

public class Get extends AbstractCommand<DequeInt> {
    
    public Get(DequeInt deque) {
        super(deque);
    }
    
    @Override public String code() {
        return "get";
    }
    
    @Override public String description() {
        return "Get value by index";
    }
    
    @Override public String format() {
        return "get [IND]";
    }
    
    @Override public String run(String[] inputParts) {
        return Integer.toString(target.get(Integer.parseInt(inputParts[1])));
    }
    
}
