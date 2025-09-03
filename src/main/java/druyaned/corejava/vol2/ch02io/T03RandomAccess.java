package druyaned.corejava.vol2.ch02io;

import static druyaned.ConsoleColors.*;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UncheckedIOException;
import java.nio.file.Path;

public class T03RandomAccess extends Topic {
    
    private final EmployeeData data;
    
    public T03RandomAccess(Chapter chapter, EmployeeData data) {
        super(chapter, 3);
        this.data = data;
    }
    
    @Override public String title() {
        return "Topic 03 RandomAccess";
    }
    
    @Override public void run() {
        Employee[] employees = data.getEmployees();
        final int EMPLOYEE_COUNT = employees.length;
        Path filePath = data.getFilePath();
        try (RandomAccessFile randomIn = new RandomAccessFile(filePath.toFile(), "r")) {
            int index = EMPLOYEE_COUNT - 2;
            System.out.println(blueBold("Getting an employee") + " with an index="
                    + purpleBold(Integer.toString(index)) + "...");
            randomIn.seek(index * Employee.DATA_SIZE);
            Employee employee = data.readEmployee(randomIn);
            System.out.println("  Read employee: " + employee);
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
}
