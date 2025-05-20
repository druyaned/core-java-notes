package druyaned.corejava.vol1.ch14.p08;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TestCollections implements Runnable {
    
    @Override public void run() {
        // ConcurrentHashMap (keys and values are not null)
        // ConcurrentSkipListMap <--> TreeMap
        int initCapacity = 16;
        float loadFactor = 0.75f;
        int concurrencyLevel = 2;
        ConcurrentHashMap<String, AtomicInteger> hashMap = new ConcurrentHashMap<>(
                initCapacity,
                loadFactor,
                concurrencyLevel
        );
        // Atomic update
        //   putIfAbsent
        hashMap.putIfAbsent("key01", new AtomicInteger(0));
        hashMap.get("key01").incrementAndGet();
        //   computeIfAbsent
        hashMap.computeIfAbsent("key02", k -> new AtomicInteger(0)).addAndGet(2);
        //   merge
        hashMap.merge(
                "key03",
                new AtomicInteger(0),
                (v0, v1) -> {
                    v0.addAndGet(v1.get());
                    return v0; // if null, the entry is removing
                }
        ).addAndGet(2);
        System.out.println("hashMap=" + hashMap);
        // Grouping operations: search, reduce, forEach
        // Available in several versions: *Keys, *Values, *, *Entries
        //   search
        String key02 = hashMap.searchKeys(16L, k -> {
            if (k.equals("key02"))
                return k;
            return null;
        });
        if (key02 != null)
            System.out.printf("Key '%s' was found.\n", key02);
        else
            System.out.println("Key was not found!");
        //   forEach
        System.out.println("Keys with values greater than or equal to 2:");
        hashMap.forEach(
                // threshold
                16,
                // transformer with filter (nulls are ignored)
                (k, v) -> (v.get() >= 2) ? k : null,
                // consumer
                k -> System.out.println("  " + k)
        );
        //   reduce
        Integer sum = hashMap.reduceValues(
                // threshold
                16,
                // transformer with filter (nulls are ignored)
                v -> (v.get() <= 10) ? v.get() : null,
                // reducer
                Integer::sum
        );
        System.out.println("Sum of values less than or equal to 10: " + sum);
        System.out.print(
        """
        There are CopyOnWriteArrayList and CopyOnWriteArraySet
            that provides iteration without throwing exception
            because of dealing with array copies.
        Collections.synchronized*: Map, Set, List.
            These methods provides thread-safe wrappers of the collections.
        Arrays.parallelSort(...);
            Arrays class provides parallel sorting
            and some other useful algorithms.
        """);
        int[] a = new int[19];
        System.out.println("a=" + Arrays.toString(a));
        System.out.println("Arrays.parallelSetAll(a, i -> i % 10 + 1);");
        Arrays.parallelSetAll(a, i -> i % 10 + 1);
        System.out.println("a=" + Arrays.toString(a));
        System.out.println("Arrays.parallelPrefix(a, (x, y) -> x + y);");
        Arrays.parallelPrefix(a, (x, y) -> x + y);
        System.out.println("a=" + Arrays.toString(a));
    }
    
}
