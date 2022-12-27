package com.github.druyaned.learn_java.vol2.chapter02;

import static com.github.druyaned.ConsoleColors.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Part 3 of the chapter 2 to practice in some reading and writing a file
 * with a random line access.
 * 
 * @author druyaned
 */
public class P03RandomAccess {
    
    /** <b>NOTE</b>: before calling this method, {@link P02BinData#run()} must be called. */
    public static void run() {
        System.out.println("\n" + bold("Part 03 RandomAccess"));
        
        final int N = EmployeeData.AMOUNT;
        final Path filePath = EmployeeData.getFilePath();
        
        try (RandomAccessFile randomIn = new RandomAccessFile(filePath.toFile(), "r")) {
            int index = N - 2;
            System.out.println(blueBold("Getting an employee with an index=" + index + " ..."));
            randomIn.seek(index * EmployeeData.SIZE);
            Employee employee = EmployeeData.readEmployee(randomIn);
            System.out.println("  Read employee: " + employee);
        } catch (IOException ex) {
            Logger.getLogger(P03RandomAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
