package druyaned.corejava.vol1.ch14.src.filesearch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SearchTask implements Runnable {
    
    final List<Path> paths = new ArrayList<>();
    private volatile boolean running = true;
    private final FileSearch search;
    private final String fileName;
    
    public SearchTask(FileSearch search, String fileName) {
        this.search = search;
        this.fileName = fileName;
    }
    
    synchronized void add(Path path) {
        paths.add(path);
    }
    
    synchronized void terminate() {
        running = false;
        notify();
    }
    
    synchronized void startRound() {
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
        } catch (InterruptedException | IOException exc) {
            throw new RuntimeException(exc);
        }
    }
    
    private void matchActions() throws IOException {
        for (Path path : paths) {
            search.checkUpdate();
            if (fileName.equals(path.getFileName().toString())) {
                search.addToResult(path);
            }
            if (Files.isDirectory(path)) {
                search.addToBuffer(path.toFile().listFiles());
            }
        }
    }
    
}
