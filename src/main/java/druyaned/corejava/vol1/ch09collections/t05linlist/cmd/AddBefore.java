package druyaned.corejava.vol1.ch09collections.t05linlist.cmd;

import druyaned.corejava.vol1.ch09collections.t05linlist.LinkedList;
import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;

public class AddBefore extends AbstractCommand<LinkedList<Integer>> {
    
    public AddBefore(LinkedList<Integer> target) {
        super(target);
    }
    
    @Override public String code() {
        return "adb";
    }
    
    @Override public String description() {
        return "Add before";
    }
    
    @Override public String format() {
        return "adb [INT_VAL]";
    }
    
    @Override public String run(String[] inputParts) {
        target.iterator().addBefore(Integer.valueOf(inputParts[1]));
        return VOID_RESULT;
    }
    
}
