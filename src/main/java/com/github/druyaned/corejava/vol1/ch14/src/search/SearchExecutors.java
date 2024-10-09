package com.github.druyaned.corejava.vol1.ch14.src.search;

import java.io.File;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

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

public class SearchExecutors extends SearchRecursive {
    
    private final ThreadPoolExecutor pool;
    private int count;
    
    public SearchExecutors(File home, String keyword) {
        super(home, keyword);
        pool = (ThreadPoolExecutor)Executors.newCachedThreadPool();
        count = 0;
    }

    @Override public long start() {
        checkAndSetIsStarted();
        long beginTime = System.currentTimeMillis();
        DirSearch search = new DirSearch(home, keyword, keyFiles, pool);
        try {
            count = pool.submit(search).get();
        } catch (InterruptedException | ExecutionException | CancellationException exc) {
            throw new RuntimeException(exc);
        }
        pool.shutdown();
        return beginTime;
    }

    @Override public void showKeyFiles() {
        super.showKeyFiles();
        System.out.println("count=" + count);
        System.out.println("LargestPoolSize=" + pool.getLargestPoolSize());
    }
    
}

class DirSearch implements Callable<Integer> {
    
    private final File where;
    private final String keyword;
    private final LinkedList<File> keyFiles;
    private final ExecutorService pool;

    public DirSearch(
            File where, String keyword,
            LinkedList<File> keyFiles, ExecutorService pool
    ) {
        this.where = where;
        this.keyword = keyword;
        this.keyFiles = keyFiles;
        this.pool = pool;
    }

    @Override public Integer call()
    throws InterruptedException, ExecutionException, CancellationException
    {
        int count = 0;
        if (where.isDirectory()) {
            File[] dirFiles = where.listFiles();
            if (dirFiles == null) {
                return count;
            }
            for (File dirFile : dirFiles) {
                DirSearch search = new DirSearch(
                        dirFile, keyword, keyFiles, pool
                );
                Future<Integer> result = pool.submit(search);
                count += result.get();
            }
        } else {
            File file = where;
            if (file.getName().equals(keyword)) {
                addToKeyFiles(file);
            }
        }
        return count;
    }

    private synchronized void addToKeyFiles(File file) {
        keyFiles.add(file);
    }
    
}
