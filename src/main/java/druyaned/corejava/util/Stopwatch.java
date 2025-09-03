package druyaned.corejava.util;

import java.time.Duration;
import java.time.Instant;

/**
 * Counts the execution time (e.g. of the method).
 * The class is thread-safe but mutable.
 * 
 * @author druyaned
 */
public class Stopwatch {
    
    private volatile Instant start;
    private volatile Duration spent;
    
    /**
     * Creates a new stopwatch that is not {@link #start() started}
     * or {@link #stop() stopped}.
     */
    public Stopwatch() {
        start = null;
        spent = null;
    }
    
    /**
     * Returns wether the stopwatch has been started.
     * @return {@code true} if the stopwatch has been started or {@code false} if it has not
     */
    public boolean isStarted() {
        return start != null;
    }
    
    /**
     * Starts counting the stopwatch while unsetting the {@link #getSpent() spent state}.
     * @return a reference to this object to call other methods
     * @throws IllegalStateException if the stopwatch has been started
     */
    public synchronized Stopwatch start() throws IllegalStateException {
        if (start != null)
            throw new IllegalStateException("stopwatch has already been started");
        start = Instant.now();
        spent = null;
        return this;
    }
    
    /**
     * Stops counting the stopwatch while unsetting the {@link #getStart() start state}.
     * @return a reference to this object to call other methods
     * @throws IllegalStateException if the stopwatch has not been started
     */
    public synchronized Stopwatch stop() throws IllegalStateException {
        if (start == null)
            throw new IllegalStateException("stopwatch has not been started");
        spent = Duration.between(start, Instant.now());
        start = null;
        return this;
    }
    
    /**
     * Returns the start time or {@code null} if the stopwatch hasn't been stopped.
     * @return start time or {@code null} if the stopwatch hasn't been started
     */
    public synchronized Instant getStart() {
        return start != null ? start : null;
    }
    
    /**
     * Returns the spent time or {@code null} if the stopwatch hasn't been stopped.
     * @return spent time or {@code null} if the stopwatch hasn't been stopped
     */
    public synchronized Duration getSpent() {
        return spent != null ? spent : null;
    }
    
}
