package druyaned.corejava.vol1.ch12gui.t01calc.calc;

import static druyaned.corejava.vol1.ch12gui.t01calc.cmd.Dot.DOT_CHAR;

public class InputField implements Cloneable {
    
    private final char[] value = new char[CAPACITY];
    private int size = 0;
    private int dotCount = 0;
    
    public InputField() {
    }
    
    public InputField(InputField toClone) {
        System.arraycopy(toClone.value, 0, value, 0, toClone.size);
        size = toClone.size;
        dotCount = toClone.dotCount;
    }
    
    public char[] value() {
        return value;
    }
    
    public int size() {
        return size;
    }
    
    public int dotCount() {
        return dotCount;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == CAPACITY;
    }
    
    public char charAt(int i) {
        if (i < 0 || size <= i)
            throw new IndexOutOfBoundsException("size=" + size + " i=" + i);
        return value[i];
    }
    
    public InputField append(char ch) {
        value[size++] = ch;
        if (ch == DOT_CHAR)
            dotCount++;
        return this;
    }
    
    public char removeLast() {
        if (value[size - 1] == DOT_CHAR)
            dotCount--;
        return value[--size];
    }
    
    public void clear() {
        size = 0;
        dotCount = 0;
    }
    
    @Override public InputField clone() {
        return new InputField(this);
    }
    
    @Override public String toString() {
        return size == 0 ? "" : new String(value, 0, size);
    }
    
    public static final int CAPACITY = 128;
    
}
