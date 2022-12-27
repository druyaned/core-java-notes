package com.github.druyaned.learn_java.vol1.chapter14;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

/*
План такой (syn - синхронизированно, non - несинхронизированно):
- [syn] взятие каталога из очереди
- [non] проход по файлам, получение найденных файлов и новых каталогов
- [syn] добавление найденных к результатам
- [syn] добавляем каталоги в очередь

- очередь пустая => поток в ожидание
- отслеживается количество не ожидающих потоков (n)
- n=0 => заканчиваем поиск
*/

public class SearchThreaded extends SearchRecursive {

    // Static part
    
    public static final int AMOUNT_OF_SEARCHERS = 16;
    
    // Non-static part

    private final LinkedList<File> dirQueue;
    private volatile int activeSearchers;
    
    public SearchThreaded(String keyword) {
        super(keyword);
        dirQueue = new LinkedList<>();
        activeSearchers = 0;
    }

    @Override
    public long start() {
        checkAndSetIsStarted();
        int beginThreadCount = Thread.activeCount();
        activeSearchers = 0;
        
        long beginTime = System.currentTimeMillis();
        search(keyword, home);
        try {
            while (beginThreadCount != Thread.activeCount()) {
                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return beginTime;
    }

    private void search(String keyword, File home) {
        dirQueue.add(home);

        for (int i = 0; i < AMOUNT_OF_SEARCHERS; ++i) {
            Runnable searcher = () -> {
                File dir;
                ArrayList<File> newKeyFiles;
                ArrayList<File> newDirs;
                
                while (activeSearchers != 0) {
                    try {
                        dir = getDir();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (activeSearchers == 0) {
                        return;
                    }
                    newKeyFiles = new ArrayList<>();
                    newDirs = new ArrayList<>();
                    dirSearch(dir, keyword, newKeyFiles, newDirs);
                    addNewKeyFiles(newKeyFiles);
                    addNewDirs(newDirs);
                }
            };
            ++activeSearchers;
            new Thread(searcher).start();
        }
    }

    private synchronized File getDir() throws InterruptedException {
        File dir = dirQueue.poll();
        while (dir == null) {
            --activeSearchers;
            if (activeSearchers == 0) {
                notifyAll();
                return null;
            }
            wait();
            ++activeSearchers;
            dir = dirQueue.poll();
        }
        return dir;
    }

    private void dirSearch(File dir, String keyword,
        ArrayList<File> newKeyFiles, ArrayList<File> newDirs) {
        
        File[] dirFiles = dir.listFiles();
        if (dirFiles == null) {
            return;
        }
        newKeyFiles.ensureCapacity(dirFiles.length);
        newDirs.ensureCapacity(dirFiles.length);

        for (File dirFile : dirFiles) {
            if (dirFile.isDirectory()) {
                newDirs.add(dirFile);
            } else if (dirFile.getName().equals(keyword)) {
                newKeyFiles.add(dirFile);
            }
        }
    }

    private synchronized void addNewKeyFiles(ArrayList<File> newKeyFiles) {
        for (File keyFile : newKeyFiles) {
            keyFiles.add(keyFile);
        }
    }

    private synchronized void addNewDirs(ArrayList<File> newDirs) {
        for (File dir : newDirs) {
            dirQueue.add(dir);
            notify();
        }
    }
}
