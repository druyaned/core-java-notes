package com.github.druyaned.learn_java.vol2.chapter02;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * This class provides a showing of all directory entries.
 * 
 * @author druyaned
 */
public class FileVisitorToShow extends SimpleFileVisitor<Path> {
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        System.out.println(" ".repeat(2) + file); // showing
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
            throws IOException {

        System.out.println(" ".repeat(2) + dir); // showing
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException ex) throws IOException {

        if (ex != null)
            throw ex;
        return FileVisitResult.SKIP_SIBLINGS;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException ex) throws IOException {

        if (ex != null)
            throw ex;
        return FileVisitResult.CONTINUE;
    }

}
