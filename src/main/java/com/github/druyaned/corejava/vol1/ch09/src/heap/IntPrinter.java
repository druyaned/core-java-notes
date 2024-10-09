package com.github.druyaned.corejava.vol1.ch09.src.heap;

import java.io.PrintStream;

/**
 * IntPrinter of the {@link Heap}.
 * @author druyaned
 */
public class IntPrinter {
    
    /**
     * Prints the heap.
     * 
     * <P><i>Example of the view</i><pre>
     *     3  
     *   4   5
     *  7 8 6 
     * </pre>
     * 
     * @param heap to be printed
     * @param filler fills the empty space between the values in a line
     * @param sout to print the heap
     */
    public static void print(IntHeap heap, String filler, PrintStream sout) {
        checkArgs(heap, sout);
        if (heap.isEmpty()) {
            sout.println("The heap is empty.");
            return;
        }
        if (filler == null) {
            filler = " ";
        }
        int heapHeight = getHeapHeight(heap);
        int maxValLen = getMaxValLen(heap);
        for (int k = 0; k < heapHeight; k++) {
            int indentFactor = (1 << (heapHeight - 1 - k)) - 1;
            int gapFactor = (1 << (heapHeight - k)) - 1;
            int valCount = 1 << k;
            sout.print(filler.repeat(indentFactor * maxValLen));
            int begin = valCount - 1;
            int end = begin + valCount;
            { // the first in the line
                printValue(heap, begin, maxValLen, filler, sout);
            }
            for (int i = begin + 1; i < end; i++) {
                sout.print(filler.repeat(gapFactor * maxValLen));
                printValue(heap, i, maxValLen, filler, sout);
            }
            sout.print(filler.repeat(indentFactor * maxValLen));
            sout.println();
        }
    }
    
    private static<T> void checkArgs(IntHeap heap, PrintStream sout) {
        if (heap == null) {
            throw new NullPointerException("the heap is null");
        }
        if (sout == null) {
            throw new NullPointerException("the stream is null");
        }
    }
    
    private static<T> int getHeapHeight(IntHeap heap) {
        int heapHeight = 1;
        int size = heap.size;
        while ((size /= 2) > 0) {
            heapHeight++;
        }
        return heapHeight;
    }
    
    private static<T> int getMaxValLen(IntHeap heap) {
        int maxValLen = 1;
        for (int i = 0; i < heap.size; i++) {
            int valLen = Integer.toString(heap.values[i]).length();
            if (maxValLen < valLen) {
                maxValLen = valLen;
            }
        }
        return maxValLen;
    }
    
    private static<T> void printValue(
            IntHeap heap, int index, int maxValLen, String filler, PrintStream sout
    ) {
        String valString = index >= heap.size ? filler : Integer.toString(heap.values[index]);
        int valLen = valString.length();
        int indent = (maxValLen - valLen) / 2;
        String leftIndent = filler.repeat(indent);
        String rightIndent = leftIndent;
        if (maxValLen % 2 == 1) {
            if (valLen % 2 == 0) {
                if (index % 2 == 1) {
                    leftIndent += filler;
                } else {
                    rightIndent += filler;
                }
            }
        } else {
            if (valLen % 2 == 1) {
                if (index % 2 == 1) {
                    leftIndent += filler;
                } else {
                    rightIndent += filler;
                }
            }
        }
        sout.print(leftIndent + valString + rightIndent);
    }
    
}