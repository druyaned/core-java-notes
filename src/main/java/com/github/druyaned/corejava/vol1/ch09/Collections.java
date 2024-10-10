package com.github.druyaned.corejava.vol1.ch09;

import static com.github.druyaned.corejava.App.sin;
import com.github.druyaned.corejava.Chapter;
import com.github.druyaned.corejava.vol1.ch09.src.TestHashMapVsTreeMap;
import com.github.druyaned.corejava.vol1.ch09.src.TestHeap;
import com.github.druyaned.corejava.vol1.ch09.src.TestDeque;
import com.github.druyaned.corejava.vol1.ch09.src.TestHeapSort;
import com.github.druyaned.corejava.vol1.ch09.src.TestLinkedList;
import com.github.druyaned.corejava.vol1.ch09.src.TestRedBlackMap;
import com.github.druyaned.corejava.vol1.ch09.src.TestRedBlackTree;
import com.github.druyaned.corejava.vol1.ch09.src.rbm.RedBlackMap;
import java.nio.file.Path;

/**
 * Practical implementation of the Chapter#09: Collections.
 * 
 * <P><i>Collection Notes</i>:<pre>
 *       Collection                     Map
 *    ________|________________          |
 *   |        |        |       |      SortedMap
 *  List     Set     Queue   Deque
 *            |
 *        SortedSet
 * </pre>
 * 
 * @author druyaned
 */
public class Collections extends Chapter {
    
    /**
     * Creates the Chapter#09: Collections.
     * @param volDataDir the path to the volume's data-directory
     */
    public Collections(Path volDataDir) {
        super(volDataDir, 9);
    }
    
    @Override public String getTitle() {
        return "Collections";
    }
    
    @Override public void run() {
        RedBlackMap<Integer, TestPair> idToTest = new RedBlackMap<>(Integer::compareTo);
        idToTest.put(1, new TestPair("Deque", () -> TestDeque.interactiveTest()));
        idToTest.put(2, new TestPair("HashMapVsTreeMap", () -> TestHashMapVsTreeMap.run()));
        idToTest.put(3, new TestPair("Heap", () -> TestHeap.interactiveTest()));
        idToTest.put(4, new TestPair("HeapSort", () -> TestHeapSort.run()));
        idToTest.put(5, new TestPair("LinkedList", () -> TestLinkedList.interactiveTest()));
        idToTest.put(6, new TestPair("RedBlackMap", () -> TestRedBlackMap.comparisonTest()));
        idToTest.put(7, new TestPair("RedBlackTree", () -> TestRedBlackTree.run()));
        System.out.println("Choose the test:");
        idToTest.forEach(node -> {
            System.out.printf("  %d. %s\n", node.getKey(), node.getValue().name);
        });
        System.out.print("input: ");
        try {
            idToTest.get(Integer.valueOf(sin.nextLine())).getValue().runner.run();
        } catch(RuntimeException exc) {
            System.out.println("Bad choice");
            exc.printStackTrace();
        }
    }
    
}
