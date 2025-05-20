package druyaned.corejava.vol1.ch09;

import druyaned.corejava.Chapter;
import druyaned.corejava.vol1.ch09.src.P02HashMapVsTreeMap;
import druyaned.corejava.vol1.ch09.src.P03HeapInteractive;
import druyaned.corejava.vol1.ch09.src.P01DequeInteractive;
import druyaned.corejava.vol1.ch09.src.P04HeapSort;
import druyaned.corejava.vol1.ch09.src.P05LinkedListInteractive;
import druyaned.corejava.vol1.ch09.src.P07RBMComparison;
import druyaned.corejava.vol1.ch09.src.P06RBTComparison;
import druyaned.corejava.vol1.ch09.src.P08RBTInteractive;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
        List<Runnable> parts = new ArrayList<>();
        parts.add(new P01DequeInteractive());
        parts.add(new P02HashMapVsTreeMap());
        parts.add(new P03HeapInteractive());
        parts.add(new P04HeapSort());
        parts.add(new P05LinkedListInteractive());
        parts.add(new P06RBTComparison());
        parts.add(new P07RBMComparison());
        parts.add(new P08RBTInteractive());
        choosePartAndRun(parts);
    }
    
}
