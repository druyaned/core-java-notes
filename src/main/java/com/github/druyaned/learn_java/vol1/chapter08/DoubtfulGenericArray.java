package com.github.druyaned.learn_java.vol1.chapter08;

/**
 * Not very safe way of creation a generic array.
 * It can be broken like this:
 * {@code Object[] objects = make(LocalDate.now(), LocalDate.now());}
 * {@code objects[0] = new String();}
 */
interface DoubtfulGenericArray {
    @SafeVarargs
    static <T> T[] make(T... arr) {return arr;}
}
