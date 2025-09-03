package druyaned.corejava.util.utf;

/**
 * Representation of the <a href="https://home.unicode.org/">Unicode code point</a>.
 * @author druyaned
 * @see UTF8Unit
 * @see UTF16Unit
 */
public interface UTFUnit extends Comparable<UTFUnit> {
    
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
    
    /**
     * Throws the {@link IllegalArgumentException} if the hexadecimal value of the
     * Unicode code point is not from the intervals {@code U+0000..U+D7FF} and
     * {@code U+E000..U+10FFFF}.
     * 
     * @param hexValue the hexadecimal value of the code point
     * @throws IllegalArgumentException if the hexadecimal value of the Unicode code point
     *      is not from the intervals {@code U+0000..U+D7FF} and {@code U+E000..U+10FFFF}
     */
    public static void checkCodePoint(int hexValue) {
        if (
                hexValue < 0x0000 ||
                (hexValue >= 0xD800 && hexValue <= 0xDFFF) ||
                hexValue > 0x10FFFF
        ) {
            throw new IllegalArgumentException(
                    "hexCodePoint=" + hexValue +
                    " must be in [U+0000..U+D7FF] or [U+E000..U+10FFFF]"
            );
        }
    }
    
    /**
     * Throws the {@link IllegalArgumentException} if the hexadecimal value of the
     * <a href="https://www.unicode.org/charts/PDF/UD800.pdf">high surrogate</a>
     * of the Unicode code point is not from the interval {@code U+D800..U+D7FF}.
     * 
     * @param highSurrogate of the Unicode code point
     * @throws IllegalArgumentException if the hexadecimal value of the high surrogate
     *      of the Unicode code point is not from the interval {@code U+D800..U+D7FF}.
     */
    public static void checkHighSurrogate(int highSurrogate) {
        if (highSurrogate < 0xD800 || highSurrogate > 0xDBFF) {
            throw new IllegalArgumentException(
                    "highSurrogate=" + highSurrogate + " must be in [D800..DBFF]"
            );
        }
    }
    
    /**
     * Throws the {@link IllegalArgumentException} if the hexadecimal value of the
     * <a href="https://www.unicode.org/charts/PDF/UDC00.pdf">low surrogate</a>
     * of the Unicode code point is not from the interval {@code U+DC00..U+DFFF}.
     * 
     * @param lowSurrogate of the Unicode code point
     * @throws IllegalArgumentException if the hexadecimal value of the low surrogate
     *         of the Unicode code point is not from the interval {@code U+DC00..U+DFFF}.
     */
    public static void checkLowSurrogate(int lowSurrogate) {
        if (lowSurrogate < 0xDC00 || lowSurrogate > 0xDFFF) {
            String m = "lowSurrogate=" + lowSurrogate + " must be in [DC00..DFFF]";
            throw new IllegalArgumentException(m);
        }
    }
    
    /**
     * Returns the hexadecimal value of the Unicode code point.
     * @return hexadecimal value of the Unicode code point
     */
    int getHexValue();
    
    /**
     * Returns the Unicode cope point as String.
     * @return Unicode cope point as String
     */
    String asString();
    
    @Override default int compareTo(UTFUnit other) {
        return getHexValue() - other.getHexValue();
    }
    
}
