package druyaned.corejava.vol1.ch14concurrency.t05filesearch;

import static druyaned.corejava.vol1.ch14concurrency.T05FileSearches.QUEUE_CAP;
import static druyaned.corejava.vol1.ch14concurrency.T05FileSearches.THREAD_COUNT;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileSearchSynchronized implements FileSearchable {
    
    private static final Path DUMMY = Path.of("");
    
    private QueuePath queue;
    private ListPath result;
    private int entryCount;
    private Path root;
    private String target;
    
    @Override public void run() {
        // Initialization
        queue = new QueuePath();
        result = new ListPath();
        entryCount = 0;
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
                        synchronized (this) {
                            entryCount++;
                        }
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
    
    @Override public FileSearchSynchronized set(Path root, String target) {
        this.root = root;
        this.target = target;
        return this;
    }
    
    @Override public List<Path> getResult() {
        List<Path> list = new ArrayList<>(result.size);
        for (int i = 0; i < result.size; i++)
            list.add(result.paths[i]);
        return list;
    }
    
    @Override public int getEntryCount() {
        return entryCount;
    }
    
    @Override public Path getRoot() {
        return root;
    }
    
    @Override public String getTarget() {
        return target;
    }
    
    private static class QueuePath {
        final Path[] paths;
        volatile int size;
        volatile int head, tail;
        QueuePath() {
            paths = new Path[QUEUE_CAP];
            size = 0;
            head = tail = 0;
        }
        synchronized void put(Path path) throws InterruptedException {
            while (size == QUEUE_CAP)
                wait();
            if (size == 0)
                paths[head = tail = 0] = path;
            else if (tail == QUEUE_CAP - 1)
                paths[tail = 0] = path;
            else
                paths[++tail] = path;
            size++;
            notifyAll();
        }
        synchronized Path take() throws InterruptedException {
            while (size == 0)
                wait();
            Path removed = paths[head];
            paths[head] = null;
            if (size == 1)
                head = tail = 0;
            else if (head == QUEUE_CAP - 1)
                head = 0;
            else
                head++;
            size--;
            notifyAll();
            return removed;
        }
    }
    
    private static class ListPath {
        static final int INIT_CAP = 128;
        volatile int capacity;
        Path[] paths;
        volatile int size;
        ListPath() {
            capacity = INIT_CAP;
            paths = new Path[INIT_CAP];
            size = 0;
        }
        synchronized void add(Path path) {
            extensionOnDemand();
            paths[size++] = path;
        }
        private void extensionOnDemand() {
            if (size != capacity)
                return;
            capacity <<= 1;
            Path[] oldPaths = paths;
            paths = new Path[capacity];
            System.arraycopy(oldPaths, 0, paths, 0, size);
        }
    }
    
}
