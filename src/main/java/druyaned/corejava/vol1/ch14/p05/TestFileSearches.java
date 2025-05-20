package druyaned.corejava.vol1.ch14.p05;

import static druyaned.ConsoleColors.bold;
import java.nio.file.Path;

public class TestFileSearches implements Runnable {
    
    public static final int QUEUE_CAP = 10_000;
    public static final int THREAD_COUNT = 32;
    
    @Override public void run() {
        System.out.println("Compare FileSearchBlockingQueue and FileSearchSynchronized.");
        // Initialization
        String userHome = System.getProperty("user.home");
        Path root = Path.of(userHome, "Documents");
        String target = "src";
        System.out.println("Root:   " + root);
        System.out.println("Target: " + target);
        // Test searches
        long t1 = testFileSearch(new FileSearchBlockingQueue(), root, target);
        long t2 = testFileSearch(new FileSearchSynchronized(), root, target);
        long t3 = testFileSearch(new FileSearch(), root, target);
        long t4 = testFileSearch(new FileSearchForkPool(), root, target);
        System.out.println("Differences in time between searches:");
        System.out.printf("  FileSearchBlockingQueue:   %.2f%%\n", calcPercentage(t1, t4));
        System.out.printf("  FileSearchSynchronized:    %.2f%%\n", calcPercentage(t2, t4));
        System.out.printf("  FileSearch:                %.2f%%\n", calcPercentage(t3, t4));
        System.out.printf("  FileSearchForkPool:        %.2f%%\n", calcPercentage(t4, t4));
        // Summary of BlockingQueue methods
        System.out.print(
        """
        Summary of BlockingQueue methods:
            Throws:         add(e),     remove(),   element()
            Special value:  offer(e),   poll(),     peek()
            Blocks:         put(e),     take()
            Times out:      offer(e, time, unit), poll(time, unit)
        """);
    }
    
    private long testFileSearch(FileSearchable search, Path root, String target) {
        System.out.printf("Searching '%s' in '%s' by %s\n",
                target, root, bold(search.getClass().getSimpleName()));
        long t1 = System.currentTimeMillis();
        search.set(root, target).run();
        long t2 = System.currentTimeMillis();
        long timeSpent = t2 - t1;
        System.out.printf("  Time spent:  %d (millis)\n", timeSpent);
        System.out.printf("  Result size: %d\n", search.getResult().size());
        System.out.printf("  Entry count: %d\n", search.getEntryCount());
        return timeSpent;
    }
    
    private double calcPercentage(double numerator, double denominator) {
        return (numerator / denominator) * 100d;
    }
    
}
