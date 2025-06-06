package druyaned.corejava.vol1.ch14.p05;

import static druyaned.corejava.vol1.ch14.p05.TestFileSearches.QUEUE_CAP;
import static druyaned.corejava.vol1.ch14.p05.TestFileSearches.THREAD_COUNT;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

public class FileSearchForkPool implements FileSearchable {
    
    private static final Path DUMMY = Path.of("");
    
    private ForkJoinPool fillerService;
    private BlockingQueue<Path> queue;
    private List<Path> result;
    private AtomicInteger entryCount;
    private Path root;
    private String target;
    
    @Override public void run() {
        // Initialization
        fillerService = new ForkJoinPool(THREAD_COUNT);
        queue = new ArrayBlockingQueue<>(QUEUE_CAP);
        result = Collections.synchronizedList(new ArrayList<>());
        entryCount = new AtomicInteger(0);
        // Fillers
        fillerService.submit(() -> {
            fillerService.invoke(new FillerTask(this, root));
            try {
                queue.put(DUMMY);
            } catch (InterruptedException exc) {
                throw new IllegalStateException(exc);
            }
        });
        // Extractors
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++)
            (threads[i] = new Thread(new ExtractorTask(this))).start();
        try {
            for (int i = 0; i < THREAD_COUNT; i++)
                threads[i].join();
        } catch (InterruptedException exc) {
            throw new IllegalStateException(exc);
        }
        // Stop fillerService
        fillerService.shutdown();
    }
    
    @Override public FileSearchForkPool set(Path root, String target) {
        this.root = root;
        this.target = target;
        return this;
    }
    
    @Override public List<Path> getResult() {
        return new ArrayList<>(result);
    }
    
    @Override public int getEntryCount() {
        return entryCount.get();
    }
    
    @Override public Path getRoot() {
        return root;
    }
    
    @Override public String getTarget() {
        return target;
    }
    
    private static class FillerTask extends RecursiveTask<Object> {
        final Path path;
        final FileSearchForkPool pool;
        FillerTask(FileSearchForkPool pool, Path path) {
            this.pool = pool;
            this.path = path;
        }
        @Override protected Object compute() {
            try {
                pool.queue.put(path);
                if (Files.isDirectory(path)) {
                    for (File file : path.toFile().listFiles())
                        pool.fillerService.submit(new FillerTask(pool, file.toPath()));
                }
                return null;
            } catch (InterruptedException exc) {
                throw new IllegalStateException(exc);
            }
        }
    }
    
    private static class ExtractorTask implements Runnable {
        final FileSearchForkPool pool;
        public ExtractorTask(FileSearchForkPool pool) {
            this.pool = pool;
        }
        @Override public void run() {
            try {
                Path taken;
                while ( !(taken = pool.queue.take()).equals(DUMMY) ) {
                    pool.entryCount.incrementAndGet();
                    if (taken.getFileName().toString().equals(pool.target))
                        pool.result.add(taken);
                }
                pool.queue.put(DUMMY);
            } catch (InterruptedException exc) {
                throw new IllegalStateException(exc);
            }
        }
    }
    
}
