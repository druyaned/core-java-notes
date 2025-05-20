package druyaned.corejava.vol1.ch09.src.linlist;

import druyaned.corejava.vol1.ch09.src.AbstractPrinter;
import java.io.PrintStream;

public class Printer<T> extends AbstractPrinter<LinkedList<T>> {

    public Printer(LinkedList<T> target, String filler, PrintStream sout) {
        super(target, filler, sout);
    }
    
    /**
     * Prints the linked list.
     * 
     * <P><i>Example of view</i><pre>
     *  5 2 |3 1 8 4
     * </pre>
     */
    @Override public void print() {
        if (target.isEmpty()) {
            sout.println("The linked list is empty.");
            return;
        }
        LinkedList.Node<T> node = target.firstNode();
        if (target.iterator().nextNode() == node)
            sout.print('|');
        sout.print(node.value().toString());
        for (node = node.next(); node != null; node = node.next()) {
            if (target.iterator().nextNode() == node)
                sout.print('|');
            sout.print(filler + node.value().toString());
        }
        if (target.iterator().nextNode() == null)
            sout.print('|');
        sout.println();
    }
    
}
