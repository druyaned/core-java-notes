package com.github.druyaned.corejava.vol2.ch02.src;

import static com.github.druyaned.ConsoleColors.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Part 5 of the chapter 2 to practice in serialization and object reading or writing.
 * @author druyaned
 */
public class P05ObjectIO implements Runnable {
    
    private final Path filePath;
    private final EmployeeData data;
    
    public P05ObjectIO(Path dataDir, EmployeeData data) {
        filePath = dataDir.resolve("serialized-employees.dat");
        try {
            if (!Files.exists(filePath)) {
                System.out.println("Creting " + blueBold(filePath.toString()) + "...");
                Files.createFile(filePath);
                System.out.println(blueBold(filePath.toString()) + " was " +
                        greenBold("successfully") + " created"
                );
            }
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
        this.data = data;
    }
    
    @Override public void run() {
        System.out.println("\n" + bold("Part 05 ObjectIO"));
        // initializing employees
        Employee[] originalEmployees = data.getEmployees();
        Employee[] readEmployees;
        // writing employees as objects
        try (ObjectOutputStream objectOut = new ObjectOutputStream(
                new FileOutputStream(filePath.toFile())
        )) {
            objectOut.writeObject(originalEmployees); // also can be written by for-loop
            System.out.println(
                    "Array of employees was " +
                    greenBold("successfully") + " written into " +
                    blueBold(filePath.toString()) + "!"
            );
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
        // reading employees as objects
        try (ObjectInputStream objectIn = new ObjectInputStream(
                new FileInputStream(filePath.toFile())
        )) {
            readEmployees = (Employee[])objectIn.readObject();
            System.out.println(
                    "Array of employees was " +
                    greenBold("successfully") + " read from " +
                    blueBold(filePath.toString()) + "!"
            );
        } catch (IOException | ClassNotFoundException exc) {
            throw new RuntimeException(exc);
        }
        System.out.println("Read employees:");
        for (int i = 0; i < readEmployees.length; ++i) {
            System.out.printf("  %d. %s\n", i + 1, readEmployees[i]);
        }
    }
    
}
