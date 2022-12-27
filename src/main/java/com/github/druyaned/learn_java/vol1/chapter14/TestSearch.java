package com.github.druyaned.learn_java.vol1.chapter14;

import static com.github.druyaned.ConsoleColors.bold;
import static com.github.druyaned.learn_java.App.APP_IN;

public class TestSearch {
    public static final int DELAY = 10;

    public static void run() {
        System.out.println("\n" + bold("Testing searching a file"));

        // Recursive
        System.out.print("Enter a file name to search: ");
        String keyword = APP_IN.nextLine();
        SearchRecursive searchRecursive = new SearchRecursive(keyword);
        long beginTime = searchRecursive.start();
        printSpentTime(beginTime, "Recursive");
        searchRecursive.showKeyFiles();

        // Non-recursive
        SearchThreaded searchThreaded = new SearchThreaded(keyword);
        beginTime = searchThreaded.start();
        printSpentTime(beginTime, "Non-recursive");
        searchThreaded.showKeyFiles();

        // With executors
        SearchExecutors searchExecutors = new SearchExecutors(keyword);
        beginTime = searchExecutors.start();
        printSpentTime(beginTime, "WithExecutors");
        searchExecutors.showKeyFiles();
    }

    private static void printSpentTime(long beginTime, String searchType) {
        double spentTime = (System.currentTimeMillis() - beginTime) / 1000D;
        System.out.println(bold(searchType) + " SpentTime=" + spentTime + " (sec)");
    }
}
