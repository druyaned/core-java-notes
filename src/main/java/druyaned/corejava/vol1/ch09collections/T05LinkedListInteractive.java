package druyaned.corejava.vol1.ch09collections;

import druyaned.corejava.Chapter;
import druyaned.corejava.vol1.ch09collections.t05linlist.LinkedList;
import druyaned.corejava.vol1.ch09collections.t05linlist.PrinterLinkedList;
import druyaned.corejava.vol1.ch09collections.t05linlist.cmd.AddAfter;
import druyaned.corejava.vol1.ch09collections.t05linlist.cmd.AddBefore;
import druyaned.corejava.vol1.ch09collections.t05linlist.cmd.Clear;
import druyaned.corejava.vol1.ch09collections.t05linlist.cmd.First;
import druyaned.corejava.vol1.ch09collections.t05linlist.cmd.Last;
import druyaned.corejava.vol1.ch09collections.t05linlist.cmd.MoveAfterLast;
import druyaned.corejava.vol1.ch09collections.t05linlist.cmd.MoveBack;
import druyaned.corejava.vol1.ch09collections.t05linlist.cmd.MoveBeforeFirst;
import druyaned.corejava.vol1.ch09collections.t05linlist.cmd.MoveForward;
import druyaned.corejava.vol1.ch09collections.t05linlist.cmd.Next;
import druyaned.corejava.vol1.ch09collections.t05linlist.cmd.Prev;
import druyaned.corejava.vol1.ch09collections.t05linlist.cmd.RemoveNext;
import druyaned.corejava.vol1.ch09collections.t05linlist.cmd.RemovePrev;
import druyaned.corejava.vol1.ch09collections.util.Command;
import druyaned.corejava.vol1.ch09collections.util.InteractiveTopic;

public class T05LinkedListInteractive extends InteractiveTopic<LinkedList<Integer>> {
    
    public T05LinkedListInteractive(Chapter chapter) {
        super(chapter, 5);
    }
    
    @Override public String title() {
        return "Topic 05 LinkedListInteractive";
    }
    
    @Override protected LinkedList<Integer> createTarget() {
        return new LinkedList();
    }
    
    @Override protected void addCommands(LinkedList<Integer> target) {
        Command command;
        commands.put((command = new First(target)).code(), command);
        commands.put((command = new Last(target)).code(), command);
        commands.put((command = new Clear(target)).code(), command);
        commands.put((command = new Prev(target)).code(), command);
        commands.put((command = new Next(target)).code(), command);
        commands.put((command = new AddBefore(target)).code(), command);
        commands.put((command = new AddAfter(target)).code(), command);
        commands.put((command = new RemovePrev(target)).code(), command);
        commands.put((command = new RemoveNext(target)).code(), command);
        commands.put((command = new MoveBack(target)).code(), command);
        commands.put((command = new MoveForward(target)).code(), command);
        commands.put((command = new MoveBeforeFirst(target)).code(), command);
        commands.put((command = new MoveAfterLast(target)).code(), command);
    }
    
    @Override protected void setPrinter(LinkedList<Integer> target) {
        printer = new PrinterLinkedList(target, "_", null);
    }
    
}
