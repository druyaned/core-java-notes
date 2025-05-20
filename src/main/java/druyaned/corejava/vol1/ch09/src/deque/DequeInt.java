package druyaned.corejava.vol1.ch09.src.deque;

public class DequeInt {
    
    public static final int DEFAULT_CAPACITY = 16;
    
    private int capacity;
    private int[] values;
    private int size;
    private int head, tail;
    
    public DequeInt(int capacity) {
        this.capacity = capacity > 0 ? capacity : DEFAULT_CAPACITY;
        values = new int[capacity];
        size = 0;
        head = tail = 0;
    }
    
    public DequeInt() {
        this(DEFAULT_CAPACITY);
    }
    
    public DequeInt(int... elems) {
        size = elems.length;
        head = 0;
        if (size > 0) {
            capacity = size;
            tail = size - 1;
        } else {
            capacity = DEFAULT_CAPACITY;
            tail = 0;
        }
        values = new int[capacity];
        System.arraycopy(elems, 0, values, 0, size);
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public int getSize() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int getHead() {
        return head;
    }
    
    public int getTail() {
        return tail;
    }
    
    public void forEach(java.util.function.IntConsumer consumer) {
        if (head <= tail) {
            for (int i = head; i <= tail; i++)
                consumer.accept(values[i]);
        } else {
            for (int i = head; i < capacity; i++)
                consumer.accept(values[i]);
            for (int i = 0; i <= tail; i++)
                consumer.accept(values[i]);
        }
    }
    
    public int get(int i) {
        throwIfBadIndex(i);
        return values[relativeIndex(i)];
    }
    
    public int getFirst() {
        throwIfBadIndex(0);
        return values[head];
    }
    
    public int getLast() {
        throwIfBadIndex(size - 1);
        return values[tail];
    }
    
    public void set(int i, int value) {
        throwIfBadIndex(i);
        values[relativeIndex(i)] = value;
    }
    
    public void clear() {
        for (int i = 0; i < size; i++)
            values[i] = 0;
        size = head = tail = 0;
    }
    
    public void clearSimple() {
        size = head = tail = 0;
    }
    
    public void addFirst(int value) {
        extensionOnDemand();
        if (size == 0)
            values[head = tail = 0] = value;
        else if (head == 0)
            values[head = capacity - 1] = value;
        else
            values[--head] = value;
        size++;
    }
    
    public void addLast(int value) {
        extensionOnDemand();
        if (size == 0)
            values[head = tail = 0] = value;
        else if (tail == capacity - 1)
            values[tail = 0] = value;
        else
            values[++tail] = value;
        size++;
    }
    
    public int removeFirst() {
        throwIfEmpty();
        int removed = values[head];
        values[head] = 0;
        if (size == 1)
            head = tail = 0;
        else if (head == capacity - 1)
            head = 0;
        else
            head++;
        size--;
        return removed;
    }
    
    public int removeLast() {
        throwIfEmpty();
        int removed = values[tail];
        values[tail] = 0;
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
        int[] oldValues = values;
        values = new int[capacity];
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
