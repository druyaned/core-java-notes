package com.github.druyaned.corejava.vol1.ch09.src.deque;

import java.io.PrintStream;

/**
 * Printer of the {@link IntDeque}.
 * @author druyaned
 */
public class IntPrinter {
    
    /**
     * Prints the deque.
     * 
     * <P><i>Example#1 of view</i><pre>
     *  * * 5 7 2 8 4 *
     * </pre>
     * <i>Example#2 of view</i><pre>
     *  5 7 2 * * * 8 4
     * </pre>
     * 
     * @param deque to be printed
     * @param filler fills the empty space between the values in a line
     * @param sout to print the deque
     */
    public static void print(IntDeque deque, String filler, PrintStream sout) {
        checkArgs(deque, sout);
        if (deque.isEmpty()) {
            sout.println("The deque is empty.");
            return;
        }
        if (filler == null) {
            filler = " ";
        }
        int maxValLen = getMaxValLen(deque);
        printValue(deque, 0, maxValLen, filler, sout);
        for (int i = 1; i < deque.capacity; i++) {
            sout.print(filler.repeat(maxValLen));
            printValue(deque, i, maxValLen, filler, sout);
        }
        sout.println();
    }
    
    private static void checkArgs(IntDeque deque, PrintStream sout) {
        if (deque == null) {
            throw new NullPointerException("the deque is null");
        }
        if (sout == null) {
            throw new NullPointerException("the stream is null");
        }
    }
    
    private static int getMaxValLen(IntDeque deque) {
        int maxValLen = 1;
        for (int i = 0; i < deque.size; i++) {
            int valLen = Integer.toString(deque.get(i)).length();
            if (maxValLen < valLen) {
                maxValLen = valLen;
            }
        }
        return maxValLen;
    }
    
    private static void printValue(
            IntDeque deque, int index, int maxValLen, String filler, PrintStream sout
    ) {
        String valString;
        if (contains(deque, index)) {
            valString = Integer.toString(deque.values[index]);
        } else {
            valString = "*".repeat(maxValLen);
        }
        int valLen = valString.length();
        int indent = (maxValLen - valLen) / 2;
        String leftIndent = filler.repeat(indent);
        String rightIndent = leftIndent;
        if (maxValLen % 2 == 1 && valLen % 2 == 0 || maxValLen % 2 == 0 && valLen % 2 == 1) {
            leftIndent += filler;
        }
        sout.print(leftIndent + valString + rightIndent);
    }
    
    private static boolean contains(IntDeque deque, int index) {
        if (deque.head <= deque.tail) {
            return deque.head <= index && index <= deque.tail;
        } else {
            return 0 <= index && index <= deque.tail ||
                    deque.head <= index && index < deque.capacity;
        }
    }
    
}
