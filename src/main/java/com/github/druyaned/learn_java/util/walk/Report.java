package com.github.druyaned.learn_java.util.walk;

import com.github.druyaned.learn_java.util.Strings;
import java.nio.file.Path;
import java.time.LocalDateTime;

/**
 * Provides a report for a {@link Walker walker}.
 * <p>Provided states:
 * <ol>
 *   <li>filePath;</li>
 *   <li>patterns;</li>
 *   <li>matchCount;</li>
 *   <li>makeTime.</li>
 * </ol>
 * 
 * @author druyaned
 */
public class Report {
    private final Path filePath;
    private final Strings patterns;
    private final long matchCount;
    private final LocalDateTime makeTime;
    
    /**
     * Constructs a report for a {@link Walker walker}.
     * 
     * @param filePath the file to walk through.
     * @param patterns line patterns to match a text-blocks.
     * @param matchCount the amount of matches with the {@code patterns}.
     * @param makeTime the time of making the report.
     */
    Report(Path filePath, Strings patterns, long matchCount, LocalDateTime makeTime) {
        this.filePath = filePath;
        this.patterns = patterns;
        this.matchCount = matchCount;
        this.makeTime = makeTime;
    }
    
//-Getters------------------------------------------------------------------------------------------
    
    /**
     * The file to walk through.
     * 
     * @return the file to walk through.
     */
    public Path getFilePath() { return filePath; }
    
    /**
     * Patterns line patterns to match a text-blocks.
     * 
     * @return patterns line patterns to match a text-blocks.
     */
    public Strings getPatterns() { return patterns; }
    
    /**
     * The amount of matches with the {@code patterns}.
     * 
     * @return the amount of matches with the {@code patterns}.
     */
    public long getMatchCount() { return matchCount; }
    
    /**
     * The time of making the report.
     * 
     * @return the time of making the report.
     */
    public LocalDateTime getMakeTime() { return makeTime; }
    
//-Methods------------------------------------------------------------------------------------------
    
    @Override
    public String toString() {
        return "[filePath=" + filePath +
               ", patterns=" + patterns +
               ", matchCount=" + matchCount +
               ", makeTime=" + makeTime +
               "]";
    }
}
