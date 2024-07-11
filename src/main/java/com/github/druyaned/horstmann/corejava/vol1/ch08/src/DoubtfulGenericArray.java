package com.github.druyaned.horstmann.corejava.vol1.ch08.src;

/**
 * Not very safe way of creation a generic array.
 * It can be broken like this:
 * {@snippet :
 * Object[] objects = make(LocalDate.now(), LocalDate.now());
 * objects[0] = new String();
 * }
 */
interface DoubtfulGenericArray {
    
    @SafeVarargs static <T> T[] make(T... arr) { return arr; }
    
}
