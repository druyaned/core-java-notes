package com.github.druyaned.learn_java.util.src;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.util.Strings;
import com.github.druyaned.learn_java.util.walk.ReplaceReport;
import com.github.druyaned.learn_java.util.walk.Walker;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Replaces lines that match the {@code patterns}
 * from {@link Src#getSrcPaths() source files} by the given {@code replacer}.
 * 
 * @author druyaned
 */
public class SrcLineReplacer {
    
    /**
     * Replaces lines that match the {@code patterns}
     * from {@link Src#getSrcPaths() source files} by the given {@code replacer}
     * and returns all {@code reports of matching}.
     * <p><u><b>WARNING</b></u>: each {@code pattern} from the {@code paterns}
     * must have a "<i>new line</i>" character or other <i>vertical whitespace</i> at the end;
     * also each {@code matched line} of the {@code replacer}
     * has a "<i>new line</i>" character or other <i>vertical whitespace</i> at the end.
     * 
     * @param volNumber a volume number which must be 1 or 2.
     * @param patterns line patterns to match a text-blocks.
     * @param replacer the function to replace the {@code matched lines}
     *        that are an <i>argument</i> with the
     *        {@code lines to replace} that are a <i>returning</i>;
     *        <u>can be a null</u> then the {@code matched lines} will be removed.
     * @return all {@code reports of matching}.
     * @throws IOException if an I/O error occurs.
     * @see Walker#replace
     */
    public static List<ReplaceReport> run(int volNumber,
                                          Strings patterns,
                                          Function<Strings, Strings> replacer) throws IOException {
        
        System.out.printf("Running the %s for the %s:\n",
                          bold("SrcLineReplacer"), bold("patterns"));
        for (int i = 0; i < patterns.size(); ++i) {
            System.out.printf("  %d: \"%s\"\n", i + 1, greenBold(patterns.get(i)));
        }
        Walker walker = new Walker();
        final List<Path> srcPaths = Src.getSrcPaths(volNumber);
        final List<Path> relativePaths = Src.getRelativeSrcPaths(volNumber);
        final int N = srcPaths.size();
        boolean noFound = true;
        List<ReplaceReport> reports = new ArrayList<>(N);
        for (int i = 0; i < N; ++i) {
            Path src = srcPaths.get(i);
            Path relative = relativePaths.get(i);
            ReplaceReport report = walker.replace(src, relative, patterns, replacer);
            reports.add(report);
            if (noFound && report.getMatchCount() > 0) {
                noFound = false;
            }
        }
        if (noFound) {
            if (replacer == null) {
                System.out.println(bold("No matches so nothing to remove"));
            } else {
                System.out.println(bold("No matches so nothing to replace"));
            }
        } else {
            if (replacer == null) {
                System.out.println(bold("Lines are removed in") + ":");
            } else {
                System.out.println(bold("Lines are replaced in") + ":");
            }
            for (ReplaceReport report : reports) {
                if (report.getMatchCount() > 0) {
                    System.out.printf("%s:\n  copy=%s\n",
                                      blueBold(report.getRelativePath().toString()),
                                      purpleBold(report.getCopyPath().toString()));
                }
            }
        }
        return reports;
    }
}
