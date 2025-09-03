package druyaned.corejava.vol1.ch09collections.t04heap;

/**
 * A binary tree with a minimal element at the root.
 * To reach that state every ancestor must be not greater than any descendant.
 * The tree is stored as an array where index of an ancestor is {@code (i - 1)/2}
 * and indexes of descendants are {@code 2*i + 1} and {@code 2*i + 2}.
 * 
 * <P><i>Example</i><pre>
 *     3                              
 *   4   5  [as array]-->  3 4 5 7 8 6
 *  7 8 6                             
 * </pre>
 * 
 * @param <T> the type of value maintained by the heap
 * @author druyaned
 * @see java.util.PriorityQueue
 */
public class Heap<T> {
    
    /** Default capacity for some constructors to be set in indefinite case. */
    public static final int DEFAULT_CAPACITY = 16;
    
    private int capacity;
    private Object[] values;
    private int size;
    private final java.util.Comparator<T> comp;
    
    /**
     * Creates a new empty heap.
     * 
     * @param capacity capacity of the heap
     * @param comp to compare values maintained by the heap
     */
    public Heap(int capacity, java.util.Comparator<T> comp) {
        this.capacity = capacity > 0 ? capacity : DEFAULT_CAPACITY;
        this.size = 0;
        this.values = new Object[capacity];
        this.comp = comp;
    }
    
    /**
     * Creates a new empty heap with a {@link DEFAULT_CAPACITY default capacity}.
     * 
     * @param comp to compare values maintained by the heap
     */
    public Heap(java.util.Comparator<T> comp) {
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
        this.values = new Object[capacity];
        this.comp = comp;
    }
    
    /**
     * Returns immutable capacity of the heap.
     * @return immutable capacity of the heap
     */
    public int capacity() {
        return capacity;
    }
    
    /**
     * Returns size of the heap.
     * @return size of the heap
     */
    public int size() {
        return size;
    }
    
    /**
     * Returns {@code true} if the heap is empty, otherwise - {@code false}.
     * @return {@code true} if the heap is empty, otherwise - {@code false}
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Returns root of the heap.
     * @return root of the heap
     */
    public T root() {
        throwIfEmpty();
        return (T)values[0];
    }
    
    /**
     * Returns an element at the position {@code i}.
     * @param i index of the position
     * @return element at the position {@code i}
     */
    public T get(int i) {
        throwIfBadIndex(i);
        return (T)values[i];
    }
    
    /**
     * Inserts the value into the heap. Complexity is O(log(n)).
     * 
     * <P><i>Execution Example</i>: heap.add(2)<pre>
     *     3            3            3            2  
     *   4   5  -->   4   5  -->   4   2  -->   4   3
     *  7 8          7 8 2        7 8 5        7 8 5 
     * </pre>
     * 
     * @param value to be inserted
     */
    public void add(T value) {
        extensionOnDemand();
        int i = size;
        while (i > 0) {
            int parent = (i - 1) >>> 1;
            T parentValue = (T)values[parent];
            if (comp.compare(parentValue, value) <= 0)
                break;
            values[i] = parentValue;
            i = parent;
        }
        values[i] = value;
        size++;
    }
    
    /**
     * Removes the root (lowest value) from the heap. Complexity is O(log(n)).
     * 
     * <P><i>Execution Example</i><pre>
     *     3            6            4  
     *   4   5  -->   4   5  -->   6   5
     *  7 8 6        7 8          7 8   
     * </pre>
     * 
     * @return removed value
     */
    public T remove() {
        throwIfEmpty();
        T removed = (T)values[0];
        size--;
        T value = (T)values[size];
        values[size] = null;
        int i = 0, l = 1;
        while (l < size) {
            int r = l + 1;
            int minChild = r < size
                    && comp.compare((T)values[l], (T)values[r]) > 0
                    ? r : l;
            T minChildValue = (T)values[minChild];
            if (comp.compare(value, minChildValue) <= 0)
                break;
            values[i] = minChildValue;
            i = minChild;
            l = (i << 1) + 1;
        }
        values[i] = value;
        return removed;
    }
    
    /**
     * Clears the whole heap. Complexity is O(n).
     */
    public void clear() {
        for (int i = 0; i < size; i++)
            values[i] = null;
        size = 0;
    }
    
    /**
     * Simply sets the size to zero. Complexity is O(1).
     */
    public void clearSimply() {
        size = 0;
    }
    
    private void extensionOnDemand() {
        if (size != capacity)
            return;
        capacity <<= 1;
        Object[] oldValues = values;
        values = new Object[capacity];
        System.arraycopy(oldValues, 0, values, 0, size);
    }
    
    private void throwIfEmpty() {
        if (size == 0)
            throw new java.util.NoSuchElementException("the heap is empty");
    }
    
    private void throwIfBadIndex(int i) {
        if (i < 0 || size <= i)
            throw new IndexOutOfBoundsException("i=" + i + " size=" + size);
    }
    
}
