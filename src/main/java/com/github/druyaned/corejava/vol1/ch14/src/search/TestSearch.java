package com.github.druyaned.corejava.vol1.ch14.src.search;

import static com.github.druyaned.ConsoleColors.bold;
import java.io.File;

public class TestSearch implements Runnable {
    
    public static final int DELAY = 10;
    
    @Override public void run() {
        System.out.println("\n" + bold("Testing searching a file"));
        String fileName = "Images.java";
        File home = new File(System.getProperty("user.home"), "Documents");
        System.out.println("File name: " + fileName);
        System.out.println("Home:      " + home);
        // Recursive
        SearchRecursive searchRecursive = new SearchRecursive(home, fileName);
        long beginTime = searchRecursive.start();
        printSpentTime(beginTime, "Recursive");
        searchRecursive.showKeyFiles();
        // Non-recursive
        SearchThreaded searchThreaded = new SearchThreaded(home, fileName);
        beginTime = searchThreaded.start();
        printSpentTime(beginTime, "Non-recursive");
        searchThreaded.showKeyFiles();
        // With executors
        SearchExecutors searchExecutors = new SearchExecutors(home, fileName);
        beginTime = searchExecutors.start();
        printSpentTime(beginTime, "WithExecutors");
        searchExecutors.showKeyFiles();
    }

    private static void printSpentTime(long beginTime, String searchType) {
        double spentTime = (System.currentTimeMillis() - beginTime) / 1000D;
        System.out.println(bold(searchType) + " SpentTime=" + spentTime + " (sec)");
    }
    
}
