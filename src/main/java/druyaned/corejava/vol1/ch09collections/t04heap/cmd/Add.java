package druyaned.corejava.vol1.ch09collections.t04heap.cmd;

import druyaned.corejava.vol1.ch09collections.t04heap.HeapInt;
import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;

public class Add extends AbstractCommand<HeapInt> {
    
    public Add(HeapInt target) {
        super(target);
    }
    
    @Override public String code() {
        return "a";
    }
    
    @Override public String description() {
        return "Add value";
    }
    
    @Override public String format() {
        return "a [INT_VAL]";
    }
    
    @Override public String run(String[] inputParts) {
        target.add(Integer.parseInt(inputParts[1]));
        return VOID_RESULT;
    }
    
}
