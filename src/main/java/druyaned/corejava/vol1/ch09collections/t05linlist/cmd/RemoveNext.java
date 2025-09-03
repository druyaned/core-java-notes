package druyaned.corejava.vol1.ch09collections.t05linlist.cmd;

import druyaned.corejava.vol1.ch09collections.t05linlist.LinkedList;
import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;

public class RemoveNext extends AbstractCommand<LinkedList<Integer>> {
    
    public RemoveNext(LinkedList<Integer> target) {
        super(target);
    }
    
    @Override public String code() {
        return "rmn";
    }
    
    @Override public String description() {
        return "Remove next";
    }
    
    @Override public String format() {
        return "rmn";
    }
    
    @Override public String run(String[] inputParts) {
        return target.iterator().removeNext().toString();
    }
    
}
