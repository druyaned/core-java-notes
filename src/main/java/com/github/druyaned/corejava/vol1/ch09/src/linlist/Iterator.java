package com.github.druyaned.corejava.vol1.ch09.src.linlist;

/**
 * Iterator of the {@link LinkedList} which is located between its elements.
 * 
 * <P><i>Example#1</i>: in array<pre>
 *  array:  a1 a2|a3 a4 a5
 *               ^
 *            iterator
 * </pre>
 * <i>Example#2</i>: at the beginning of array<pre>
 *  array: |a1 a2 a3 a4 a5
 *         ^
 *      iterator
 * </pre>
 * <i>Example#3</i>: at the end of array<pre>
 *  array:  a1 a2 a3 a4 a5|
 *                        ^
 *                     iterator
 * </pre>
 * 
 * <P><i>Structure of the Iterator class</i><pre>
 *  public class Iterator&lt;T&gt; {
 *  default:
 *      Node&lt;T&gt; prev, next;
 *      final LinkedList&lt;T&gt; linlist;
 *      Constructor(...) {
 *  public:
 *      boolean hasPrevious()
 *      boolean hasNext()
 *      T previous()
 *      T next()
 *      void addBefore(T value)
 *      void addAfter(T value)
 *      T removePrevious()
 *      T removeNext()
 *      boolean moveBack()
 *      boolean moveForward()
 *      void moveBeforeFirst()
 *      void moveAfterLast()
 *  }
 * </pre>
 * 
 * @author druyaned
 * @param <T> the type of value maintained by the {@link LinkedList}
 */
public class Iterator<T> {
    
    Node<T> prev, next;
    final LinkedList<T> linlist;
    
    Iterator(LinkedList<T> linlist) {
        this.linlist = linlist;
    }
    
    /**
     * Returns {@code true} if there is a previous value, otherwise - {@code false}.
     * @return {@code true} if there is a previous value, otherwise - {@code false}
     */
    public boolean hasPrevious() {
        return prev != null;
    }
    
    /**
     * Returns {@code true} if there is a next value, otherwise - {@code false}.
     * @return {@code true} if there is a next value, otherwise - {@code false}
     */
    public boolean hasNext() {
        return next != null;
    }
    
    /**
     * Returns element previous to the iterator in the linked list.
     * @return element previous to the iterator in the linked list
     */
    public T previous() {
        checkPrev();
        return prev.value;
    }
    
    /**
     * Returns element next to the iterator in the linked list.
     * @return element next to the iterator in the linked list
     */
    public T next() {
        checkNext();
        return next.value;
    }
    
    /**
     * Adds a new element to the linked list immediately before the iterator.
     * @param value to be added as a new element
     */
    public void addBefore(T value) {
        Node node = new Node(value, prev, next);
        if (prev != null) {
            prev.next = node;
        } else {
            linlist.first = node;
        }
        if (next != null) {
            next.prev = node;
        } else {
            linlist.last = node;
        }
        prev = node;
        linlist.size++;
    }
    
    /**
     * Adds a new element to the linked list immediately after the iterator.
     * @param value to be added as a new element
     */
    public void addAfter(T value) {
        Node node = new Node(value, prev, next);
        if (prev != null) {
            prev.next = node;
        } else {
            linlist.first = node;
        }
        if (next != null) {
            next.prev = node;
        } else {
            linlist.last = node;
        }
        next = node;
        linlist.size++;
    }
    
    /**
     * Removes element previous to the iterator in the linked list and returns its value.
     * @return value of removed element
     */
    public T removePrevious() {
        checkPrev();
        T removed = prev.value;
        if (prev.prev != null) {
            prev.prev.next = next;
        } else {
            linlist.first = next;
        }
        if (next != null) {
            next.prev = prev.prev;
        } else {
            linlist.last = prev.prev;
        }
        prev = prev.prev;
        linlist.size--;
        return removed;
    }
    
    /**
     * Removes element next to the iterator in the linked list and returns its value.
     * @return value of removed element
     */
    public T removeNext() {
        checkNext();
        T removed = next.value;
        if (next.next != null) {
            next.next.prev = prev;
        } else {
            linlist.last = prev;
        }
        if (prev != null) {
            prev.next = next.next;
        } else {
            linlist.first = next.next;
        }
        next = next.next;
        linlist.size--;
        return removed;
    }
    
    /**
     * Moves the iterator one element back and returns {@code true}
     * if there is such an element, otherwise just returns {@code false}.
     * 
     * @return {@code true} if the moving succeeded, otherwise - {@code false}
     */
    public boolean moveBack() {
        if (prev != null) {
            next = prev;
            prev = prev.prev;
            return true;
        }
        return false;
    }
    
    /**
     * Moves the iterator one element forward and returns {@code true}
     * if there is such an element, otherwise just returns {@code false}.
     * 
     * @return {@code true} if the moving succeeded, otherwise - {@code false}
     */
    public boolean moveForward() {
        if (next != null) {
            prev = next;
            next = next.next;
            return true;
        }
        return false;
    }
    
    /**
     * Places the iterator before the first element of the linked list.
     */
    public void moveBeforeFirst() {
        prev = null;
        next = linlist.first;
    }
    
    /**
     * Places the iterator after the last element of the linked list.
     */
    public void moveAfterLast() {
        prev = linlist.last;
        next = null;
    }
    
    private void checkPrev() {
        if (prev == null) {
            throw new IllegalStateException("there is nothing previous");
        }
    }
    
    private void checkNext() {
        if (next == null) {
            throw new IllegalStateException("there is nothing next");
        }
    }
    
}
