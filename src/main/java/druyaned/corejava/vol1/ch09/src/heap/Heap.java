package druyaned.corejava.vol1.ch09.src.heap;

import java.util.Comparator;

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
 */
public class Heap<T> {
    
    private final int capacity;
    private int size;
    private final Object[] values;
    private final Comparator<T> comp;
    
    /**
     * Creates a new empty heap with an immutable capacity.
     * 
     * @param capacity immutable capacity of the heap
     * @param comp to compare values maintained by the heap
     */
    public Heap(int capacity, Comparator<T> comp) {
        this.capacity = capacity;
        size = 0;
        values = new Object[capacity];
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
     * Returns getRoot of the heap.
     * @return getRoot of the heap
     */
    public T getRoot() {
        checkIfEmpty();
        return (T)values[0];
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
     * @return {@code true} if the insertion was succeeded, otherwise - {@code false}
     */
    public boolean add(T value) {
        if (size == capacity) {
            return false;
        }
        values[size] = value;
        int i = size;
        int ancestor = (i - 1) / 2;
        while (ancestor >= 0 && comp.compare((T)values[ancestor], (T)values[i]) > 0) {
            swap(ancestor, i);
            i = ancestor;
            ancestor = (i - 1) / 2;
        }
        size++;
        return true;
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
        checkIfEmpty();
        T removed = (T)values[0];
        values[0] = values[size - 1];
        deleteLast();
        size--;
        return removed;
    }
    
    private void deleteLast() {
        values[size - 1] = null;
        int i = 0;
        int left = 1;
        int right = 2;
        int minChild = right < size - 1 &&
                comp.compare((T)values[left], (T)values[right]) > 0 ? right : left;
        while (left < size - 1 && comp.compare((T)values[i], (T)values[minChild]) > 0) {
            swap(i, minChild);
            i = minChild;
            left = 2 * i + 1;
            right = 2 * i + 2;
            minChild = right < size - 1 &&
                comp.compare((T)values[left], (T)values[right]) > 0 ? right : left;
        }
    }
    
    private void checkIfEmpty() {
        if (size == 0) {
            throw new IllegalStateException("the heap is empty");
        }
    }
    
    private void swap(int i1, int i2) {
        Object stock = values[i2];
        values[i2] = values[i1];
        values[i1] = stock;
    }
    
}
