package com.github.druyaned.learn_java.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

/**
 * Provides the time spent for executing the code part. The class is thread-safe.
 * 
 * @author druyaned
 */
public class Stopwatch {
    private volatile long start;
    private volatile long spent;
    
    /** Creates a new stopwatch that is not {@link #start() started} or {@link #stop() stopped}. */
    public Stopwatch() {
        start = -1;
        spent = -1;
    }
    
//-Methods------------------------------------------------------------------------------------------
    
    /**
     * Returns wether the stopwatch has been started.
     * 
     * @return {@code true} if the stopwatch has been started or {@code false} if it hasn't.
     */
    public boolean isStarted() { return start != -1; }
    
    /**
     * Starts counting the stopwatch while unsetting the {@link #getSpent() spent state}.
     * 
     * @return a reference to this object to call other methods.
     * @throws IllegalStateException if the stopwatch hasn't been started.
     */
    public synchronized Stopwatch start() throws IllegalStateException {
        if (start != -1)
            throw new IllegalStateException("stopwatch has already been started");
        
        start = System.currentTimeMillis();
        spent = -1;
        return this;
    }
    
    /**
     * Stops counting the stopwatch while unsetting the {@link #getStart() start state}.
     * 
     * @return a reference to this object to call other methods.
     * @throws IllegalStateException if the stopwatch hasn't been started.
     */
    public synchronized Stopwatch stop() throws IllegalStateException {
        if (start == -1)
            throw new IllegalStateException("stopwatch hasn't been started");
        
        spent = System.currentTimeMillis() - start;
        start = -1;
        return this;
    }
    
//-Getters------------------------------------------------------------------------------------------

    /**
     * Returns the spent time or {@code null} if the stopwatch hasn't been stopped.
     * 
     * @return the start time or {@code null} if the stopwatch hasn't been started.
     */
    public synchronized LocalDateTime getStart() {
        if (start == -1)
            return null;
        else
            return LocalDateTime
                    .ofEpochSecond(start, 0, ZoneOffset.of(ZoneId.systemDefault().getId()));
    }

    /**
     * Returns the spent time or {@code null} if the stopwatch hasn't been stopped.
     * 
     * @return the spent time or {@code null} if the stopwatch hasn't been stopped.
     */
    public synchronized Duration getSpent() {
        if (spent == - 1)
            return null;
        else
            return Duration.of(spent, ChronoUnit.MILLIS);
    }
}
