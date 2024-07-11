package com.github.druyaned.horstmann.corejava.vol1.ch09.src.linlist;

import java.util.function.Consumer;

/**
 * Implementation of the {@link java.util.LinkedList LinkedList}.
 * Main functionality of the collection is provided by the {@link Iterator}.
 * 
 * @author druyaned
 * @param <T> the type of value maintained by the list
 */
public class LinkedList<T> {
    
    Node<T> first, last;
    final Iterator iter;
    int size;
    
    /**
     * Creates a new empty linked list.
     */
    public LinkedList() {
        first = null;
        last = null;
        iter = new Iterator(this);
        size = 0;
    }
    
    /**
     * Returns value of the first element.
     * @return value of the first element
     */
    public T getFirst() {
        checkIfEmpty();
        return first.value;
    }
    
    /**
     * Returns value of the last element.
     * @return value of the last element
     */
    public T getLast() {
        checkIfEmpty();
        return last.value;
    }
    
    /**
     * Returns size of the linked list.
     * @return size of the linked list
     */
    public int size() {
        return size;
    }
    
    /**
     * Returns {@code true} if the linked list is empty, otherwise - {@code false}.
     * @return {@code true} if the linked list is empty, otherwise - {@code false}
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Removes all elements from the linked list by making all fields be {@code null}.
     */
    public void clear() {
        first = last = iter.prev = iter.next = null;
        size = 0;
    }
    
    /**
     * Returns {@link Iterator iterator} of the linked list.
     * @return {@link Iterator iterator} of the linked list
     */
    public Iterator iterator() {
        return iter;
    }
    
    /**
     * Accepts the given action for each element of the linked list.
     * @param action to accept the given action for each element of the linked list
     */
    public void forEach(Consumer<T> action) {
        for (Node<T> node = first; node != null; node = node.next) {
            action.accept(node.value);
        }
    }
    
    private void checkIfEmpty() {
        if (size == 0) {
            throw new IllegalStateException("the list is empty");
        }
    }
    
}
