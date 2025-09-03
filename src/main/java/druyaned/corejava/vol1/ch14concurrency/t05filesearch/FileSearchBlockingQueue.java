package druyaned.corejava.vol1.ch14concurrency.t05filesearch;

import static druyaned.corejava.vol1.ch14concurrency.T05FileSearches.QUEUE_CAP;
import static druyaned.corejava.vol1.ch14concurrency.T05FileSearches.THREAD_COUNT;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class FileSearchBlockingQueue implements FileSearchable {
    
    private BlockingQueue<Path> queue;
    private List<Path> result;
    private final AtomicInteger entryCount = new AtomicInteger();
    private Path root;
    private String target;
    
    @Override public void run() {
        // Initialization
        queue = new ArrayBlockingQueue<>(QUEUE_CAP);
        result = Collections.synchronizedList(new ArrayList<>());
        entryCount.set(0);
        // Filler
        Runnable fillerTask = () -> {
            try {
                // Main filler actions
                fill(root);
                queue.put(DUMMY);
            } catch (InterruptedException exc) {
                throw new IllegalStateException(exc);
            } catch (IOException exc) {
                throw new UncheckedIOException(exc);
            }
        };
        new Thread(fillerTask).start();
        // Extractors
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            Runnable extractorTask = () -> {
                try {
                    // Main extractor actions
                    Path taken;
                    while ( !(taken = queue.take()).equals(DUMMY) ) {
                        entryCount.incrementAndGet();
                        if (taken.getFileName().toString().equals(target))
                            result.add(taken);
                    }
                    queue.put(DUMMY);
                } catch (InterruptedException exc) {
                    throw new IllegalStateException(exc);
                }
            };
            (threads[i] = new Thread(extractorTask)).start();
        }
        try {
            for (int i = 0; i < THREAD_COUNT; i++)
                threads[i].join();
        } catch (InterruptedException exc) {
            throw new IllegalStateException(exc);
        }
    }
    
    private void fill(Path path) throws InterruptedException, IOException {
        queue.put(path);
        if (Files.isDirectory(path)) {
            for (File file : path.toFile().listFiles())
                fill(file.toPath());
        }
    }
    
    @Override public FileSearchBlockingQueue set(Path root, String target) {
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
    
    private static final Path DUMMY = Path.of("");
    
}
