package com.github.druyaned.learn_java.util.src;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * "Source Visitor" provides a source files ({@code ending=".java"}) of all directory entries.
 * <p><i>NOTE</i>: The instances stop be valid after calling {@link #getFileList() getFileList}.
 * <p><i>USAGE</i>:
 * <pre>
 * SrcVisitor srcVisitor = new SrcVisitor();
 * Files.walkFileTree(dirPath, srcVisitor);
 * List&lt;Path&gt; pathsOfSrcFiles = srcVisitor.getFileList();
 * </pre>
 * 
 * @author druyaned
 */
public class SrcVisitor extends SimpleFileVisitor<Path> {
    
    private final ArrayList<Path> filePaths;
    private final String pattern;
    private volatile boolean gotten;
    
    /**
     * Constructs a new "Source Visitor" which is intended for getting
     * a source files ({@code ending=".java"}) of all directory entries in volume 1 or 2.
     * 
     * @param volNumber a volume number which must be 1 or 2.
     */
    public SrcVisitor(int volNumber) {
        filePaths = new ArrayList<>();
        switch (volNumber) {
            case 1 -> pattern = ".+vol1.+\\.java$";
            case 2 -> pattern = ".+vol2.+\\.java$";
            default -> throw new IllegalArgumentException("volNumber must be 1 or 2");
        }
        gotten = false;
    }
    
//-Methods------------------------------------------------------------------------------------------
    
    /**
     * Returns sorted lexicographically by a file name
     * list of source-files ({@code ending=".java"}),
     * which were added while invoking the visitFile method.
     * 
     * @return {@link Path#compareTo(java.nio.file.Path) sorted lexicographically}
     *         by a {@link Path#getFileName() file name}
     *         list of source-files ({@code ending=".java"}), which were added
     *         while invoking the {@link #visitFile visitFile method}.
     * @throws IllegalStateException if the files have already been gotten.
     * @see Src#getSrcPaths()
     */
    public List<Path> getFileList() throws IllegalStateException {
        if (gotten) {
            throw new IllegalStateException("must be called once");
        }
        gotten = true;
        filePaths.sort((p1, p2) -> p1.getFileName().compareTo(p2.getFileName()));
        return filePaths;
    }
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (gotten) {
            throw new IllegalStateException("source files have been already gotten");
        }
        String fileName = file.toString();
        if (fileName.matches(pattern)) {
            filePaths.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        if (exc != null) {
            throw exc;
        }
        return FileVisitResult.SKIP_SIBLINGS;
    }
    
}
