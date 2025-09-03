package druyaned.corejava.util.utf;

/**
 * <a href="https://en.wikipedia.org/wiki/UTF-16">UTF-16LE</a>
 * representation of the <a href="https://home.unicode.org/">Unicode code point</a>.
 * 
 * @author druyaned
 * @see UTF8Unit
 */
public final class UTF16Unit implements UTFUnit {
    
    // High Surrogate Area: D800..DBFF
    // Low  Surrogate Area: DC00..DFFF
    
    private final int hexValue;
    private final int high;
    private final int low;
    
    /**
     * Gets the hexadecimal value of the Unicode code point
     * from intervals {@code U+0000..U+D7FF} and {@code U+E000..U+10FFFF}
     * and constructs a {@code UTF-16LE} representation of the code point.
     * 
     * @param hexValue the hexadecimal value of the code point
     */
    public UTF16Unit(int hexValue) {
        UTFUnit.checkCodePoint(hexValue);
        this.hexValue = hexValue;
        if (hexValue < 0x10000) {
            high = hexValue;
            low = -1; // to indicate whether the point has surrogates
        } else {
            int Y = (hexValue - 0x10000) / 0x400;
            int X = hexValue % 0x400;
            high = Y + 0xD800;
            low = X + 0xDC00;
        }
    }
    
    /**
     * Gets the hexadecimal values of the
     * <a href="https://www.unicode.org/charts/PDF/UD800.pdf">high surrogate</a>
     * and
     * <a href="https://www.unicode.org/charts/PDF/UDC00.pdf">low surrogate</a>
     * of the Unicode code point from intervals {@code U+D800..U+D7FF} and
     * {@code U+DC00..U+DFFF} respectively and provides a {@code UTF-16LE}
     * representation of the code point.
     * 
     * @param highSurrogate area of the Unicode code point
     * @param lowSurrogate area of the Unicode code point
     */
    public UTF16Unit(int highSurrogate, int lowSurrogate) {
        UTFUnit.checkLowSurrogate(highSurrogate);
        UTFUnit.checkLowSurrogate(lowSurrogate);
        high = highSurrogate;
        low = lowSurrogate;
        int X = low - 0xDC00;
        int Y = high - 0xD800;
        hexValue = X + Y * 0x400 + 0x10000;
    }
    
    /**
     * Returns the hexadecimal value of the
     * <a href="https://www.unicode.org/charts/PDF/UD800.pdf">high surrogate</a>
     * or {@code -1} if the code point is not represented as surrogates.
     * 
     * @return hexadecimal value of the high surrogate
     *      or {@code -1} if the code point is not represented as surrogates
     * @see UTF16Unit#hasSurrogates()
     */
    public int getHigh() {
        if (!hasSurrogates()) {
            return -1;
        }
        return high;
    }
    
    /**
     * Returns the hexadecimal value of
     * <a href="https://www.unicode.org/charts/PDF/UDC00.pdf">low surrogate</a>
     * or {@code -1} if the code point is not represented as surrogates.
     * 
     * @return hexadecimal value of low surrogate
     *      or {@code -1} if the code point is not represented as surrogates
     * @see UTF16Unit#hasSurrogates()
     */
    public int getLow() {
        if (!hasSurrogates()) {
            return -1;
        }
        return low;
    }
    
    /**
     * Returns whether the code point is represented
     * as a <a href="https://www.unicode.org/charts/PDF/UD800.pdf">high</a>
     * and <a href="https://www.unicode.org/charts/PDF/UDC00.pdf">low</a> surrogates.
     * <P><i>NOTE</i>
     * <ol>
     *  <li>High Surrogate Area: <code>0xD800..0xDBFF</code>;</li>
     *  <li>Low  Surrogate Area: <code>0xDC00..0xDFFF</code>.</li>
     * </ol>
     * 
     * @return {@code true} if the code point is represented as a high and low surrogates,
     *      otherwise - {@code false}.
     */
    public boolean hasSurrogates() {
        return low != -1;
    }
    
    @Override public int getHexValue() {
        return hexValue;
    }
    
    @Override public String asString() {
        return hasSurrogates() ?
                String.format("%c%c", (char)high, (char)low) :
                String.valueOf((char)high);
    }
    
    @Override public String toString() {
        return String.format(
                "UTF16Unit{hexValue=0x%h, high=0x%h, low=0x%h}",
                hexValue, high, low
        );
    }
    
}
