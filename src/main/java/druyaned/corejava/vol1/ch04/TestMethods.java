package druyaned.corejava.vol1.ch04;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

public class TestMethods {
    
    public static void testAll(Path chapterDataDir) {
        testFileIO(chapterDataDir);
        testBitOperations();
        testLocalDate();
    }
    
    public static void testFileIO(Path chapterDataDir) {
        System.out.println();
        Path file1Path = chapterDataDir.resolve("text-file1.txt");
        Path file2Path = chapterDataDir.resolve("text-file2.txt");
        try {
            if (!Files.exists(file1Path)) {
                Files.createFile(file1Path);
            }
            if (!Files.exists(file2Path)) {
                Files.createFile(file2Path);
            }
            String text = "Hey yo ma boy!\nOw my!\n";
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
    
    public static void testBitOperations() {
        System.out.println();
        int a = 13, b = 10;
        String s;
        s = Integer.toBinaryString(a);
        System.out.println("a: " + s + " = " + a);
        s = Integer.toBinaryString(b);
        System.out.println("b: " + s + " = " + b);
        s = Integer.toBinaryString(a ^ b);
        System.out.println("a ^ b: " + s + " = " + (a ^ b));
        s = Integer.toBinaryString(a & b);
        System.out.println("a & b: " + s + " = " + (a & b));
        s = Integer.toBinaryString(a | b);
        System.out.println("a | b: " + s + " = " + (a | b));
        s = Integer.toBinaryString(~a);
        System.out.println("~a: " + s + " = " + (~a));
        s = Integer.toBinaryString(a >> 2);
        System.out.println("a >> 2: " + s + " = " + (a >> 2));
        s = Integer.toBinaryString(a << 3);
        System.out.println("a << 3: " + s + " = " + (a << 3));
        s = Integer.toBinaryString(~a >> 2);
        System.out.println("~a >> 2: " + s + " = " + (~a >> 2));
    }
    
    public static void testLocalDate() {
        System.out.println();
        LocalDate today = LocalDate.now();
        System.out.println("Day: " + today);
        System.out.println(" Mon Tue Wed Thu Fri Sat Sun");
        DayOfWeek fstDay = today.minusDays(today.getDayOfMonth() - 1).getDayOfWeek();
        for (int i = 1; i <= 7; ++i) {
            if (fstDay.getValue() == i)
                { break; }
            System.out.printf(" %3s", " ");
        }
        int curDay = fstDay.getValue();
        for (int i = 1; i <= today.lengthOfMonth(); ++i, ++curDay) {
            System.out.printf(" %3d", i);
            if (curDay % 7 == 0)
                { System.out.println(); }
        }
        if ((curDay - 1) % 7 != 0) {
            System.out.println();
        }
    }
    
}
