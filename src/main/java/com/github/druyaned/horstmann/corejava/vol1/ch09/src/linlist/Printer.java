package com.github.druyaned.horstmann.corejava.vol1.ch09.src.linlist;

import java.io.PrintStream;

/**
 * Printer of the {@link LinkedList}.
 * @author druyaned
 */
public class Printer {
    
    /**
     * Prints the linked list.
     * 
     * <P><i>Example of view</i><pre>
     *  5 2 |3 1 8 4
     * </pre>
     * 
     * @param <T> the type of value maintained by the linked list
     * @param linlist to be printed
     * @param filler fills the empty space between the values in a line
     * @param sout to print the linked list
     */
    public static<T> void print(LinkedList<T> linlist, String filler, PrintStream sout) {
        checkArgs(linlist, sout);
        if (linlist.isEmpty()) {
            sout.println("The linked list is empty.");
            return;
        }
        if (filler == null) {
            filler = " ";
        }
        Node<T> node = linlist.first;
        if (linlist.iter.next == node) {
            sout.print('|');
        }
        sout.print(node.value.toString());
        for (node = node.next; node != null; node = node.next) {
            if (linlist.iter.next == node) {
                sout.print('|');
            }
            sout.print(filler + node.value.toString());
        }
        if (linlist.iter.next == null) {
            sout.print('|');
        }
        sout.println();
    }
    
    private static<T> void checkArgs(LinkedList<T> linlist, PrintStream sout) {
        if (linlist == null) {
            throw new NullPointerException("the list is null");
        }
        if (sout == null) {
            throw new NullPointerException("the stream is null");
        }
    }
    
}
