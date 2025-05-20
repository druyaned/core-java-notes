package druyaned.corejava.vol1.ch09.src.deque;

/**
 * Implementation of the {@link java.util.Deque Deque}.
 * 
 * <P><i>Important Note</i><br>
 * The structure implementation is based on the array with an immutable {@code capacity}.
 * 
 * <P><i>Example#1</i><pre>
 *  # 5 2 7 1 6 # #
 *    ^       ^
 *  head     tail
 * </pre>
 * <i>Example#2</i><pre>
 *  7 1 6 # # # 5 2
 *      ^       ^
 *    tail     head
 * </pre>
 * 
 * @author druyaned
 * @param <T> the type of value maintained by the list
 */
public class Deque<T> {
    
    /** Default capacity for the constructors with no arguments. */
    public static final int DEFAULT_CAPACITY = 16;
    
    private int capacity;
    private Object[] values;
    private int size;
    private int head, tail;
    
    /**
     * Creates a new empty deque.
     * @param capacity capacity of the data array
     */
    public Deque(int capacity) {
        this.capacity = capacity > 0 ? capacity : DEFAULT_CAPACITY;
        values = new Object[capacity];
        size = 0;
        head = tail = 0;
    }
    
    /**
     * Creates a new empty deque with {@link DEFAULT_CAPACITY default capacity}.
     */
    public Deque() {
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * Creates a new deque and fills it with a given elements from index=0.
     * @param elems to be added into the deque from index=0
     */
    public Deque(T... elems) {
        size = elems.length;
        head = 0;
        if (size > 0) {
            capacity = size;
            tail = size - 1;
        } else {
            capacity = DEFAULT_CAPACITY;
            tail = 0;
        }
        values = new Object[capacity];
        System.arraycopy(elems, 0, values, 0, size);
    }
    
    /**
     * Returns capacity of the deque.
     * @return capacity of the deque
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * Returns size of the deque.
     * @return size of the deque
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Returns {@code true} if the deque is empty, otherwise - {@code false}.
     * @return {@code true} if the deque is empty, otherwise - {@code false}
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Returns head of the deque.
     * @return head of the deque
     */
    public int getHead() {
        return head;
    }
    
    /**
     * Returns tail of the deque.
     * @return tail of the deque
     */
    public int getTail() {
        return tail;
    }
    
    /**
     * Accepts the given consumer for each element of the deque.
     * @param consumer to be accepted for each element of the deque
     */
    public void forEach(java.util.function.Consumer<T> consumer) {
        if (head <= tail) {
            for (int i = head; i <= tail; i++)
                consumer.accept((T)values[i]);
        } else {
            for (int i = head; i < capacity; i++)
                consumer.accept((T)values[i]);
            for (int i = 0; i <= tail; i++)
                consumer.accept((T)values[i]);
        }
    }
    
    /**
     * Returns value which is placed after the head by {@code i} positions
     * (head's position is {@code 0}). So {@code deque.get(0)} returns head
     * of the deque.
     * 
     * @param i to move forward from the head
     * @return value which is placed after the head by {@code i} positions
     *      (head's position is {@code 0})
     */
    public T get(int i) {
        throwIfBadIndex(i);
        return (T)values[relativeIndex(i)];
    }
    
    /**
     * Returns head of the deque.
     * @return head of the deque
     */
    public T getFirst() {
        throwIfBadIndex(0);
        return (T)values[head];
    }
    
    /**
     * Returns tail of the deque.
     * @return tail of the deque
     */
    public T getLast() {
        throwIfBadIndex(size - 1);
        return (T)values[tail];
    }
    
    /**
     * Sets value which is placed after the head by {@code i} positions
     * (head's position is {@code 0}). So deque.set(0, value) sets the head.
     * 
     * @param i to shift forward from the head
     * @param value to be set
     */
    public void set(int i, T value) {
        throwIfBadIndex(i);
        values[relativeIndex(i)] = value;
    }
    
    /**
     * Clears the whole deque. The complexity of the method is O(n).
     */
    public void clear() {
        for (int i = 0; i < size; i++)
            values[i] = null;
        size = head = tail = 0;
    }
    
    /**
     * Sets size, head and tail to zero.
     * The complexity of the method is O(1).
     */
    public void clearSimple() {
        size = head = tail = 0;
    }
    
    /**
     * Attaches the value to the head of the deque.
     * @param value to be attached
     */
    public void addFirst(T value) {
        extensionOnDemand();
        if (size == 0)
            values[head = tail = 0] = value;
        else if (head == 0)
            values[head = capacity - 1] = value;
        else
            values[--head] = value;
        size++;
    }
    
    /**
     * Attaches the value to the tail of the deque.
     * @param value to be attached
     */
    public void addLast(T value) {
        extensionOnDemand();
        if (size == 0)
            values[head = tail = 0] = value;
        else if (tail == capacity - 1)
            values[tail = 0] = value;
        else
            values[++tail] = value;
        size++;
    }
    
    /**
     * Detaches and returns the head of the deque.
     * @return detached head of the deque
     */
    public T removeFirst() {
        throwIfEmpty();
        T removed = (T)values[head];
        values[head] = null;
        if (size == 1)
            head = tail = 0;
        else if (head == capacity - 1)
            head = 0;
        else
            head++;
        size--;
        return removed;
    }
    
    /**
     * Detaches and returns the tail of the deque.
     * @return detached tail of the deque
     */
    public T removeLast() {
        throwIfEmpty();
        T removed = (T)values[tail];
        values[tail] = null;
        if (size == 1)
            head = tail = 0;
        else if (tail == 0)
            tail = capacity - 1;
        else
            tail--;
        size--;
        return removed;
    }
    
    private void extensionOnDemand() {
        if (size != capacity)
            return;
        capacity <<= 1;
        Object[] oldValues = values;
        values = new Object[capacity];
        if (head == 0)
            System.arraycopy(oldValues, 0, values, 0, size);
        else { // tail + 1 == head
            System.arraycopy(oldValues, head, values, head + size, oldValues.length - head);
            System.arraycopy(oldValues, 0, values, 0, head);
            head += size;
        }
    }
    
    private int relativeIndex(int i) {
        // head + i < capacity ? head + i : head + i - capacity;
        return head < capacity - i ? head + i : i - capacity + head;
    }
    
    private void throwIfBadIndex(int i) {
        if (i < 0 || i >= size)
            throw new IndexOutOfBoundsException(String.format("index=%d size=%d", i, size));
    }
    
    private void throwIfEmpty() {
        if (size == 0)
            throw new IllegalStateException("the deque is empty");
    }
    
}
