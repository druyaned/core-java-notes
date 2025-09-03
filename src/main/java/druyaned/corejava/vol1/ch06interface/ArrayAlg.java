package druyaned.corejava.vol1.ch06interface;

public class ArrayAlg {
    
    public static <T extends Comparable<T>> PairNestedClass<T> minmax(T[] arr) {
        T min = arr[0];
        T max = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i].compareTo(min) < 0) { min = arr[i]; }
            if (arr[i].compareTo(max) > 0) { max = arr[i]; }
        }
        return new PairNestedClass<>(min, max);
    }
    
    // Static inner (nested) class
    public static class PairNestedClass<T> {
        private final T first;
        private final T second;
        public PairNestedClass(T first, T second) {
            this.first = first;
            this.second = second;
        }
        public T getFirst() { return first; }
        public T getSecond() { return second; }
    }
    
}
