package druyaned.corejava.vol1.ch08generics.t05badarray;

/**
 * Not very safe way of creation a generic array.
 * It can be broken like this:
 * {@snippet :
 * Object[] objects = make(LocalDate.now(), LocalDate.now());
 * objects[0] = new String();
 * }
 */
public interface DoubtfulGenericArray {
    
    @SafeVarargs
    static <T> T[] make(T... arr) { return arr; }
    
}
