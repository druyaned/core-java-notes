package com.github.druyaned.corejava.vol1.ch09.src.linlist;

/**
 * The node is an actual component of the {@link LinkedList Linked List}.
 * 
 * @author druyaned
 * @param <T> the type of value maintained by the node
 */
class Node<T> {
    
    final T value;
    Node<T> prev, next;
    
    Node(T value, Node<T> prev, Node<T> next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
    
}
