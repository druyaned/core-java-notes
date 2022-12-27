package com.github.druyaned.learn_java.util.walk;

import com.github.druyaned.learn_java.util.Strings;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.function.Function;

/**
 * Provides report for the {@link Walker#replace replacement}.
 * <p> Additional to {@link Report report's} states:
 * <ol>
 *   <li>copyPath;</li>
 *   <li>relativePath;</li>
 *   <li>replacer.</li>
 * </ol>
 * 
 * @author druyaned
 * @see Walker#replace
 */
public class ReplaceReport extends Report {
    private final Path copyPath;
    private final Path relativePath;
    private final Function<Strings, Strings> replacer;
    
    /**
     * Constructs a report for the {@link Walker#replace replacement}.
     * 
     * @param filePath the file to make a replacement.
     * @param patterns line patterns to match a text-blocks.
     * @param matchCount the amount of matches with the {@code patterns}.
     * @param makeTime the time of making the report.
     * @param relativePath the relative path of the {@code filePath}
     *        to make safe replacement with a copy
     *        (<u>e.g.</u> a class {@code pack1.pack2.TheClass1}
     *        should have a relative path {@code pack1/pack2/TheClass1.java}).
     * @param copyPath the path to save the file before the replacement.
     * @param replacer the function to replace the
     *        {@code matched lines} that are an argument with the
     *        {@code lines to replace} that are a returning;
     *        <u>can be a null</u> then the {@code matched lines}
     *        will be removed.
     * @see Walker#replace
     */
    ReplaceReport(Path filePath,
                  Strings patterns,
                  long matchCount,
                  LocalDateTime makeTime,
                  Path relativePath,
                  Path copyPath,
                  Function<Strings, Strings> replacer) {
        
        super(filePath, patterns, matchCount, makeTime);
        this.relativePath = relativePath;
        this.copyPath = copyPath;
        this.replacer = replacer;
    }
    
//-Getters------------------------------------------------------------------------------------------
    
    /**
     * The path to save the file before the replacement.
     * 
     * @return the path to save the file before the replacement.
     */
    public Path getCopyPath() { return copyPath; }
    
    /**
     * The relative path of the {@code filePath} to make safe replacement with a copy
     * (<u>e.g.</u> a class {@code pack1.pack2.TheClass1}
     * should have a relative path {@code pack1/pack2/TheClass1.java}).
     * 
     * @return the relative path of the {@code filePath}
     *         to make safe replacement with a copy
     *         (<u>e.g.</u> a class {@code pack1.pack2.TheClass1}
     *         should have a relative path {@code pack1/pack2/TheClass1.java}).
     */
    public Path getRelativePath() { return relativePath; }
    
    /**
     * The function to replace the {@code matched lines} that are an argument
     * with the {@code lines to replace} that are a returning;
     * <u>can be a null</u> then the {@code matched lines} will be removed.
     * 
     * @return the function to replace the {@code matched lines} that are an argument
     *         with the {@code lines to replace} that are a returning;
     *         <u>can be a null</u> then the {@code matched lines} will be removed.
     */
    public Function<Strings, Strings> getReplacer() { return replacer; }
    
//-Methods------------------------------------------------------------------------------------------
    
    @Override
    public String toString() {
        return "[filePath=" + getFilePath() +
               ", patterns=" + getPatterns()+
               ", matchCount=" + getMatchCount()+
               ", makeTime=" + getMakeTime() +
               ", copyPath=" + copyPath +
               ", replacer=" + replacer +
               "]";
    }
}
