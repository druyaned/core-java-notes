package druyaned.corejava.vol1.ch09collections.t05linlist.cmd;

import druyaned.corejava.vol1.ch09collections.t05linlist.LinkedList;
import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;

public class MoveForward extends AbstractCommand<LinkedList<Integer>> {
    
    public MoveForward(LinkedList<Integer> target) {
        super(target);
    }
    
    @Override public String code() {
        return "mvf";
    }
    
    @Override public String description() {
        return "Move forward";
    }
    
    @Override public String format() {
        return "mvf";
    }
    
    @Override public String run(String[] inputParts) {
        return Boolean.toString(target.iterator().moveForward());
    }
    
}
