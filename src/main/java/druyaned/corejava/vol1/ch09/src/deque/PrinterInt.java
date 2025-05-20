package druyaned.corejava.vol1.ch09.src.deque;

import druyaned.corejava.vol1.ch09.src.AbstractPrinter;
import java.io.PrintStream;

public class PrinterInt extends AbstractPrinter<DequeInt> {
    
    public PrinterInt(DequeInt target, String filler, PrintStream sout) {
        super(target, filler, sout);
    }
    
    /**
     * Prints the deque.
     * 
     * <P><i>Example#1 of view</i><pre>
     *  * * 5 7 2 8 4 *
     * </pre>
     * <i>Example#2 of view</i><pre>
     *  5 7 2 * * * 8 4
     * </pre>
     */
    @Override public void print() {
        if (target.isEmpty()) {
            sout.println("The deque is empty.");
            return;
        }
        int maxValLen = getMaxValLen();
        printValue(0, maxValLen);
        for (int i = 1; i < target.getCapacity(); i++) {
            sout.print(filler.repeat(maxValLen));
            printValue(i, maxValLen);
        }
        sout.println();
    }
    
    private int getMaxValLen() {
        int maxValLen = 1;
        for (int i = 0; i < target.getSize(); i++) {
            int valLen = Integer.toString(target.get(i)).length();
            if (maxValLen < valLen)
                maxValLen = valLen;
        }
        return maxValLen;
    }
    
    private void printValue(int index, int maxValLen) {
        String valString = "*".repeat(maxValLen);
        int head = target.getHead();
        int tail = target.getTail();
        int capacity = target.getCapacity();
        if (head <= tail && head <= index && index <= tail)
            valString = Integer.toString(target.get(index - head));
        if (head > tail && head <= index && index < capacity)
            valString = Integer.toString(target.get(index - head));
        if (head > tail && 0 <= index && index <= tail)
            valString = Integer.toString(target.get(index - head + capacity));
        int valLen = valString.length();
        int indent = (maxValLen - valLen) / 2;
        String leftIndent = filler.repeat(indent);
        String rightIndent = leftIndent;
        if (maxValLen % 2 == 1 && valLen % 2 == 0 || maxValLen % 2 == 0 && valLen % 2 == 1) {
            leftIndent += filler;
        }
        sout.print(leftIndent + valString + rightIndent);
    }
    
}
