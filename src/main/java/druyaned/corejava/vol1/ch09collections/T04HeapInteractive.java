package druyaned.corejava.vol1.ch09collections;

import druyaned.corejava.Chapter;
import druyaned.corejava.vol1.ch09collections.t04heap.HeapInt;
import druyaned.corejava.vol1.ch09collections.t04heap.PrinterHeapInt;
import druyaned.corejava.vol1.ch09collections.t04heap.cmd.Add;
import druyaned.corejava.vol1.ch09collections.t04heap.cmd.Remove;
import druyaned.corejava.vol1.ch09collections.t04heap.cmd.Root;
import druyaned.corejava.vol1.ch09collections.util.Command;
import druyaned.corejava.vol1.ch09collections.util.InteractiveTopic;

public class T04HeapInteractive extends InteractiveTopic<HeapInt> {
    
    public T04HeapInteractive(Chapter chapter) {
        super(chapter, 4);
    }
    
    @Override public String title() {
        return "Topic 04 HeapInteractive";
    }
    
    @Override protected HeapInt createTarget() {
        return new HeapInt(2, Integer::compare);
    }
    
    @Override protected void addCommands(HeapInt target) {
        Command command;
        commands.put((command = new Root(target)).code(), command);
        commands.put((command = new Add(target)).code(), command);
        commands.put((command = new Remove(target)).code(), command);
    }
    
    @Override protected void setPrinter(HeapInt target) {
        printer = new PrinterHeapInt(target, "_", null);
    }
    
}
