package com.github.druyaned.learn_java.util.utf;

/**
 * Representation of the <a href="https://www.unicode.org/">Unicode code point</a>.
 * 
 * @author druyaned
 * @see UTF8Unit
 * @see UTF16Unit
 */
public interface UTFUnit {
    
    /** Minimum value of a Unicode code point (hexadecimal). */
    public static final int MIN_POINT = 0;
    
    /** Maximum value of a Unicode code point (hexadecimal). */
    public static final int MAX_POINT = 0x10FFFF;
    
    /** Minimum value of a high surrogate of a Unicode code point (hexadecimal). */
    public static final int MIN_HIGH_SURROGATE = 0xD800;
    
    /** Maximum value of a high surrogate of a Unicode code point (hexadecimal). */
    public static final int MAX_HIGH_SURROGATE = 0xDBFF;
    
    /** Minimum value of a low surrogate of a Unicode code point (hexadecimal). */
    public static final int MIN_LOW_SURROGATE = 0xDC00;
    
    /** Maximum value of a low surrogate of a Unicode code point (hexadecimal). */
    public static final int MAX_LOW_SURROGATE = 0xDFFF;
    
//-Non-static---------------------------------------------------------------------------------------
    
    /**
     * Returns the Unicode cope point as String.
     * 
     * @return the Unicode cope point as String.
     */
    String asString();
    
    /**
     * Returns the hexadecimal value of the Unicode code point.
     * 
     * @return the hexadecimal value of the Unicode code point
     */
    int getHexValue();
    
//-Default-Methods----------------------------------------------------------------------------------
    
    /**
     * Throws the {@link IllegalArgumentException} if the hexadecimal value of the
     * <a href="https://www.unicode.org/">Unicode code point</a> is <u>not</u>
     * from the intervals {@code U+0000..U+D7FF} and {@code U+E000..U+10FFFF}.
     * 
     * @param hexCodePoint the hexadecimal value of the code point.
     * @throws IllegalArgumentException if the hexadecimal value of the
     *         <a href="https://www.unicode.org/">Unicode code point</a> is <u>not</u>
     *         from the intervals {@code U+0000..U+D7FF} and {@code U+E000..U+10FFFF}.
     */
    default void checkCodePoint(int hexCodePoint) {
        if (hexCodePoint < 0x0000 ||
            (hexCodePoint >= 0xD800 && hexCodePoint <= 0xDFFF) ||
            hexCodePoint > 0x10FFFF) {
            
            String m = "hexCodePoint=" + hexCodePoint +
                       " must be in [U+0000..U+D7FF] or [U+E000..U+10FFFF]";
            throw new IllegalArgumentException(m);
        }
    }
    
    /**
     * Throws the {@link IllegalArgumentException} if the hexadecimal value of the
     * <a href="https://www.unicode.org/charts/PDF/UD800.pdf">high surrogate</a>
     * of the
     * <a href="https://www.unicode.org/">Unicode code point</a> is <u>not</u>
     * from the interval {@code U+D800..U+D7FF}.
     * 
     * @param highSurrogate
     *        the <a href="https://www.unicode.org/charts/PDF/UD800.pdf">high surrogate</a>
     *        of the Unicode code point.
     * @throws IllegalArgumentException if the hexadecimal value of the
     *         <a href="https://www.unicode.org/charts/PDF/UD800.pdf">high surrogate</a>
     *         of the
     *         <a href="https://www.unicode.org/">Unicode code point</a> is <u>not</u>
     *         from the interval {@code U+D800..U+D7FF}.
     */
    default void checkHighSurrogate(int highSurrogate) {
        if (highSurrogate < 0xD800 || highSurrogate > 0xDBFF) {
            String m = "highSurrogate=" + highSurrogate + " must be in [D800..DBFF]";
            throw new IllegalArgumentException(m);
        }
    }
    
    /**
     * Throws the {@link IllegalArgumentException} if the hexadecimal value of the
     * <a href="https://www.unicode.org/charts/PDF/UDC00.pdf">low surrogate</a>
     * of the
     * <a href="https://www.unicode.org/">Unicode code point</a> is <u>not</u>
     * from the interval {@code U+DC00..U+DFFF}.
     * 
     * @param lowSurrogate
     *        the <a href="https://www.unicode.org/charts/PDF/UDC00.pdf">low surrogate</a>
     *        of the Unicode code point.
     * @throws IllegalArgumentException if the hexadecimal value of the
     *         <a href="https://www.unicode.org/charts/PDF/UDC00.pdf">low surrogate</a>
     *         of the
     *         <a href="https://www.unicode.org/">Unicode code point</a> is <u>not</u>
     *         from the interval {@code U+DC00..U+DFFF}.
     */
    default void checkLowSurrogate(int lowSurrogate) {
        if (lowSurrogate < 0xDC00 || lowSurrogate > 0xDFFF) {
            String m = "lowSurrogate=" + lowSurrogate + " must be in [DC00..DFFF]";
            throw new IllegalArgumentException(m);
        }
    }
}
