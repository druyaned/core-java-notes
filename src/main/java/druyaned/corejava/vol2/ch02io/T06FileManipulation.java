package druyaned.corejava.vol2.ch02io;

import static druyaned.ConsoleColors.blueBold;
import static druyaned.ConsoleColors.greenBold;
import static druyaned.ConsoleColors.purpleBold;
import static druyaned.ConsoleColors.redBold;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.util.FileUtil;
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
import java.util.stream.Stream;

public class T06FileManipulation extends Topic {
    
    private final EmployeeData data;
    
    public T06FileManipulation(Chapter chapter, EmployeeData data) {
        super(chapter, 6);
        this.data = data;
    }
    
    @Override public String title() {
        return "Topic 06 FileManipulation";
    }
    
    @Override public void run() {
        // resolveSibling: parent + file
        Path file1Path = dataDir().resolve("idk-what-is-it").resolveSibling("file-1");
        System.out.println("file1Path: " + blueBold(file1Path.toString()));
        FileUtil.createFileOnDemand(file1Path);
        print("resolveSibling", file1Path);
        // getParent, resolve: dirPath + file
        Path parentPath = file1Path.getParent();
        Path file2Path = parentPath.resolve("file-2");
        print("getParent.resolve", file2Path);
        // relativize
        Path someDirPath = parentPath.resolveSibling("some-dir");
        Path relativePath = someDirPath.relativize(file1Path);
        print("someDirPath", someDirPath);
        print("relativize(file1Path)", relativePath);
        // normalize
        Path normalPath = parentPath.resolve(relativePath).normalize();
        print("normalize", normalPath);
        // toAbsolutePath
        Path absolutePath = relativePath.toAbsolutePath();
        print("toAbsolutePath", absolutePath);
        print("normalize", absolutePath.normalize());
        try {
            // file creation
            Path tempDirPath = dataDir().resolve("temp-dir");
            Path tempFilePath = dataDir().resolve("temp-file");
            if (!Files.exists(file1Path)) {
                Files.createFile(file1Path);
                creationInfo(file1Path);
            }
            if (!Files.exists(tempDirPath)) {
                Files.createDirectory(tempDirPath);
                creationInfo(tempDirPath);
            }
            if (!Files.exists(tempFilePath)) {
                Files.createFile(tempFilePath);
                creationInfo(tempFilePath);
            }
            // writing the file-1
            String text =
            """
            That's the 1st line and it's great!
            But the 2nd line can be even better :D
            """;
            Files.write(file1Path, text.getBytes());
            writingInfo(file1Path);
            // readAllLines
            List<String> lines = Files.readAllLines(file1Path, StandardCharsets.UTF_8);
            print("Files.readAllLines", file1Path);
            System.out.println("Read lines:");
            for (int i = 0; i < lines.size(); i++)
                System.out.printf("  %d. %s\n", i + 1, lines.get(i));
            // Files.newBufferedReader
            try (BufferedReader reader = Files.newBufferedReader(file1Path)) {
                print("Files.newBufferedReader", file1Path);
                System.out.println("Reading line by line:");
                while (reader.ready())
                    System.out.println("  " + reader.readLine());
            }
            // copy, move, size
            Path copiedFilePath = parentPath.resolve("file-1-copy");
            Files.copy(
                    file1Path,
                    copiedFilePath,
                    StandardCopyOption.COPY_ATTRIBUTES,
                    StandardCopyOption.REPLACE_EXISTING
            );
            copyInfo(file1Path, copiedFilePath);
            Path movedFilePath = tempDirPath.resolve("file-1-moved");
            print("Files.move", movedFilePath);
            Files.move(
                    copiedFilePath,
                    movedFilePath,
                    StandardCopyOption.ATOMIC_MOVE,
                    StandardCopyOption.REPLACE_EXISTING
            );
            System.out.println(purpleBold("Files.size(") + blueBold(file1Path.toString())
                    + purpleBold(")") + ": " + Files.size(file1Path));
            // BasicFileAttributes
            BasicFileAttributes copyAttributes = Files.readAttributes(
                    movedFilePath,
                    BasicFileAttributes.class
            );
            System.out.println(
                    purpleBold("BasicFileAttributes") + ":" +
                    "\n  creationTime:     " + copyAttributes.creationTime() +
                    "\n  isRegularFile:    " + copyAttributes.isRegularFile() +
                    "\n  lastAccessTime:   " + copyAttributes.lastAccessTime() +
                    "\n  lastModifiedTime: " + copyAttributes.lastModifiedTime() +
                    "\n  fileKey:          " + copyAttributes.fileKey()
            );
            // list, walk, find, newDirectoryStream, walkFileTree
            try (
                    Stream<Path> dirList = Files.list(dataDir());
                    Stream<Path> dirWalk = Files.walk(dataDir());
                    Stream<Path> dirFind = Files.find(dataDir(), 4, (path, attributes) -> true);
                    DirectoryStream<Path> dirStream = Files.newDirectoryStream(dataDir());
            ) {
                // list
                System.out.println(purpleBold("dirList") + ":");
                dirList.forEach((path) -> System.out.println("  " + path));
                // walk
                System.out.println(purpleBold("dirWalk") + ":");
                dirWalk.forEach((path) -> System.out.println("  " + path));
                // find
                System.out.println(purpleBold("dirFind.sorted") + ":");
                dirFind.sorted((path1, path2) ->
                        path1.getFileName().compareTo(path2.getFileName())
                ).forEachOrdered((path) -> System.out.println("  " + path));
                // newDirectoryStream
                System.out.println(purpleBold("dirStream") + ":");
                for (Path path : dirStream) {
                    System.out.println("  " + path);
                }
                // walkFileTree
                System.out.println(purpleBold("walkFileTree") + " (fileVisitor):");
                Files.walkFileTree(dataDir(), new FileVisitorToShow());
            }
            // zip: deleting the file
            String testZipName = "test-dir.zip";
            Path testZipPath = parentPath.resolve(testZipName);
            if (Files.exists(testZipPath)) {
                Files.delete(testZipPath);
            }
            // zip: walking through the file tree
            Map<String, String> env = new HashMap<>();
            env.put("create", "true");
            try (
                    FileSystem fileSystem = FileSystems.newFileSystem(testZipPath, env);
                    Stream<Path> zipWalk = Files.walk(fileSystem.getPath("/"));
            ) {
                print("fileSystem.getPath(\"/\")", fileSystem.getPath("/").toUri().toString());
                // zip: writing the file
                String testDirName = tempDirPath.getFileName().toString() + "/";
                String movedFileName = testDirName + "/"
                        + movedFilePath.getFileName().toString();
                Files.copy(tempDirPath, fileSystem.getPath(testDirName));
                copyInfo(tempDirPath, fileSystem.getPath(testDirName));
                Files.copy(movedFilePath, fileSystem.getPath(movedFileName));
                copyInfo(movedFilePath, fileSystem.getPath(movedFileName));
                Files.copy(file1Path, fileSystem.getPath(file1Path.getFileName().toString()));
                copyInfo(file1Path, fileSystem.getPath(file1Path.getFileName().toString()));
                // zip: getRootDirectories
                System.out.println(purpleBold("getRootDirectories") + ":");
                fileSystem.getRootDirectories().forEach((root) -> {
                    try {
                        Files.walk(root).forEach((path) -> System.out.println("  " + path));
                    } catch (IOException ex) {
                        throw new UncheckedIOException(ex);
                    }
                });
                // zip: walk; WRONG USAGE
                System.out.println(
                        purpleBold("walk") + "(" + redBold("WRONG USAGE") + "):"
                );
                zipWalk.forEach((path) -> System.out.println("  " + path));
                // zip: walkFileTree
                System.out.println(purpleBold("walkFileTree") + ":");
                Files.walkFileTree(fileSystem.getPath("/"), new FileVisitorToShow());
            }
            // deleting the temp-file
            Files.delete(tempFilePath);
            System.out.println(blueBold(tempFilePath.toString()) + " was successfully deleted!");
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
    
    private void print(String command, Path path) {
        System.out.printf("%32s: %s\n", purpleBold(command), blueBold(path.toString()));
    }
    
    private void print(String command, String path) {
        System.out.printf("%32s: %s\n", purpleBold(command), blueBold(path));
    }
    
    private void creationInfo(Path path) {
        System.out.println(blueBold(path.toString()) + " was "
                + greenBold("successfully") + " created!");
    }
    
    private void writingInfo(Path path) {
        System.out.println(blueBold(path.toString()) + " was " + purpleBold("written") + '!');
    }
    
    private void copyInfo(Path resource, Path target) {
        System.out.println(blueBold(resource.toString()) + " was "
                + purpleBold("copied") + " into " + blueBold(target.toString()) + "!");
    }
    
}
