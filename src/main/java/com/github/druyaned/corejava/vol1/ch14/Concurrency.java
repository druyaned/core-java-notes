package com.github.druyaned.corejava.vol1.ch14;

import com.github.druyaned.corejava.Chapter;
import com.github.druyaned.corejava.vol1.ch14.src.ball.TestBall;
import com.github.druyaned.corejava.vol1.ch14.src.balls.TestBalls;
import com.github.druyaned.corejava.vol1.ch14.src.banks.TestBank;
import com.github.druyaned.corejava.vol1.ch14.src.banks.TestBankFixed;
import com.github.druyaned.corejava.vol1.ch14.src.banks.TestBankQueued;
import com.github.druyaned.corejava.vol1.ch14.src.banks.TestBankSynchronized;
import com.github.druyaned.corejava.vol1.ch14.src.demo.TestDemo;
import com.github.druyaned.corejava.vol1.ch14.src.filesearch.TestFileSearch;
import com.github.druyaned.corejava.vol1.ch14.src.search.TestSearch;
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
        choosePartAndRun(parts);
    }
    
}
