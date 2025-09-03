package druyaned.corejava.vol1.ch09collections;

import druyaned.corejava.Chapter;
import druyaned.corejava.vol1.ch09collections.t06rbt.RBTIndexed;
import druyaned.corejava.vol1.ch09collections.t08rbt.PrinterRBTIndexed;
import druyaned.corejava.vol1.ch09collections.t08rbt.cmd.Add;
import druyaned.corejava.vol1.ch09collections.t08rbt.cmd.GetIndexed;
import druyaned.corejava.vol1.ch09collections.t08rbt.cmd.Remove;
import druyaned.corejava.vol1.ch09collections.util.Command;
import druyaned.corejava.vol1.ch09collections.util.InteractiveTopic;

public class T08RBTInteractive extends InteractiveTopic<RBTIndexed> {
    
    public T08RBTInteractive(Chapter chapter) {
        super(chapter, 8);
    }
    
    @Override public String title() {
        return "Topic 08 RBTInteractive";
    }
    
    @Override protected RBTIndexed createTarget() {
        return new RBTIndexed();
    }
    
    @Override protected void addCommands(RBTIndexed target) {
        Command command;
        commands.put((command = new Add(target)).code(), command);
        commands.put((command = new Remove(target)).code(), command);
        commands.put((command = new GetIndexed(target)).code(), command);
    }
    
    @Override protected void setPrinter(RBTIndexed target) {
        printer = new PrinterRBTIndexed(target, "_", null);
    }
    
}
