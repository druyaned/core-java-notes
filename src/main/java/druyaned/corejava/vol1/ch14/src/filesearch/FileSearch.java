package druyaned.corejava.vol1.ch14.src.filesearch;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Searches the file in the file system.
 * <P><i>Execution</i><ol>
 *  <li>Transfer of {@code home} and {@code fileName} to the searcher.</li>
 *  <li>Creation of thread tasks.</li>
 *  <li>Each thread task has a list of paths that are filled by the searcher
 *      from the {@code buffer}.</li>
 *  <li>After all thread tasks process their paths they are starting to wait.</li>
 *  <li>During the process each task fills the buffer by subpaths.</li>
 * </ol>
 * @author druyaned
 */
public class FileSearch {
    
    public static final int DEFAULT_THREAD_COUNT = 16;
    
    private final List<Path> result = new ArrayList<>();
    private final List<Path> buffer = new ArrayList<>();
    private final SearchTask[] tasks;
    private volatile int activeCount;
    private volatile long checkCount;
    private volatile int maxBufferSize;
    
    public FileSearch(int threadCount) {
        tasks = new SearchTask[threadCount];
    }
    
    public FileSearch() {
        this(DEFAULT_THREAD_COUNT);
    }
    
    public synchronized List<Path> getResult() {
        return result;
    }
    
    public synchronized long getCheckCount() {
        return checkCount;
    }
    
    public synchronized int getMaxBufferSize() {
        return maxBufferSize;
    }
    
    synchronized void addToResult(Path path) {
        result.add(path);
    }
    
    synchronized void addToBuffer(File[] files) {
        if (files == null) {
            return;
        }
        for (File file : files) {
            buffer.add(file.toPath());
        }
    }
    
    synchronized void roundEndUpdate() {
        activeCount--;
        if (activeCount == 0) {
            notify();
        }
    }
    
    synchronized void checkUpdate() {
        checkCount++;
    }
    
    public synchronized void run(Path home, String fileName) {
        try {
            preparation(home, fileName);
            while (!buffer.isEmpty()) {
                spreadBuffer();
                activateTasks();
                wait();
            }
            for (SearchTask task : tasks) {
                task.terminate();
            }
        } catch (InterruptedException exc) {
            throw new RuntimeException(exc);
        }
    }
    
    private void preparation(Path home, String fileName) {
        result.clear();
        buffer.clear();
        activeCount = 0;
        checkCount = 0;
        maxBufferSize = 1;
        for (int i = 0; i < tasks.length; i++) {
            new Thread(tasks[i] = new SearchTask(this, fileName)).start();
        }
        buffer.add(home);
    }
    
    private void spreadBuffer() {
        if (buffer.size() > maxBufferSize) {
            maxBufferSize = buffer.size();
        }
        int i = 0;
        for (Path path : buffer) {
            tasks[i].add(path);
            i = (i + 1) % tasks.length;
        }
        buffer.clear();
    }
    
    private void activateTasks() {
        activeCount = tasks.length;
        for (SearchTask task : tasks) {
            task.startRound();
        }
    }
    
}
