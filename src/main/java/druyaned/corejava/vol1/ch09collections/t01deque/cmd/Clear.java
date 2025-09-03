package druyaned.corejava.vol1.ch09collections.t01deque.cmd;

import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;
import druyaned.corejava.vol1.ch09collections.t01deque.DequeInt;

public class Clear extends AbstractCommand<DequeInt> {
    
    public Clear(DequeInt deque) {
        super(deque);
    }
    
    @Override public String code() {
        return "clr";
    }
    
    @Override public String description() {
        return "Clear";
    }
    
    @Override public String format() {
        return "clr";
    }
    
    @Override public String run(String[] inputParts) {
        target.clear();
        return VOID_RESULT;
    }
    
}
