package druyaned.corejava.vol1.ch08.src;

import java.util.Arrays;
import java.util.function.Consumer;

public class GenericArray<T> {
    
    public static final int MIN_CAPACITY = 16;
    public static final int MAX_CAPACITY = 1 << 24;

    private int capacity;
    private int size;
    private Object[] elements;

    public GenericArray() {
        capacity = MIN_CAPACITY;
        size = 0;
        elements = new Object[capacity];
    }

    public GenericArray(int initCapacity) {
        checkCapacity(initCapacity);
        this.capacity = MIN_CAPACITY;
        while (initCapacity > this.capacity) {
            this.capacity *= 2;
        }
        size = 0;
        elements = new Object[this.capacity];
    }

    @SafeVarargs private GenericArray(T... elements) {
        checkCapacity(elements.length);
        capacity = MIN_CAPACITY;
        size = elements.length;
        while (size > capacity) {
            capacity *= 2;
        }
        this.elements = new Object[capacity];
        System.arraycopy(elements, 0, this.elements, 0, size);
    }

    public int getCapacity() { return capacity; }

    public int getSize() { return size; }

    public boolean add(T elem) {
        if (size == capacity) {
            if (capacity == MAX_CAPACITY) {
                return false;
            }
            capacity *= 2;
            Object[] newElements = new Object[capacity];
            System.arraycopy(elements, 0, newElements, 0, size);
            newElements[size] = elem;
            size++;
            elements = newElements;
        } else {
            elements[size] = elem;
            size++;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T)elements[index];
    }

    @SuppressWarnings("unchecked")
    public void forEach(Consumer<T> action) {
        for (int i = 0; i < size; ++i) {
            action.accept((T)elements[i]);
        }
    }

    @Override public String toString() {
        return "GenericArray{capacity=" + capacity +
                ", size=" + size +
                ", elements=" + Arrays.toString(elements) + "}";
    }

    @SafeVarargs public static <T> GenericArray<T> from(T... elements) {
        return new GenericArray<>(elements);
    }

    private static void checkCapacity(int capacity) {
        if (capacity < 0 || capacity > MAX_CAPACITY) {
            throw new IllegalStateException(
                    "capacity must be from " + MIN_CAPACITY + " to " + MAX_CAPACITY
            );
        }
    }

}
