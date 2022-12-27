
package com.github.druyaned.learn_java.util.src;

import static com.github.druyaned.ConsoleColors.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Provides useful methods for a source-files editing.
 * 
 * @author druyaned
 * @see EditStarter
 */
public class Src {
    
    /**
     * {@link com.github.druyaned.ConsoleColors#purpleBold(java.lang.String) Purple}
     * slashes are used to begin the output of the source file content.
     * 
     * @see #printLine(java.lang.String)
     */
    public static final String OPEN_SLASHES;
    
    /**
     * {@link com.github.druyaned.ConsoleColors#purpleBold(java.lang.String) Purple}
     * slashes are used to end the output of the source file content.
     * 
     * @see #printLine(java.lang.String)
     */
    public static final String CLOSE_SLASHES;

    // paths
    private static final Path srcDirPath;
    
    static {
        
        // slashes
        OPEN_SLASHES = purpleBold(new String(new char[40 - 3]).replace('\0', '/') +
                                  "BEGIN" +
                                  new String(new char[40 - 2]).replace('\0', '\\'));
        CLOSE_SLASHES = purpleBold(new String(new char[40 - 2]).replace('\0', '\\') +
                                   "END" +
                                   new String(new char[40 - 1]).replace('\0', '/'));
        
        // paths
        srcDirPath = Paths.get(System.getProperty("user.dir"), "src", "main", "java");
    }

//-Getters------------------------------------------------------------------------------------------
    
    /**
     * Reads the file and returns an <u>unmodifiable</u> list of its lines.
     * 
     * @param srcPath an absolute path to the source file ({@code <FILE_NAME>.java}).
     * @return an {@link Collections#unmodifiableList(java.util.List) unmodifiable list} of lines.
     * @throws IOException if an I/O error occurs.
     */
    public static List<String> getLines(Path srcPath) throws IOException {
        try (Stream<String> streamOfLines = Files.lines(srcPath)) {
            return Collections.unmodifiableList(Arrays.asList(
                    streamOfLines.toArray(String[]::new)));
        }
    }
    
    /**
     * Returns the path of the directory with the source files.
     * 
     * @return the path of the directory with the source files.
     */
    public static Path getSrcDirPath() { return srcDirPath; }
    
    /**
     * Returns unmodifiable, sorted lexicographically by a file name
     * list of source-files ({@code ending=".java"}) from the source dir of volume 1 or 2.
     * 
     * @param volNumber a volume number which must be 1 or 2.
     * @return {@link Collections#unmodifiableList unmodifiable},
     *         {@link Path#compareTo(java.nio.file.Path) sorted lexicographically}
     *         by a {@link Path#getFileName() file name}
     *         list of source-files ({@code ending=".java"})
     *         from the {@link #getSrcDirPath() source dir}.
     * @throws IOException if an I/O error occurs.
     * @see SrcVisitor#getFileList()
     */
    public static List<Path> getSrcPaths(int volNumber) throws IOException {
        SrcVisitor visitor = new SrcVisitor(volNumber);
        Files.walkFileTree(srcDirPath, visitor);
        return Collections.unmodifiableList(visitor.getFileList());
    }
    
    /**
     * Returns unmodifiable, sorted lexicographically by a file name list of source-files
     * ({@code ending=".java"}) as relative paths from the source dir of volume 1 or 2.
     * 
     * @param volNumber a volume number which must be 1 or 2.
     * @return {@link Collections#unmodifiableList unmodifiable},
     *         {@link Path#compareTo(java.nio.file.Path) sorted lexicographically}
     *         by a {@link Path#getFileName() file name}
     *         list of source-files ({@code ending=".java"}) as relative paths
     *         from the {@link #getSrcDirPath() source dir}.
     * @throws IOException if an I/O error occurs.
     * @see SrcVisitor#getFileList()
     */
    public static List<Path> getRelativeSrcPaths(int volNumber) throws IOException {
        SrcVisitor visitor = new SrcVisitor(volNumber);
        Files.walkFileTree(srcDirPath, visitor);
        List<Path> srcPaths = visitor.getFileList();
        List<Path> relativeSrcPaths = new ArrayList<>(srcPaths.size());
        srcPaths.forEach((p) -> relativeSrcPaths.add(srcDirPath.relativize(p)));
        return Collections.unmodifiableList(relativeSrcPaths);
    }
    
    /**
     * Outputs the line to the {@link System#out standard ouput}
     * with 2 {@link com.github.druyaned.ConsoleColors#purpleBold(java.lang.String) purple}
     * open and close characters at the beginning and at the end of the line respectively.
     * <p><i>NOTE</i>: must be used with {@link #OPEN_SLASHES} and {@link #CLOSE_SLASHES}.
     * 
     * @param line the line to be printed.
     */
    public static void printLine(String line) {
        System.out.println(purpleBold("[>") + line + purpleBold("<]"));
    }
}
