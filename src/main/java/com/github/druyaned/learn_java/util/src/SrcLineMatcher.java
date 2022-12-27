package com.github.druyaned.learn_java.util.src;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.util.Strings;
import com.github.druyaned.learn_java.util.walk.MatchReport;
import com.github.druyaned.learn_java.util.walk.Walker;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * Matches lines by the {@code patterns} of the {@link Src#getSrcPaths() source files}.
 * 
 * @author druyaned
 */
public class SrcLineMatcher {
    
    /**
     * Matches lines by the {@code patterns} of the {@link Src#getSrcPaths() source files}
     * and returns all {@code reports of matching}.
     * <p><u><b>WARNING</b></u>: each {@code pattern} from the {@code paterns}
     * must have a "<i>new line</i>" character or other <i>vertical whitespace</i> at the end;
     * also each {@code matched line} of the {@code matcher}
     * has a "<i>new line</i>" character or other <i>vertical whitespace</i> at the end.
     * 
     * @param volNumber a volume number which must be 1 or 2.
     * @param patterns line patterns to match a text-blocks.
     * @param matcher a consumer of the {@code matched lines},
     *        <u>can be a null</u> then nothing happens with {@code matched lines}.
     * @return all {@code reports of matching}.
     * @throws IOException if an I/O error occurs.
     * @see Walker#match
     */
    public static List<MatchReport> run(int volNumber,
                                        Strings patterns,
                                        Consumer<Strings> matcher) throws IOException {
        
        System.out.printf("Running the %s for the %s:\n",
                          bold("SrcLineMatcher"), bold("patterns"));
        for (int i = 0; i < patterns.size(); ++i) {
            System.out.printf("  %d: \"%s\"\n", i + 1, greenBold(patterns.get(i)));
        }
        Walker walker = new Walker();
        final List<Path> srcPaths = Src.getSrcPaths(volNumber);
        final List<Path> relativePaths = Src.getRelativeSrcPaths(volNumber);
        final int N = srcPaths.size();
        List<MatchReport> reports = new ArrayList<>(N);
        boolean noFound = true;
        for (int i = 0; i < N; ++i) {
            MatchReport report = walker.match(srcPaths.get(i), patterns, matcher);
            reports.add(report);
            if (noFound && report.getMatchCount() > 0) {
                noFound = false;
            }
        }
        if (noFound) {
            System.out.println(bold("No matches found"));
        } else {
            System.out.println(bold("Matches were found in") + ":");
            for (int i = 0; i < N; ++i) {
                if (reports.get(i).getMatchCount() > 0) {
                    System.out.printf("%s: matchCount=%d\n",
                                      blueBold(relativePaths.get(i).toString()),
                                      reports.get(i).getMatchCount());
                }
            }
        }
        return Collections.unmodifiableList(reports);
    }
}
