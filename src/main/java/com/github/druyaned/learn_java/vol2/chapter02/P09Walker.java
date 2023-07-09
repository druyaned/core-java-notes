package com.github.druyaned.learn_java.vol2.chapter02;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.util.Strings;
import com.github.druyaned.learn_java.util.src.Src;
import com.github.druyaned.learn_java.util.walk.MatchReport;
import com.github.druyaned.learn_java.util.walk.Walker;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.LongStream;

/**
 * Part 9 of the chapter 2 to practice with {@link Walker}.
 * 
 * @author druyaned
 */
public class P09Walker {
    
    public static void run() {
        System.out.println("\n" + bold("Part 09 Walker"));
        
        try {
            List<Path> fileList = Src.getSrcPaths(2);
            Walker walker = new Walker();
            final String[] regexs = { "\\h*System.out.println\\(\"\\\\n\" \\+ bold.*\\n" };
            final Strings patterns = new Strings(regexs);
            System.out.println("Pattern: \"" + greenBold(patterns.get(0)) + "\"");
            for (Path filePath : fileList) {
                matcher(filePath, walker, patterns);
            }
        } catch (IOException ex) {
            Logger.getLogger(P09Walker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void matcher(Path filePath, Walker walker, Strings patterns)
            throws IOException {
        
        MatchReport report = walker.match(filePath, patterns, null);
        if (report.getMatchCount() != 0) {
            String fileName = filePath.getFileName().toString();
            System.out.println(blueBold(fileName) + "; matchCount=" + report.getMatchCount());
            LongStream indexesOfMatchedLines = report.getIndexesOfMatchedLines();
            System.out.print("  Indexes Of Matched Lines:");
            indexesOfMatchedLines.forEachOrdered((ind) -> System.out.print(" " + ind));
            System.out.println();
        }
    }

}
