package druyaned.corejava.vol1.ch03constr;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.util.FileUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.Scanner;

public class T04FileIO extends Topic {
    
    public T04FileIO(Chapter chapter) {
        super(chapter, 4);
    }
    
    @Override public String title() {
        return "Topic 04 FileIO";
    }
    
    @Override public void run() {
        Path file1Path = dataDir().resolve("text-file-1.txt");
        Path file2Path = dataDir().resolve("text-file-2.txt");
        try {
            FileUtil.createFileOnDemand(file1Path);
            FileUtil.createFileOnDemand(file2Path);
            Files.writeString(file1Path, "Hey yo!\n", StandardOpenOption.WRITE);
            Scanner fin = new Scanner(file1Path, "UTF-8");
            if (fin.hasNext()) {
                String line = fin.nextLine();
                System.out.println("Line in a file: '" + line + "'");
            } else {
                System.out.println("No lines in the file.");
            }
        } catch (IOException exc) {
            exc.printStackTrace();
            return;
        }
        try (PrintWriter out = new PrintWriter(file2Path.toString(), "UTF-8")) {
            out.printf("Date: %1$tc\n", new Date());
            out.print("Just wanna say that's gonna be a great file!\n");
        } catch (IOException exc) {
            exc.printStackTrace();
            return;
        }
        { // a little fun with blocks
            int a = 5;
            System.out.println("a = " + a);
        }
        {
            int a = 3;
            System.out.println("a = " + a);
        }
    }
    
}
