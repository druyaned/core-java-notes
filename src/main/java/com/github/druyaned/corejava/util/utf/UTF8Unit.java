package com.github.druyaned.corejava.util.utf;

import java.nio.charset.StandardCharsets;

/**
 * <a href="https://en.wikipedia.org/wiki/UTF-8">UTF-8</a>
 * representation of the <a href="https://home.unicode.org">Unicode code point</a>.
 * 
 * @author druyaned
 * @see UTF16Unit
 */
public final class UTF8Unit implements UTFUnit {
    
    // fst       lst      byte1     byte2     byte3     byte4
    // 0x000000..0x00007F 0xxx-xxxx
    // 0x000080..0x0007FF 110x-xxxx 10xx-xxxx
    // 0x000800..0x00FFFF 1110-xxxx 10xx-xxxx 10xx-xxxx
    // 0x010000..0x10FFFF 1111-0xxx 10xx-xxxx 10xx-xxxx 10xx-xxxx
    
    private final int hexValue;
    private final int size;
    private final int byte1;
    private final int byte2;
    private final int byte3;
    private final int byte4;
    
    /**
     * Gets the hexadecimal value of the Unicode code point
     * from intervals {@code U+0000..U+D7FF} and {@code U+E000..U+10FFFF}
     * and constructs a {@code UTF-8} representation of the code point.
     * 
     * @param hexValue the hexadecimal value of the code point
     */
    public UTF8Unit(int hexValue) {
        UTFUnit.checkCodePoint(hexValue);
        this.hexValue = hexValue;
        final int MIN1 = 0x000000, MAX1 = 0x00007F;
        final int MIN2 = 0x000080, MAX2 = 0x0007FF;
        final int MIN3 = 0x000800, MAX3 = 0x00FFFF;
        final int MIN4 = 0x010000, MAX4 = 0x10FFFF;
        if (hexValue >= MIN1 && hexValue <= MAX1) {
            size = 1;
            byte1 = hexValue;
            byte2 = byte3 = byte4 = -1;
        } else if (hexValue >= MIN2 && hexValue <= MAX2) {
            size = 2;
            byte1 = ((hexValue & 0x7C0) >> 6) + 0xC0;
            byte2 = (hexValue & 0x3F) + 0x80;
            byte3 = byte4 = -1;
        } else if (hexValue >= MIN3 && hexValue <= MAX3) {
            size = 3;
            byte1 = ((hexValue & 0xF000) >> 12) + 0xE0;
            byte2 = ((hexValue & 0xFC0) >> 6) + 0x80;
            byte3 = (hexValue & 0x3F) + 0x80;
            byte4 = -1;
        } else if (hexValue >= MIN4 && hexValue <= MAX4) {
            size = 4;
            byte1 = ((hexValue & 0x1C0000) >> 18) + 0xF0;
            byte2 = ((hexValue & 0x3F000) >> 12) + 0x80;
            byte3 = ((hexValue & 0xFC0) >> 6) + 0x80;
            byte4 = (hexValue & 0x3F) + 0x80;
        } else {
            throw new IllegalArgumentException("invalid hexCodePoint");
        }
    }
    
    /**
     * Returns the amount of bytes of the UTF-8 representation of the Unicode code point.
     * @return the amount of bytes of the UTF-8 representation of the Unicode code point
     */
    public int size() {
        return size;
    }
    
    /**
     * Returns the first byte of the UTF-8 representation of the Unicode code point.
     * @return the first byte of the UTF-8 representation of the Unicode code point
     */
    public int getByte1() {
        return byte1;
    }
    
    /**
     * The second byte of the UTF-8 representation of the Unicode code point
     * or {@code -1} if the amount of bytes is less than {@code 2}.
     * 
     * @return the second byte of the UTF-8 representation of the Unicode code point
     *      or {@code -1} if the amount of bytes is less than {@code 2}
     */
    public int getByte2() {
        return byte2;
    }
    
    /**
     * The third byte of the UTF-8 representation of the Unicode code point
     * or {@code -1} if the amount of bytes is less than {@code 3}.
     * 
     * @return the third byte of the UTF-8 representation of the Unicode code point
     *      or {@code -1} if the amount of bytes is less than {@code 3}
     */
    public int getByte3() {
        return byte3;
    }
    
    /**
     * The fourth byte of the UTF-8 representation of the Unicode code point
     * or {@code -1} if the amount of bytes is less than {@code 4}.
     * 
     * @return the fourth byte of the UTF-8 representation of the Unicode code point
     *      or {@code -1} if the amount of bytes is less than {@code 4}
     */
    public int getByte4() {
        return byte4;
    }
    
    @Override public int getHexValue() {
        return hexValue;
    }
    
    @Override public String asString() {
        byte[] bytes;
        switch (size) {
            case 1 -> bytes = new byte[] { (byte)byte1 };
            case 2 -> bytes = new byte[] { (byte)byte1, (byte)byte2 };
            case 3 -> bytes = new byte[] { (byte)byte1, (byte)byte2, (byte)byte3 };
            case 4 -> bytes = new byte[] { (byte)byte1, (byte)byte2, (byte)byte3, (byte)byte4 };
            default -> throw new AssertionError();
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }
    
    @Override public String toString() {
        return String.format(
                "UTF8Unit{hexValue=0x%h, byte1=0x%h, byte2=0x%h, byte3=0x%h, byte4=0x%h}",
                hexValue, byte1, byte2, byte3, byte4
        );
    }
    
}
