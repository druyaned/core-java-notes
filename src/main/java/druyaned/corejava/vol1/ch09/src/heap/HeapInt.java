package druyaned.corejava.vol1.ch09.src.heap;

public class HeapInt {
    
    public static final int DEFAULT_CAPACITY = 16;
    
    private int capacity;
    private int[] values;
    private int size;
    private final java.util.function.IntBinaryOperator comp;
    
    public HeapInt(int capacity, java.util.function.IntBinaryOperator comp) {
        this.capacity = capacity > 0 ? capacity : DEFAULT_CAPACITY;
        this.values = new int[capacity];
        this.size = 0;
        this.comp = comp;
    }
    
    public HeapInt() {
        this.capacity = DEFAULT_CAPACITY;
        this.values = new int[capacity];
        this.size = 0;
        this.comp = Integer::compare;
    }
    
    public int capacity() {
        return capacity;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int root() {
        throwIfEmpty();
        return values[0];
    }
    
    public int get(int i) {
        throwIfBadIndex(i);
        return values[i];
    }
    
    public void add(int value) {
        if (size == capacity)
            extension();
        int i = size;
        while (i > 0) {
            int parent = (i - 1) >>> 1;
            int parentValue = values[parent];
            if (comp.applyAsInt(parentValue, value) <= 0)
                break;
            values[i] = parentValue;
            i = parent;
        }
        values[i] = value;
        size++;
    }
    
    public int remove() {
        throwIfEmpty();
        int removed = values[0];
        size--;
        int value = values[size];
        values[size] = 0;
        int i = 0, l = 1;
        while (l < size) {
            int r = l + 1;
            int minChild = r < size
                    && comp.applyAsInt(values[l], values[r]) > 0
                    ? r : l;
            int minChildValue = values[minChild];
            if (comp.applyAsInt(value, minChildValue) <= 0)
                break;
            values[i] = minChildValue;
            i = minChild;
            l = (i << 1) + 1;
        }
        values[i] = value;
        return removed;
    }
    
    public void clear() {
        for (int i = 0; i < size; i++)
            values[i] = 0;
        size = 0;
    }
    
    public void clearSimply() {
        size = 0;
    }
    
    // only if (size == capacity)
    private void extension() {
        capacity <<= 1;
        int[] oldValues = values;
        values = new int[capacity];
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
