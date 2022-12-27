package com.github.druyaned.learn_java.vol2.chapter02;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.vol2.Volume2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Part 6 of the chapter 2 to practice in Files, Path and Paths API.
 * 
 * @author druyaned
 */
public class P06FileManipulation {
    
    public static void run() {
        System.out.println("\n" + bold("Part 06 FileManipulation"));
        
        Path dataDirPath = Volume2.getDataDirPath();
        Path dataFilePath = EmployeeData.getFilePath();
        System.out.println("filePath=" + dataFilePath);
        
        // resolveSibling: parent + file
        Path file1Path = dataFilePath.resolveSibling("file-1");
        System.out.printf("%30s: %s\n", blueBold("resolveSibling"), file1Path);
        
        // getParent, resolve: dirPath + file
        Path parentPath = dataFilePath.getParent();
        Path file2Path = parentPath.resolve("file-2");
        System.out.printf("%30s: %s\n", blueBold("getParent.resolve"), file2Path);
        
        // relativize
        Path someDirPath = parentPath.resolveSibling("some-dir");
        Path relativePath = someDirPath.relativize(dataFilePath);
        System.out.printf("%30s: %s\n", greenBold("someDirPath"), someDirPath);
        System.out.printf("%30s: %s\n", blueBold("relativize"), relativePath);
        
        // normalize
        Path normalPath = parentPath.resolve(relativePath).normalize();
        System.out.printf("%30s: %s\n", blueBold("normalize"), normalPath);
        
        // toAbsolutePath
        Path absolutePath = relativePath.toAbsolutePath();
        System.out.printf("%30s: %s\n", blueBold("toAbsolutePath"), absolutePath);
        System.out.printf("%30s: %s\n", blueBold("normalize"), absolutePath.normalize());
        
        try {
            
            // creation of the file-1, chapter02/test-dir and temp-file
            System.out.println(blueBold("The file-1 was successfully created"));
            Path tempFilePath = dataDirPath.resolve("temp-file");
            Path testDirPath = parentPath.resolve("test-dir");
            if (!Files.exists(file1Path))
                Files.createFile(file1Path);
            if (!Files.exists(testDirPath))
                Files.createDirectory(testDirPath);
            if (!Files.exists(tempFilePath))
                Files.createFile(tempFilePath);
            System.out.println(blueBold("The test-dir was successfully created"));
            System.out.println(blueBold("The temp-file was successfully created"));
            
            // writing the file-1
            String s = """
                       That's the 1st line and it's great!
                       But the 2nd line can be even better :D
                       """;
            Files.write(file1Path, s.getBytes());
            System.out.println(blueBold("The String was successfully written to the file-1"));
            
            // readAllLines
            List<String> lines = Files.readAllLines(file1Path, StandardCharsets.UTF_8);
            System.out.println(blueBold("Files.readAllLines") + ":");
            for (String line : lines)
                System.out.println("  " + line);
            
            // Files.newBufferedReader
            try (BufferedReader reader = Files.newBufferedReader(file1Path)) {
                System.out.println(blueBold("Files.newBufferedReader") + ":");
                while (reader.ready())
                    System.out.println("  " + reader.readLine());
            }
            
            // copy, move, size
            Path copiedFilePath = parentPath.resolve("file-1-copy");
            Path movedFilePath = testDirPath.resolve("file-1-moved");
            Files.copy(file1Path, copiedFilePath, StandardCopyOption.COPY_ATTRIBUTES,
                                          StandardCopyOption.REPLACE_EXISTING);
            System.out.println(blueBold("The file-1-copy was successfully created"));
            Files.move(file1Path, movedFilePath, StandardCopyOption.ATOMIC_MOVE,
                                          StandardCopyOption.REPLACE_EXISTING);
            System.out.println(blueBold("The file-1 was successfully moved"));
            System.out.println(blueBold("size") + ": Files.size(employees.dat)=" +
                               Files.size(dataFilePath) + " (bytes)");
            
            // BasicFileAttributes
            BasicFileAttributes copyAttributes =
                    Files.readAttributes(copiedFilePath, BasicFileAttributes.class);
            System.out.println(blueBold("BasicFileAttributes") +
                               ":\n  creationTime=" + copyAttributes.creationTime() +
                               "\n  isRegularFile=" + copyAttributes.isRegularFile() +
                               "\n  lastAccessTime=" + copyAttributes.lastAccessTime() +
                               "\n  lastModifiedTime=" + copyAttributes.lastModifiedTime() +
                               "\n  fileKey=" + copyAttributes.fileKey());
            
            // list, walk, find, newDirectoryStream, walkFileTree
            try (Stream<Path> dirList = Files.list(dataDirPath);
                 Stream<Path> dirWalk = Files.walk(dataDirPath /* , depth */ );
                 Stream<Path> dirFind = Files.find(dataDirPath, 4, (path, attributes) -> true);
                 DirectoryStream<Path> dirStream = Files.newDirectoryStream(dataDirPath
                                                                            /* , "*0*" */)) {
                
                // list
                System.out.println(blueBold("dirList") + ":");
                dirList.forEach((path) -> System.out.println("  " + path));
                
                // walk
                System.out.println(blueBold("dirWalk") + ":");
                dirWalk.forEach((path) -> System.out.println("  " + path));
                
                // find
                System.out.println(blueBold("dirFind.sorted") + ":");
                dirFind.sorted((path1, path2) ->
                        path1.getFileName().compareTo(path2.getFileName()))
                        .forEachOrdered((path) -> System.out.println("  " + path));
                
                // newDirectoryStream
                System.out.println(blueBold("dirStream") + ":");
                for (Path path : dirStream)
                    System.out.println("  " + path);
                
                // walkFileTree
                System.out.println(blueBold("walkFileTree") + ":");
                Files.walkFileTree(dataDirPath, new FileVisitorToShow());
            }
            
            // zip: deleting the file
            String testZipName = "test-dir.zip";
            Path testZipPath = parentPath.resolve(testZipName);
            if (Files.exists(testZipPath))
                Files.delete(testZipPath);
            
            // zip: walking through the file tree
            Map<String, String> env = new HashMap<>();
            env.put("create", "true");
            try (FileSystem fs = FileSystems.newFileSystem(testZipPath, env);
                 Stream<Path> zipWalk = Files.walk(fs.getPath("/"))) {
                
                System.out.println(blueBold("fs.getPath(\"/\")") + ": " +
                                   fs.getPath("/").toUri().toString());
                
                // zip: writing the file
                String testDirName = testDirPath.getFileName().toString() + "/";
                String movedFileName = testDirName + "/" + movedFilePath.getFileName().toString();
                Files.copy(testDirPath, fs.getPath(testDirName));
                Files.copy(movedFilePath, fs.getPath(movedFileName));
                Files.copy(dataFilePath, fs.getPath(dataFilePath.getFileName().toString()));
                System.out.println(blueBold(testZipName + " was successfully written"));
                
                // zip: getRootDirectories
                System.out.println(blueBold("getRootDirectories") + ":");
                fs.getRootDirectories().forEach((root) -> {
                    try {
                        Files.walk(root).forEach((path) -> System.out.println("  " + path));
                    } catch (IOException ex) {
                        throw new UncheckedIOException(ex);
                    }
                });
                
                // zip: walk; WRONG USAGE
                System.out.println(blueBold("walk (WRONG USAGE)") + ":");
                zipWalk.forEach((path) -> System.out.println("  " + path));
                
                // zip: walkFileTree
                System.out.println(blueBold("walkFileTree") + ":");
                Files.walkFileTree(fs.getPath("/"), new FileVisitorToShow());
            }
            
            // deleting the temp-file
            Files.delete(tempFilePath);
            System.out.println(blueBold("temp-file was successfully deleted"));
            
        } catch (IOException ex) {
            Logger.getLogger(P06FileManipulation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
