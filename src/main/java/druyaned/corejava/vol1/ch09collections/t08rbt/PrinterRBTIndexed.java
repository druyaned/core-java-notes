package druyaned.corejava.vol1.ch09collections.t08rbt;

import static druyaned.ConsoleColors.bold;
import static druyaned.ConsoleColors.redBold;
import druyaned.corejava.vol1.ch09collections.util.AbstractPrinter;
import druyaned.corejava.vol1.ch09collections.t01deque.Deque;
import druyaned.corejava.vol1.ch09collections.t06rbt.RBTIndexed;
import java.io.PrintStream;

public class PrinterRBTIndexed extends AbstractPrinter<RBTIndexed> {
    
    public PrinterRBTIndexed(RBTIndexed target, String filler, PrintStream sout) {
        super(target, filler, sout);
    }
    
    /**
     * Prints the tree.
     * 
     * <P><i>Example of the view</i><pre>
     *             10              
     *      7              21      
     *  5      8       11      27  
     *   6               12        
     * </pre>
     */
    @Override public void print() {
        if (target.isEmpty()) {
            sout.println("The tree is empty.");
            return;
        }
        int treeHeight = getTreeHeight();
        RBTIndexed.Node[] treeArray = getTreeArray(treeHeight);
        int maxValLen = getMaxValLen();
        for (int k = 0; k < treeHeight; k++) {
            int indentFactor = (1 << (treeHeight - 1 - k)) - 1;
            int gapFactor = (1 << (treeHeight - k)) - 1;
            int valCount = 1 << k;
            sout.print(filler.repeat(indentFactor * maxValLen));
            int begin = valCount - 1;
            int end = begin + valCount;
            // the first in the line
            printNode(treeArray[begin], maxValLen, begin);
            for (int i = begin + 1; i < end; i++) {
                RBTIndexed.Node node = treeArray[i];
                sout.print(filler.repeat(gapFactor * maxValLen));
                printNode(node, maxValLen, i);
            }
            sout.print(filler.repeat(indentFactor * maxValLen));
            sout.println();
        }
        sout.println("size=" + target.size());
    }
    
    private int getTreeHeight() {
        int treeHeight = 1;
        Deque<NodeAndNumber> deque = new Deque<>(target.size());
        deque.addLast(new NodeAndNumber(target.root(), 1));
        while (!deque.isEmpty()) {
            NodeAndNumber pair = deque.removeFirst();
            RBTIndexed.Node node = pair.node;
            int height = pair.number;
            if (treeHeight < height)
                treeHeight = height;
            if (node.left() != null)
                deque.addLast(new NodeAndNumber(node.left(), height + 1));
            if (node.right() != null)
                deque.addLast(new NodeAndNumber(node.right(), height + 1));
        }
        return treeHeight;
    }
    
    private RBTIndexed.Node[] getTreeArray(int treeHeight) {
        int capacity = (1 << treeHeight) - 1;
        RBTIndexed.Node[] array = new RBTIndexed.Node[capacity];
        Deque<NodeAndNumber> deque = new Deque<>(target.size());
        deque.addLast(new NodeAndNumber(target.root(), 0));
        while (!deque.isEmpty()) {
            NodeAndNumber pair = deque.removeFirst();
            RBTIndexed.Node node = pair.node;
            int index = pair.number;
            array[index] = node;
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            if (node.left() != null)
                deque.addLast(new NodeAndNumber(node.left(), leftChildIndex));
            if (node.right() != null)
                deque.addLast(new NodeAndNumber(node.right(), rightChildIndex));
        }
        return array;
    }
    
    private int getMaxValLen() {
        final int[] maxValLen = { 1 };
        target.forEach(node -> {
            int valLen = Integer.toString(node.value()).length();
            if (maxValLen[0] < valLen)
                maxValLen[0] = valLen;
        });
        return maxValLen[0];
    }
    
    private void printNode(RBTIndexed.Node node, int maxValLen, int index) {
        String valString = node == null ? filler : Integer.toString(node.value());
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
        if (node == null)
            sout.print(leftIndent + valString + rightIndent);
        else if (node.color() == RBTIndexed.Color.BLACK)
            sout.print(leftIndent + bold(valString) + rightIndent);
        else
            sout.print(leftIndent + redBold(valString) + rightIndent);
    }
    
    private static class NodeAndNumber {
        private final RBTIndexed.Node node;
        private final int number;
        private NodeAndNumber(RBTIndexed.Node node, int number) {
            this.node = node;
            this.number = number;
        }
    }
    
}
/*
Printing the RBT Notes:
                                             ***
                     ***                                             ***
         ***                     ***                     ***                     ***
   ***         ***         ***         ***         ***         ***         ***         ***
***   ***   ***   ***   ***   ***   ***   ***   ***   ***   ***   ***   ***   ***   ***   ***
maxValLen = 3;
treeHeight = 5;
treeCapacity = (1 << treeHeight) - 1 = 31;
| 1 | indent = 15 * maxValLen;
| 2 | indent = 7 * maxValLen; gap = 15 * maxValLen
| 3 | indent = 3 * maxValLen; gap = 7 * maxValLen
| 4 | indent = 1 * maxValLen; gap = 3 * maxValLen
| 5 | indent = 0 * maxValLen; gap = 1 * maxValLen
for i in [0, treeHeight) {
    indentFactor = (1 << (treeHeight - 1 - i)) - 1;
    gapFactor = (1 << (treeHeight - i)) - 1;
    valCount = 1 << i;
    startIndex = valCount - 1;
}

Printing a Value Notes:
Way1 {
    variables   |   left child illustration | right child illustration
    ------------+---------------------------+-------------------------
    maxValLen=8 |       **|****|**          |       **|****|**        
    val1Len=3   |         | ***|            |         |*** |          
    val2Len=4   |         |****|            |         |****|          
}
Way2 {
    variables   |   left child illustration | right child illustration
    ------------+---------------------------+-------------------------
    maxValLen=9 |       **|*****|**         |       **|*****|**       
    val1Len=4   |         | ****|           |         |**** |         
    val2Len=5   |         |*****|           |         |*****|         
}

TreeArray Notes:
       0
   1       2
 3   4   5   6
7 8 9
----
0 1 2 3 4 5 6 7 8 9
0: 1 2
1: 3 4
2: 5 6
3: 7 8
l = 2 * i + 1
r = 2 * i + 2
nodeIndex % 2 == 1 => left child
nodeIndex % 2 == 0 => right child
*/
