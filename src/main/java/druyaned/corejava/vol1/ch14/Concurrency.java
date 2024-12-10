package druyaned.corejava.vol1.ch14;

import druyaned.corejava.Chapter;
import druyaned.corejava.vol1.ch14.src.ball.TestBall;
import druyaned.corejava.vol1.ch14.src.balls.TestBalls;
import druyaned.corejava.vol1.ch14.src.banks.TestBank;
import druyaned.corejava.vol1.ch14.src.banks.TestBankFixed;
import druyaned.corejava.vol1.ch14.src.banks.TestBankQueued;
import druyaned.corejava.vol1.ch14.src.banks.TestBankSynchronized;
import druyaned.corejava.vol1.ch14.src.demo.TestDemo;
import druyaned.corejava.vol1.ch14.src.filesearch.TestFileSearch;
import druyaned.corejava.vol1.ch14.src.loop.TestLoop;
import druyaned.corejava.vol1.ch14.src.loop.TestLoopInteractive;
import druyaned.corejava.vol1.ch14.src.search.TestSearch;
import druyaned.corejava.vol1.ch14.src.states.base.TestBaseStates;
import druyaned.corejava.vol1.ch14.src.states.blocked.TestBlockedState;
import druyaned.corejava.vol1.ch14.src.states.waiting.TestWaitingState;
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
        parts.add(new TestBall());
        parts.add(new TestBank());
        parts.add(new TestBankFixed());
        parts.add(new TestBankSynchronized());
        parts.add(new TestBankQueued());
        parts.add(new TestSearch());
        parts.add(new TestBalls());
        parts.add(new TestDemo());
        parts.add(new TestFileSearch());
        parts.add(new TestBaseStates());
        parts.add(new TestBlockedState());
        parts.add(new TestWaitingState());
        parts.add(new TestLoop());
        parts.add(new TestLoopInteractive());
        choosePartAndRun(parts);
    }
    
}
