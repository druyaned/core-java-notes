package com.github.druyaned.corejava.vol1.ch14.src.search;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

public class SearchRecursive {

    protected final LinkedList<File> keyFiles;
    protected final String keyword;
    protected final File home;
    private boolean isStarted;

    public SearchRecursive(File home, String keyword) {
        keyFiles = new LinkedList<>();
        this.keyword = keyword;
        this.home = home;
        isStarted = false;
    }

    protected void checkAndSetIsStarted() {
        if (isStarted) {
            String m = "start() has already been invoked for " + this;
            throw new IllegalStateException(m);
        }
        isStarted = true;
    }

    /**
     * Starts the search.
     * 
     * <P>{@code NOTE}: while {@code overriding} this method
     * {@link #checkAndSetIsStarted()} must be written as 1st line.
     * 
     * @return starting time in milliseconds; {@code 0} if the method has been invoked once.
     */
    public long start() {
        checkAndSetIsStarted();
        long beginTime = System.currentTimeMillis();
        enumerateRecursive(home, keyword);
        return beginTime;
    }

    /**
     * Shows found files after the {@link #start() start} method.
     */
    public void showKeyFiles() {
        if (keyFiles.isEmpty()) {
            System.out.println("Nothing was found");
        } else {
            for (int i = 0; i < keyFiles.size(); ++i) {
                System.out.printf("%d) %s\n", i + 1, keyFiles.get(i));
            }
        }
    }
    
    private void enumerateRecursive(File where, String keyword) {
        ArrayList<File> dirs = search(where, keyword);
        for (File dir : dirs) {
            enumerateRecursive(dir, keyword);
        }
    }

    private ArrayList<File> search(File where, String keyword) {
        File[] dirFiles = where.listFiles();
        if (dirFiles == null) {
            return new ArrayList<>();
        }
        ArrayList<File> newDirs = new ArrayList<>(dirFiles.length);
        for (int i = 0; i < dirFiles.length; ++i) {
            if (dirFiles[i].isDirectory()) {
                newDirs.add(dirFiles[i]);
            } else if (dirFiles[i].getName().equals(keyword)) {
                keyFiles.add(dirFiles[i]);
            }
        }
        return newDirs;
    }
    
}
