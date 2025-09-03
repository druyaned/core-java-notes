package druyaned.corejava.vol1.ch09collections.t04heap.cmd;

import druyaned.corejava.vol1.ch09collections.t04heap.HeapInt;
import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;

public class Remove extends AbstractCommand<HeapInt> {
    
    public Remove(HeapInt target) {
        super(target);
    }
    
    @Override public String code() {
        return "r";
    }
    
    @Override public String description() {
        return "Remove root";
    }
    
    @Override public String format() {
        return "r";
    }
    
    @Override public String run(String[] inputParts) {
        return Integer.toString(target.remove());
    }
    
}
