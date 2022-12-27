package com.github.druyaned.learn_java.util.walk;

import com.github.druyaned.learn_java.util.Strings;

/**
 * Matches the lines by {@code patterns} in a thread and provides {@code matchedLines}.
 * 
 * @author druyaned
 */
class MatchTask implements Runnable {
    public static final int DELAY = 1; // nanos
    
    private final int threadNumber;
    private final String[] lines;
    private Strings matchedLines;
    private final Strings patterns;
    private final int PATTERN_N;
    private volatile boolean paused;
    private volatile boolean stopped;
    private volatile boolean matched;

    public MatchTask(int threadNumber, final String[] lines, final Strings patterns) {
        this.threadNumber = threadNumber;
        this.lines = lines;
        this.patterns = patterns;
        PATTERN_N = patterns.size();
        paused = true;
        stopped = false;
        matched = false;
    }
    
//-Getters-and-Setters------------------------------------------------------------------------------
    
    Strings getMatchedLines() { return matchedLines; }
    boolean isPaused() { return paused; }
    public boolean isMatched() { return matched; }
    void unsetPaused() { this.paused = false; }
    void setStopped() { this.stopped = true; }
    
//-Methods------------------------------------------------------------------------------------------
    
    @Override
    public void run() {
        try {
            while (!stopped) {
                while (paused && !stopped) {
                    Thread.sleep(0, DELAY);
                }
                if (stopped) {
                    break;
                }
                matched = true;
                for (int i = threadNumber; i < threadNumber + PATTERN_N; ++i) {
                    if (!lines[i].matches(patterns.get(i - threadNumber))) {
                        matched = false;
                        break;
                    }
                }
                matchedLines = new Strings(lines, threadNumber, PATTERN_N);
                paused = true;
            }
        } catch (InterruptedException exc) {
            throw new RuntimeException(exc);
        }
    }
}
