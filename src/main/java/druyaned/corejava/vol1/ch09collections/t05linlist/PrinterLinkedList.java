package druyaned.corejava.vol1.ch09collections.t05linlist;

import druyaned.corejava.vol1.ch09collections.util.AbstractPrinter;
import java.io.PrintStream;

public class PrinterLinkedList<T> extends AbstractPrinter<LinkedList<T>> {

    public PrinterLinkedList(LinkedList<T> target, String filler, PrintStream sout) {
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
        LinkedList.Node<T> node = target.first();
        if (target.iterator().next() == node)
            sout.print('|');
        sout.print(node.value().toString());
        for (node = node.next(); node != null; node = node.next()) {
            if (target.iterator().next() == node)
                sout.print('|');
            sout.print(filler + node.value().toString());
        }
        if (target.iterator().next() == null)
            sout.print('|');
        sout.println("\nsize=" + target.size());
    }
    
}
