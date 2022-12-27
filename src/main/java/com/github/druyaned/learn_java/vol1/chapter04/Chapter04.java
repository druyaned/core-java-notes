package com.github.druyaned.learn_java.vol1.chapter04;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.Chapterable;
import com.github.druyaned.learn_java.vol1.Volume1;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Practice implementation of the chapter 4.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol1.Volume1
 */
public class Chapter04 implements Chapterable {

    private static void cout(Object line) { System.out.print(line); }
    private static final String ENDL = "\n";

    public static void testFileIO() {
        
        Path dataDirPath = Volume1.getDataDirPath();
        Path file1Path = dataDirPath.resolve("chapter03").resolve("text-file1.txt");
        Path file2Path = dataDirPath.resolve("chapter03").resolve("text-file2.txt");
        
        try {
            Path dirPath = file1Path.getParent();
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
            if (!Files.exists(file1Path)) {
                Files.createFile(file1Path);
            }
            if (!Files.exists(file2Path)) {
                Files.createFile(file2Path);
            }
            String text1 = "Hey yo ma boy!\nOw my!\n";
            Files.writeString(file1Path, text1, StandardOpenOption.WRITE);
        } catch (IOException exc) {
            exc.printStackTrace();
            return;
        }
        
        try (Scanner sc = new Scanner(file1Path, "UTF-8")) {
            if (!sc.hasNext()) {
                System.out.printf("Oops. There is no lines in %s.\n", file1Path.toString());
                return;
            }
            StringBuilder sBuilder = new StringBuilder();
            String s;
            cout(file1Path.toString() + ":\n");
            while (sc.hasNext()) {
                s = sc.nextLine() + "\n";
                sBuilder.append("\t").append(s);
                cout(s);
            }
            try (PrintStream ps = new PrintStream(file2Path.toString(), "UTF-8")) {
                ps.print(sBuilder.toString());
            }
        } catch (IOException exc) {
            exc.printStackTrace();
            return;
        }
        
        try (Scanner sc = new Scanner(file2Path, "UTF-8")) {
            cout(file2Path.toString() + ":\n");
            while (sc.hasNext()) {
                cout(sc.nextLine() + ENDL);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static void testBitOperations() {
        int a = 13, b = 10;
        String s;
        s = Integer.toBinaryString(a);
        cout("a: " + s + " = " + a + ENDL);
        s = Integer.toBinaryString(b);
        cout("b: " + s + " = " + b + ENDL);

        s = Integer.toBinaryString(a ^ b);
        cout("a ^ b: " + s + " = " + (a ^ b) + ENDL);
        s = Integer.toBinaryString(a & b);
        cout("a & b: " + s + " = " + (a & b) + ENDL);
        s = Integer.toBinaryString(a | b);
        cout("a | b: " + s + " = " + (a | b) + ENDL);
        s = Integer.toBinaryString(~a);
        cout("~a: " + s + " = " + (~a) + ENDL);
        s = Integer.toBinaryString(a >> 2);
        cout("a >> 2: " + s + " = " + (a >> 2) + ENDL);
        s = Integer.toBinaryString(a << 3);
        cout("a << 3: " + s + " = " + (a << 3) + ENDL);
        s = Integer.toBinaryString(~a >> 2);
        cout("~a >> 2: " + s + " = " + (~a >> 2) + ENDL);
    }

    public static void testLocalDate() {
        LocalDate today = LocalDate.now();
        cout("Day: " + today + ENDL);

        PrintStream so = System.out;
        so.println(" Mon Tue Wed Thu Fri Sat Sun");
        DayOfWeek fstDay = today.minusDays(today.getDayOfMonth() - 1).getDayOfWeek();
        for (int i = 1; i <= 7; ++i) {
            if (fstDay.getValue() == i)
                { break; }
            so.printf(" %3s", " ");
        }
        int curDay = fstDay.getValue();
        for (int i = 1; i <= today.lengthOfMonth(); ++i, ++curDay) {
            so.printf(" %3d", i);
            if (curDay % 7 == 0)
                { so.println(); }
        }
        if ((curDay - 1) % 7 != 0)
            { so.println(); }
    }
    
//-Non-static---------------------------------------------------------------------------------------
    
    @Override
    public void run() {
        System.out.println(bold("Running Chapter04: Objects and Classes"));
        
        testFileIO();
        testBitOperations();
        testLocalDate();
    }

    @Override
    public int getNumber() { return 4; }
    
    @Override
    public String getTitle() { return "Objects and Classes"; }
    
    @Override
    public boolean passed() { return true; }
}
