package com.github.druyaned.learn_java.vol1.chapter08;

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

    public GenericArray(int initCapacity) throws CapacityException {
        // Capacity check
        if (initCapacity < MIN_CAPACITY || initCapacity > MAX_CAPACITY) {
            String message = String.format("[MIN_CAPACITY; MAX_CAPACITY]=[%d; %d], given=%d",
                                           MIN_CAPACITY, MAX_CAPACITY, initCapacity);
            throw new CapacityException(message);
        }
        
        // Capacity initialization
        capacity = MIN_CAPACITY;
        while (initCapacity > capacity) {
            capacity = capacity << 1;
        }

        size = 0;
        elements = new Object[capacity];
    }

    @SafeVarargs
    private GenericArray(T... elements) throws CapacityException {
        // Capacity check
        if (elements.length > MAX_CAPACITY) {
            String message = "MAX_CAPACITY=" + MAX_CAPACITY + ", given=" + elements.length;
            throw new CapacityException(message);
        }

        // Capacity and size initialization
        capacity = MIN_CAPACITY;
        size = elements.length;
        while (size > capacity) {
            capacity = capacity << 1;
        }

        // Elements initialization
        this.elements = new Object[capacity];
        for (int i = 0; i < size; ++i) {
            this.elements[i] = elements[i];
        }
    }

    // Simple getters
    public int getCapacity() {return this.capacity;}
    public int getSize() {return this.size;}

    /**
     * Adds the element to the end of the array.
     * The capacity will be increased by 2 times if necessary.
     * @param toTheEnd the element to add to the end.
     * @return {@code false} if current capacity == MAX_CAPACITY, else {@code true}.
     */
    public boolean add(T toTheEnd) {
        if (size == capacity) {
            // Capacity check
            if (capacity == MAX_CAPACITY) {
                return false;
            }

            // Capacity change and new elements creation
            capacity = capacity << 1;
            Object[] newElements = new Object[capacity];
            for (int i = 0; i < size; ++i) {
                newElements[i] = elements[i];
            }
            newElements[size] = toTheEnd;

            // Size and elements change
            size += 1;
            elements = newElements;
            return true;
        }

        elements[size] = toTheEnd;
        size += 1;
        return true;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return (T) elements[index];
    }

    @SuppressWarnings("unchecked")
    public void forEach(Consumer<T> action) {
        for (int i = 0; i < size; ++i) {
            action.accept((T) elements[i]);
        }
    }

    @Override
    public String toString() {
        return "[capacity=" + capacity + ", size=" + size + ", elements=" + elements + "]";
    }

    @SafeVarargs
    public static <T> GenericArray<T> from(T... elements) throws CapacityException {
        return new GenericArray<>(elements);
    }
}
