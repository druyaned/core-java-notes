package druyaned.corejava.vol2.ch02io;

import static druyaned.ConsoleColors.*;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.util.FileUtil;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Path;

public class T05ObjectIO extends Topic {
    
    private final EmployeeData data;
    private final Path filePath;
    
    public T05ObjectIO(Chapter chapter, EmployeeData data) {
        super(chapter, 5);
        this.data = data;
        this.filePath = dataDir().resolve("serialized-employees.dat");
        FileUtil.createFileOnDemand(filePath);
    }
    
    @Override public String title() {
        return "Topic 05 ObjectIO";
    }
    
    @Override public void run() {
        // initializing employees
        Employee[] originalEmployees = data.getEmployees();
        Employee[] readEmployees;
        // writing employees as objects
        try (
                FileOutputStream fileOut = new FileOutputStream(filePath.toFile());
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        ) {
            objectOut.writeObject(originalEmployees); // also can be written by for-loop
            System.out.println("Array of employees was "
                    + greenBold("successfully") + " written into "
                    + blueBold(filePath.toString()) + "!");
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
        // reading employees as objects
        try (
                FileInputStream fileIn = new FileInputStream(filePath.toFile());
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        ) {
            readEmployees = (Employee[])objectIn.readObject();
            System.out.println("Array of employees was "
                    + greenBold("successfully") + " read from "
                    + blueBold(filePath.toString()) + "!");
        } catch (IOException | ClassNotFoundException exc) {
            throw new RuntimeException(exc);
        }
        System.out.println("Read employees:");
        for (int i = 0; i < readEmployees.length; ++i)
            System.out.printf("  %d. %s\n", i + 1, readEmployees[i]);
    }
    
}
