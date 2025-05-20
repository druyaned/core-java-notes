package druyaned.corejava.vol1.ch09.src.linlist;

import java.util.function.Consumer;

/**
 * Implementation of the {@link java.util.LinkedList LinkedList}.
 * Main functionality of the collection is provided by the {@link Iterator}.
 * 
 * @author druyaned
 * @param <T> the type of value maintained by the list
 */
public class LinkedList<T> {
    
    private Node<T> first, last;
    private final Iterator iter;
    private int size;
    
    /**
     * Creates a new empty linked list.
     */
    public LinkedList() {
        this.first = null;
        this.last = null;
        this.iter = new Iterator(this);
        this.size = 0;
    }
    
    /**
     * Returns value of the first element.
     * @return value of the first element
     */
    public T first() {
        throwIfEmpty();
        return first.value;
    }
    
    /**
     * Returns value of the last element.
     * @return value of the last element
     */
    public T last() {
        throwIfEmpty();
        return last.value;
    }
    
    /**
     * Returns the first node.
     * @return first node
     */
    public Node<T> firstNode() {
        return first;
    }
    
    /**
     * Returns the last node.
     * @return last node
     */
    public Node<T> lastNode() {
        return last;
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
     * Removes all elements from the linked list. Complexity is O(n).
     */
    public void clear() {
        for (Node<T> node = first; node != null; ) {
            Node<T> next = node.next;
            node.next = null;
            node.prev = null;
            node = next;
        }
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
        for (Node<T> node = first; node != null; node = node.next)
            action.accept(node.value);
    }
    
    private void throwIfEmpty() {
        if (size == 0)
            throw new IllegalStateException("the linked list is empty");
    }
    
    /**
     * The node is an actual component of the {@link LinkedList Linked List}.
     * 
     * @author druyaned
     * @param <T> the type of value maintained by the node
     */
    public static class Node<T> {
        private final T value;
        private Node<T> prev, next;
        private Node(T value, Node<T> prev, Node<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
        /**
         * Returns value of the node.
         * @return value of the node
         */
        public T value() {
            return value;
        }
        /**
         * Returns previous node to this node.
         * @return previous node to this node
         */
        public Node<T> prev() {
            return prev;
        }
        /**
         * Returns next node to this node.
         * @return next node to this node
         */
        public Node<T> next() {
            return next;
        }
    }
    
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
     * @author druyaned
     * @param <T> the type of value maintained by the {@link LinkedList}
     */
    public static class Iterator<T> {
        private Node<T> prev, next;
        private final LinkedList<T> linlist;
        private Iterator(LinkedList<T> linlist) {
            this.linlist = linlist;
        }
        /**
         * Returns {@code true} if there is a previous value, otherwise - {@code false}.
         * @return {@code true} if there is a previous value, otherwise - {@code false}
         */
        public boolean hasPrev() {
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
        public T prev() {
            throwIfNoPrev();
            return prev.value;
        }
        /**
         * Returns element next to the iterator in the linked list.
         * @return element next to the iterator in the linked list
         */
        public T next() {
            throwIfNoNext();
            return next.value;
        }
        /**
         * Returns previous node to the iterator in the linked list.
         * @return previous node to the iterator in the linked list
         */
        public Node<T> prevNode() {
            return prev;
        }
        /**
         * Returns next node to the iterator in the linked list.
         * @return next node to the iterator in the linked list
         */
        public Node<T> nextNode() {
            return next;
        }
        /**
         * Adds a new element to the linked list immediately before the iterator.
         * @param value to be added as a new element
         */
        public void addBefore(T value) {
            Node node = new Node(value, prev, next);
            if (prev != null)
                prev.next = node;
            else
                linlist.first = node;
            if (next != null)
                next.prev = node;
            else
                linlist.last = node;
            prev = node;
            linlist.size++;
        }
        /**
         * Adds a new element to the linked list immediately after the iterator.
         * @param value to be added as a new element
         */
        public void addAfter(T value) {
            Node node = new Node(value, prev, next);
            if (prev != null)
                prev.next = node;
            else
                linlist.first = node;
            if (next != null)
                next.prev = node;
            else
                linlist.last = node;
            next = node;
            linlist.size++;
        }
        /**
         * Removes element previous to the iterator in the linked list and returns its value.
         * @return value of removed element
         */
        public T removePrev() {
            throwIfNoPrev();
            T removed = prev.value;
            if (prev.prev != null)
                prev.prev.next = next;
            else
                linlist.first = next;
            if (next != null)
                next.prev = prev.prev;
            else
                linlist.last = prev.prev;
            prev = prev.prev;
            linlist.size--;
            return removed;
        }
        /**
         * Removes element next to the iterator in the linked list and returns its value.
         * @return value of removed element
         */
        public T removeNext() {
            throwIfNoNext();
            T removed = next.value;
            if (next.next != null)
                next.next.prev = prev;
            else
                linlist.last = prev;
            if (prev != null)
                prev.next = next.next;
            else
                linlist.first = next.next;
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
        private void throwIfNoPrev() {
            if (prev == null)
                throw new IllegalStateException("there is no previous");
        }
        private void throwIfNoNext() {
            if (next == null)
                throw new IllegalStateException("there is no next");
        }
    }
    
}
