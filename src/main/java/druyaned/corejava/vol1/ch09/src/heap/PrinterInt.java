package druyaned.corejava.vol1.ch09.src.heap;

import druyaned.corejava.vol1.ch09.src.AbstractPrinter;
import java.io.PrintStream;

/**
 * PrinterInt of the {@link Heap}.
 * @author druyaned
 */
public class PrinterInt extends AbstractPrinter<HeapInt> {
    
    public PrinterInt(HeapInt target, String filler, PrintStream sout) {
        super(target, filler, sout);
    }
    
    /**
     * Prints the heap.
     * 
     * <P><i>Example of the view</i><pre>
     *     3  
     *   4   5
     *  7 8 6 
     * </pre>
     */
    @Override public void print() {
        if (target.isEmpty()) {
            sout.println("The heap is empty.");
            return;
        }
        int heapHeight = getHeapHeight();
        int maxValLen = getMaxValLen();
        for (int k = 0; k < heapHeight; k++) {
            int indentFactor = (1 << (heapHeight - 1 - k)) - 1;
            int gapFactor = (1 << (heapHeight - k)) - 1;
            int valCount = 1 << k;
            sout.print(filler.repeat(indentFactor * maxValLen));
            int begin = valCount - 1;
            int end = begin + valCount;
            // the first in the line
            printValue(begin, maxValLen);
            for (int i = begin + 1; i < end; i++) {
                sout.print(filler.repeat(gapFactor * maxValLen));
                printValue(i, maxValLen);
            }
            sout.print(filler.repeat(indentFactor * maxValLen));
            sout.println();
        }
    }
    
    private int getHeapHeight() {
        int heapHeight = 1;
        int size = target.size();
        while ((size /= 2) > 0)
            heapHeight++;
        return heapHeight;
    }
    
    private int getMaxValLen() {
        int maxValLen = 1;
        for (int i = 0; i < target.size(); i++) {
            int valLen = Integer.toString(target.get(i)).length();
            if (maxValLen < valLen)
                maxValLen = valLen;
        }
        return maxValLen;
    }
    
    private void printValue(int index, int maxValLen) {
        String valString = index >= target.size() ? filler : Integer.toString(target.get(index));
        int valLen = valString.length();
        int indent = (maxValLen - valLen) / 2;
        String leftIndent = filler.repeat(indent);
        String rightIndent = leftIndent;
        if (maxValLen % 2 == 1) {
            if (valLen % 2 == 0) {
                if (index % 2 == 1)
                    leftIndent += filler;
                else
                    rightIndent += filler;
            }
        } else {
            if (valLen % 2 == 1) {
                if (index % 2 == 1)
                    leftIndent += filler;
                else
                    rightIndent += filler;
            }
        }
        sout.print(leftIndent + valString + rightIndent);
    }
    
}
