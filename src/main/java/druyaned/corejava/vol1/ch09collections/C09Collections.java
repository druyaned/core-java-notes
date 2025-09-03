package druyaned.corejava.vol1.ch09collections;

import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;

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
public class C09Collections extends Chapter {
    
    /**
     * Creates the Chapter#09: Collections.
     * @param volume to which the chapter belongs
     */
    public C09Collections(Volume volume) {
        super(volume, 9);
        topics().add(new T01DequeInteractive(this));
        topics().add(new T02HashMapVsTreeMap(this));
        topics().add(new T03HeapSort(this));
        topics().add(new T04HeapInteractive(this));
        topics().add(new T05LinkedListInteractive(this));
        topics().add(new T06RBTComparison(this));
        topics().add(new T07RBMComparison(this));
        topics().add(new T08RBTInteractive(this));
    }
    
    @Override public String title() {
        return "Chapter 09 Collections";
    }
    
}
