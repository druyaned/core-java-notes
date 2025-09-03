package druyaned.corejava.vol1.ch09collections.t04heap.cmd;

import druyaned.corejava.vol1.ch09collections.t04heap.HeapInt;
import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;

public class Root extends AbstractCommand<HeapInt> {
    
    public Root(HeapInt target) {
        super(target);
    }
    
    @Override public String code() {
        return "g";
    }
    
    @Override public String description() {
        return "Get root";
    }
    
    @Override public String format() {
        return "g";
    }
    
    @Override public String run(String[] inputParts) {
        return Integer.toString(target.root());
    }
    
}
