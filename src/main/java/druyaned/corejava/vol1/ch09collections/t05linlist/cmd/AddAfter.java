package druyaned.corejava.vol1.ch09collections.t05linlist.cmd;

import druyaned.corejava.vol1.ch09collections.t05linlist.LinkedList;
import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;

public class AddAfter extends AbstractCommand<LinkedList<Integer>> {
    
    public AddAfter(LinkedList<Integer> target) {
        super(target);
    }
    
    @Override public String code() {
        return "ada";
    }
    
    @Override public String description() {
        return "Add after";
    }
    
    @Override public String format() {
        return "ada [INT_VAL]";
    }
    
    @Override public String run(String[] inputParts) {
        target.iterator().addAfter(Integer.valueOf(inputParts[1]));
        return VOID_RESULT;
    }
    
}
