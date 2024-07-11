package com.github.druyaned.horstmann.corejava.vol1.ch14.src.filesearch;

import static com.github.druyaned.ConsoleColors.bold;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestFileSearch implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Running TestFileSearch"));
        FileSearch search = new FileSearch();
        Path home = Paths.get(System.getProperty("user.home"), "Documents");
        String fileName = "Images.java";
        System.out.println("Home:     " + home);
        System.out.println("File name: " + fileName);
        System.out.println("Searching...");
        long t1 = System.currentTimeMillis();
        search.run(home, fileName);
        long t2 = System.currentTimeMillis();
        System.out.println("Search result:");
        int i = 1;
        for (Path path : search.getResult()) {
            System.out.printf("%d. %s\n", i++, path);
        }
        System.out.println("Time spent:      " + (t2 - t1) + " (ms)");
        System.out.println("Check count:     " + search.getCheckCount());
        System.out.println("Max buffer size: " + search.getMaxBufferSize());
    }
    
}
