package com.github.druyaned.corejava.vol1.ch09.src.rbt;

import static com.github.druyaned.ConsoleColors.bold;
import static com.github.druyaned.ConsoleColors.redBold;
import com.github.druyaned.corejava.vol1.ch09.src.deque.Deque;
import java.io.PrintStream;

/**
 * Printer of the {@link RedBlackTree Red-Black Tree}.
 * @author druyaned
 */
public class Printer {
    
    /**
     * Prints the tree.
     * 
     * <P><i>Example of the view</i><pre>
     *             10              
     *      7              21      
     *  5      8       11      27  
     *   6               12        
     * </pre>
     * 
     * @param <T> the type of value maintained by the tree
     * @param tree to be printed
     * @param filler fills the empty space between the values in a line
     * @param sout to print the tree
     */
    public static<T> void print(RedBlackTree<T> tree, String filler, PrintStream sout) {
        checkArgs(tree, sout);
        if (tree.isEmpty()) {
            sout.println("The tree is empty.");
            return;
        }
        if (filler == null) {
            filler = " ";
        }
        int treeHeight = getTreeHeight(tree);
        Object[] treeArray = getTreeArray(tree, treeHeight);
        int maxValLen = getMaxValLen(tree);
        for (int k = 0; k < treeHeight; k++) {
            int indentFactor = (1 << (treeHeight - 1 - k)) - 1;
            int gapFactor = (1 << (treeHeight - k)) - 1;
            int valCount = 1 << k;
            sout.print(filler.repeat(indentFactor * maxValLen));
            int begin = valCount - 1;
            int end = begin + valCount;
            { // the first in the line
                Node<T> node = (Node<T>)treeArray[begin];
                printNode(node, maxValLen, begin, filler, sout);
            }
            for (int i = begin + 1; i < end; i++) {
                Node<T> node = (Node<T>)treeArray[i];
                sout.print(filler.repeat(gapFactor * maxValLen));
                printNode(node, maxValLen, i, filler, sout);
            }
            sout.print(filler.repeat(indentFactor * maxValLen));
            sout.println();
        }
    }
    
    private static<T> void checkArgs(RedBlackTree<T> tree, PrintStream sout) {
        if (tree == null) {
            throw new NullPointerException("the tree is null");
        }
        if (sout == null) {
            throw new NullPointerException("the stream is null");
        }
    }
    
    private static<T> int getTreeHeight(RedBlackTree<T> tree) {
        int treeHeight = 1;
        Deque<Pair<T>> deque = new Deque<>(tree.size());
        deque.addLast(new Pair<>(tree.getRoot(), 1));
        while (!deque.isEmpty()) {
            Pair<T> pair = deque.popFirst();
            Node<T> node = pair.node;
            int height = pair.number;
            if (treeHeight < height) {
                treeHeight = height;
            }
            if (node.left != null) {
                deque.addLast(new Pair<>(node.left, height + 1));
            }
            if (node.right != null) {
                deque.addLast(new Pair<>(node.right, height + 1));
            }
        }
        return treeHeight;
    }
    
    private static<T> Object[] getTreeArray(RedBlackTree<T> tree, int treeHeight) {
        int capacity = (1 << treeHeight) - 1;
        Object[] array = new Object[capacity];
        Deque<Pair<T>> deque = new Deque<>(tree.size());
        deque.addLast(new Pair<>(tree.getRoot(), 0));
        while (!deque.isEmpty()) {
            Pair<T> pair = deque.popFirst();
            Node<T> node = pair.node;
            int index = pair.number;
            array[index] = node;
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            if (node.left != null) {
                deque.addLast(new Pair<>(node.left, leftChildIndex));
            }
            if (node.right != null) {
                deque.addLast(new Pair<>(node.right, rightChildIndex));
            }
        }
        return array;
    }
    
    private static<T> int getMaxValLen(RedBlackTree<T> tree) {
        final int[] maxValLen = { 1 };
        tree.forEach(node -> {
            int valLen = node.getValue().toString().length();
            if (maxValLen[0] < valLen) {
                maxValLen[0] = valLen;
            }
        });
        return maxValLen[0];
    }
    
    private static<T> void printNode(
            Node<T> node, int maxValLen, int index, String filler, PrintStream sout
    ) {
        String valString = node == null ? filler : node.getValue().toString();
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
        if (node == null) {
            sout.print(leftIndent + valString + rightIndent);
        } else if (node.getColor() == Color.BLACK) {
            sout.print(leftIndent + bold(valString) + rightIndent);
        } else {
            sout.print(leftIndent + redBold(valString) + rightIndent);
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
