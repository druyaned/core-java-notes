package com.github.druyaned.horstmann.corejava.vol2.ch02.src;

import static com.github.druyaned.ConsoleColors.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UncheckedIOException;
import java.nio.file.Path;

/**
 * Part 3 of the chapter 2 to practice in some reading and writing a file
 * with a random line access.
 * 
 * <P><i>Important Note</i>: before calling the run method,
 * {@link P02BinData#run()} must be called.
 * 
 * @author druyaned
 */
public class P03RandomAccess implements Runnable {
    
    private final EmployeeData data;
    
    public P03RandomAccess(EmployeeData data) {
        this.data = data;
    }
    
    @Override public void run() {
        System.out.println("\n" + bold("Part 03 RandomAccess"));
        Employee[] employees = data.getEmployees();
        final int EMPLOYEE_COUNT = employees.length;
        Path filePath = data.getFilePath();
        try (RandomAccessFile randomIn = new RandomAccessFile(filePath.toFile(), "r")) {
            int index = EMPLOYEE_COUNT - 2;
            System.out.println(
                    blueBold("Getting an employee") + " with an index=" +
                    purpleBold(Integer.toString(index)) + "..."
            );
            randomIn.seek(index * Employee.DATA_SIZE);
            Employee employee = data.readEmployee(randomIn);
            System.out.println("  Read employee: " + employee);
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
}
