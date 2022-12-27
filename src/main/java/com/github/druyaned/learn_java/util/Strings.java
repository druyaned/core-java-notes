package com.github.druyaned.learn_java.util;

import java.util.Objects;

/**
 * A wrapper for an array of {@link String} instances that guarantees immutable behavior.
 * 
 * @author druyaned
 */
public final class Strings {
    
    /** Minimum size of the array. */
    public static final int MIN_SIZE = 1;
    
    /** Maximum size of the array. */
    public static final int MAX_SIZE = 2 * ((1 << (16 - 2)) - 1) + 1; // a bit funnier calculation
    
//-Non-static---------------------------------------------------------------------------------------
    
    private final String[] array;
    private final int begin;
    private final int size;
    
    /**
     * Constructs a new wrapper for the array of strings.
     * 
     * @param array an array of strings to be wrapped.
     * @see #from(int begin)
     * @see #from(int being, int size)
     * @see #fromTo(int begin, int end)
     */
    public Strings(String[] array) {
        if (array.length < MIN_SIZE || array.length > MAX_SIZE) {
            String m = "array.length=" + array.length +
                       " which must be in [" + MIN_SIZE + ", " + MAX_SIZE + "]";
            throw new IllegalArgumentException(m);
        }
        this.array = array;
        begin = 0;
        size = array.length;
    }
    
    /**
     * Constructs a new wrapper for the array of strings.
     * 
     * @param array an array of strings to get a wrapped part of it.
     * @param begin the beginning index (<i>inclusive</i>)
     *        of the wrapped part of the {@code array}.
     * @param size the size of the wrapped part of the {@code array}.
     * @see #from(int begin)
     * @see #from(int being, int size)
     * @see #fromTo(int begin, int end)
     */
    public Strings(String[] array, int begin, int size) {
        Objects.requireNonNull(array, "array mustn't be a null");
        if (array.length < MIN_SIZE || array.length > MAX_SIZE) {
            String m = "array.length=" + array.length +
                       " which must be in [" + MIN_SIZE + ", " + MAX_SIZE + "]";
            throw new IllegalArgumentException(m);
        }
        if (size < MIN_SIZE || size > array.length) {
            String m = "size=" + size +
                       " which must be in [" + MIN_SIZE + ", " + array.length + "]";
            throw new IndexOutOfBoundsException(m);
        }
        if (begin < 0 || begin > array.length - size) {
            String m = "begin=" + begin + " which must be in [0, " + (array.length - size) + "]";
            throw new IndexOutOfBoundsException(m);
        }
        this.array = array;
        this.begin = begin;
        this.size = size;
    }
    
    /**
     * Constructs a new wrapper for the array of string.
     * 
     * @param str a string to place it into a new array of 1 element.
     * @see #from(int begin)
     * @see #from(int being, int size)
     * @see #fromTo(int begin, int end)
     */
    public Strings(String str) {
        array = new String[] { str };
        begin = 0;
        size = 1;
    }
    
//-Getters------------------------------------------------------------------------------------------

    /**
     * The beginning index (<i>inclusive</i>) of the wrapped part of the {@code array}.
     * 
     * @return the beginning index (<i>inclusive</i>) of the wrapped part of the {@code array}.
     */
    public int getBegin() { return begin; }

    /**
     * The ending index (<i>exclusive</i>) of the wrapped part of the {@code array}.
     * 
     * @return the ending index (<i>exclusive</i>) of the wrapped part of the {@code array}.
     */
    public int getEnd() { return begin + size; }

    /**
     * The size of the wrapped array.
     * 
     * @return the size of the wrapped array.
     */
    public int size() { return size; }
    
//-Methods------------------------------------------------------------------------------------------
    
    /**
     * The line with the index {@code i}
     * from 0 (<i>inclusive</i>) to {@code size} (<i>exclusive</i>)
     * in the wrapped part of the array.
     * 
     * @param i an index from 0 (<i>inclusive</i>) to {@code size} (<i>exclusive</i>)
     *        of the line in the wrapped part of the array.
     * @return the line with the index {@code i}
     *         from 0 (<i>inclusive</i>) to {@code size} (<i>exclusive</i>)
     *         in the wrapped part of the array.
     */
    public String get(int i) {
        if (i < 0 || i > size) {
            String m = "i=" + i +
                       " which must be in [" + 0 + ", " + size + ")";
            throw new IndexOutOfBoundsException(m);
        }
        return array[i + begin];
    }
    
    /**
     * The last line in the wrapped part of the array.
     * 
     * @return the last line in the wrapped part of the array.
     */
    public String getLast() { return array[size - 1 + begin]; }
    
    /**
     * Returns a new wrapper for the same array from the {@code begin} and with the {@code size}.
     * 
     * @param begin the beginning index (<i>inclusive</i>)
     *        of the wrapped part of the {@link #Strings(java.lang.String[]) array}.
     * @param size the size of the wrapped part of the {@code array}.
     * @return a new wrapper for the same array from the {@code begin} and with the {@code size}.
     * @see #getBegin()
     * @see #getEnd()
     * @see #size()
     */
    public Strings from(int begin, int size) { return new Strings(array, begin, size); }
    
    /**
     * Returns a new wrapper for the same array from the {@code begin} and to the {@code end}.
     * 
     * @param begin the beginning index (<i>inclusive</i>)
     *        of the wrapped part of the {@link #Strings(java.lang.String[]) array}.
     * @param end the ending index (<i>exclusive</i>)
     *        of the wrapped part of the {@link #Strings(java.lang.String[]) array}.
     * @return a new wrapper for the same array from the {@code begin} and to the {@code end}.
     * @see #getBegin()
     * @see #getEnd()
     * @see #size()
     */
    public Strings fromTo(int begin, int end) { return new Strings(array, begin, end - begin); }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(2 * size + 1).append("[").append(array[begin]);
        for (int i = begin + 1; i < begin + size; ++i) {
            builder.append(", ").append(array[i]);
        }
        return builder.append("]").toString();
    }
}
