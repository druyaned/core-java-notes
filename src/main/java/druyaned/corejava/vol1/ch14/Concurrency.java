package druyaned.corejava.vol1.ch14;

import druyaned.corejava.Chapter;
import druyaned.corejava.vol1.ch14.p01.TestBounce;
import druyaned.corejava.vol1.ch14.p02.TestInterrupt;
import druyaned.corejava.vol1.ch14.p03.TestBanks;
import druyaned.corejava.vol1.ch14.p04.TestSynchronization;
import druyaned.corejava.vol1.ch14.p05.TestFileSearches;
import druyaned.corejava.vol1.ch14.p06.TestExecutors;
import druyaned.corejava.vol1.ch14.p07.TestFutureTask;
import druyaned.corejava.vol1.ch14.p08.TestCollections;
import druyaned.corejava.vol1.ch14.p09.TestSynchronizer;
import druyaned.corejava.vol1.ch14.p10.TestSwing;
import druyaned.corejava.vol1.ch14.p11.TestLoop;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Practical implementation of the Chapter#14: Concurrency.
 * @author druyaned
 */
public class Concurrency extends Chapter {
    
    /**
     * Creates the Chapter#14: Concurrency.
     * @param volDataDir the path to the volume's data-directory
     */
    public Concurrency(Path volDataDir) {
        super(volDataDir, 14);
    }
    
    @Override public String getTitle() {
        return "Concurrency";
    }
    
    @Override public void run() {
        List<Runnable> parts = new ArrayList<>();
        parts.add(new TestBounce());
        parts.add(new TestInterrupt());
        parts.add(new TestBanks());
        parts.add(new TestSynchronization());
        parts.add(new TestFileSearches());
        parts.add(new TestExecutors());
        parts.add(new TestFutureTask());
        parts.add(new TestCollections());
        parts.add(new TestSynchronizer());
        parts.add(new TestSwing());
        parts.add(new TestLoop());
        choosePartAndRun(parts);
    }
    
}
