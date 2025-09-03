package druyaned.corejava.vol1.ch09collections.t05linlist.cmd;

import druyaned.corejava.vol1.ch09collections.t05linlist.LinkedList;
import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;

public class MoveBack extends AbstractCommand<LinkedList<Integer>> {
    
    public MoveBack(LinkedList<Integer> target) {
        super(target);
    }
    
    @Override public String code() {
        return "mvb";
    }
    
    @Override public String description() {
        return "Move back";
    }
    
    @Override public String format() {
        return "mvb";
    }
    
    @Override public String run(String[] inputParts) {
        return Boolean.toString(target.iterator().moveBack());
    }
    
}
