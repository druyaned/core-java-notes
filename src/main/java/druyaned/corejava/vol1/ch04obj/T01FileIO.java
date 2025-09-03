package druyaned.corejava.vol1.ch04obj;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.util.FileUtil;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class T01FileIO extends Topic {
    
    public T01FileIO(Chapter chapter) {
        super(chapter, 1);
    }
    
    @Override public String title() {
        return "Topic 01 FileIO";
    }
    
    @Override public void run() {
        Path file1Path = dataDir().resolve("text-file1.txt");
        Path file2Path = dataDir().resolve("text-file2.txt");
        FileUtil.createFileOnDemand(file1Path);
        FileUtil.createFileOnDemand(file2Path);
        String text =
        """
        Hey yo ma boy!
        Ow my!
        """;
        try {
            Files.writeString(file1Path, text, StandardOpenOption.WRITE);
        } catch (IOException exc) {
            exc.printStackTrace();
            return;
        }
        try (Scanner fin = new Scanner(file1Path, "UTF-8")) {
            if (!fin.hasNext()) {
                System.out.printf("Oops. There is no lines in %s.\n", file1Path.toString());
                return;
            }
            StringBuilder builder = new StringBuilder();
            System.out.println(file1Path + ":");
            while (fin.hasNextLine()) {
                String line = fin.nextLine();
                builder.append(line).append('\n');
                System.out.println(line);
            }
            try (PrintStream ps = new PrintStream(file2Path.toString(), "UTF-8")) {
                ps.print(builder.toString());
            }
        } catch (IOException exc) {
            exc.printStackTrace();
            return;
        }
        try (Scanner fin = new Scanner(file2Path, "UTF-8")) {
            System.out.println(file2Path.toString() + ":");
            while (fin.hasNext()) {
                System.out.println(fin.nextLine());
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
    
}
