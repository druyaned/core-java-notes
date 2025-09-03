package druyaned.corejava.vol1.ch14concurrency.t05filesearch;

import static druyaned.corejava.vol1.ch14concurrency.T05FileSearches.THREAD_COUNT;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Searches the file in the file system.
 * <P><i>Execution</i><ol>
 *  <li>Transfer of {@code root} and {@code target} to the searcher.</li>
 *  <li>Creation of thread tasks.</li>
 *  <li>Each thread task has a list of paths that are filled by the searcher
 *      from the {@code buffer}.</li>
 *  <li>After all thread tasks process their paths they are starting to wait.</li>
 *  <li>During the process each task fills the buffer by subpaths.</li>
 * </ol>
 * @author druyaned
 */
public class FileSearch implements FileSearchable {
    
    private final List<Path> result = new ArrayList<>();
    private final List<Path> buffer = new ArrayList<>();
    private final SearchTask[] tasks;
    private volatile int activeCount;
    private volatile int entryCount;
    private volatile int maxBufferSize;
    private Path root;
    private String target;
    
    public FileSearch() {
        tasks = new SearchTask[THREAD_COUNT];
    }
    
    @Override public synchronized void run() {
        try {
            preparation();
            while (!buffer.isEmpty()) {
                spreadBuffer();
                activateTasks();
                wait();
            }
            for (SearchTask task : tasks)
                task.terminate();
        } catch (InterruptedException exc) {
            throw new IllegalStateException(exc);
        }
    }
    
    private void preparation() {
        result.clear();
        buffer.clear();
        activeCount = 0;
        entryCount = 0;
        maxBufferSize = 1;
        for (int i = 0; i < tasks.length; i++)
            new Thread(tasks[i] = new SearchTask(this, target)).start();
        buffer.add(root);
    }
    
    private void spreadBuffer() {
        if (buffer.size() > maxBufferSize)
            maxBufferSize = buffer.size();
        int i = tasks.length - 1;
        for (Path path : buffer)
            tasks[i = (i + 1) % tasks.length].add(path);
        buffer.clear();
    }
    
    private void activateTasks() {
        activeCount = tasks.length;
        for (SearchTask task : tasks)
            task.startRound();
    }
    
    private synchronized void addToResult(Path path) {
        result.add(path);
    }
    
    private synchronized void addToBuffer(File[] files) {
        if (files == null)
            return;
        for (File file : files)
            buffer.add(file.toPath());
    }
    
    private synchronized void roundEndUpdate() {
        activeCount--;
        if (activeCount == 0)
            notify();
    }
    
    private synchronized void entryCountUpdate() {
        entryCount++;
    }
    
    @Override public FileSearch set(Path root, String target) {
        this.root = root;
        this.target = target;
        return this;
    }
    
    @Override public List<Path> getResult() {
        return new ArrayList<>(result);
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
    
    public int getMaxBufferSize() {
        return maxBufferSize;
    }
    
    private static class SearchTask implements Runnable {
        private final List<Path> paths = new ArrayList<>();
        private volatile boolean running = true;
        private final FileSearch search;
        private final String target;
        private SearchTask(FileSearch search, String target) {
            this.search = search;
            this.target = target;
        }
        private synchronized void add(Path path) {
            paths.add(path);
        }
        private synchronized void terminate() {
            running = false;
            notify();
        }
        private synchronized void startRound() {
            notify();
        }
        @Override public synchronized void run() {
            try {
                wait();
                while (running) {
                    matchActions();
                    paths.clear();
                    search.roundEndUpdate();
                    wait();
                }
            } catch (InterruptedException exc) {
                throw new IllegalStateException(exc);
            } catch (IOException exc) {
                throw new UncheckedIOException(exc);
            }
        }
        private void matchActions() throws IOException {
            for (Path path : paths) {
                search.entryCountUpdate();
                if (target.equals(path.getFileName().toString()))
                    search.addToResult(path);
                if (Files.isDirectory(path))
                    search.addToBuffer(path.toFile().listFiles());
            }
        }
    }
    
}
