package com.github.druyaned.horstmann.corejava.vol2.ch02.src;

import static com.github.druyaned.ConsoleColors.*;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Part 4 of the chapter 2 to practice in some reading and writing zip-files.
 * @author druyaned
 */
public class P04Zip implements Runnable {
    
    private final Path zipPath;
    private final EmployeeData data;
    
    public P04Zip(Path dataDir, EmployeeData data) {
        zipPath = dataDir.resolve("employees.zip");
        try {
            if (!Files.exists(zipPath)) {
                System.out.println("Creting " + blueBold(zipPath.toString()) + "...");
                Files.createFile(zipPath);
                System.out.println(blueBold(zipPath.toString()) + " was " +
                        greenBold("successfully") + " created"
                );
            }
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
        this.data = data;
    }
    
    public Path getZipPath() {
        return zipPath;
    }
    
    @Override public void run() {
        System.out.println("\n" + bold("Part 04 Zip"));
        // writing the zip-file
        final int AVAILABLE_BYTES;
        final int EMPLOYEE_COUNT = data.getEmployees().length;
        try (
                ZipOutputStream zipOut = new ZipOutputStream(
                        new FileOutputStream(zipPath.toFile())
                );
                FileInputStream fileIn = new FileInputStream(data.getFilePath().toFile());
        ) {
            AVAILABLE_BYTES = fileIn.available();
            ZipEntry zipEntry = new ZipEntry(zipPath.getFileName().toString());
            zipOut.putNextEntry(zipEntry); // entry is opened
            byte[] fileBytes = new byte[AVAILABLE_BYTES];
            System.out.println("Reading " + blueBold(data.getFilePath().toString()) + "...");
            fileIn.read(fileBytes);
            System.out.println("Writing " + blueBold(zipPath.toString()) + "...");
            zipOut.write(fileBytes, 0, AVAILABLE_BYTES);
            zipOut.closeEntry();
            System.out.println(
                    blueBold(zipPath.toString()) + " was " +
                    greenBold("successfully") + " written!"
            );
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
        // reading the zip-file
        try (ZipInputStream zipIn = new ZipInputStream(
                new FileInputStream(zipPath.toFile())
        )) {
            zipIn.getNextEntry(); // entry is opened
            byte[] fileBytes = new byte[AVAILABLE_BYTES];
            System.out.println("Reading " + blueBold(zipPath.toString()) + "...");
            zipIn.read(fileBytes, 0, AVAILABLE_BYTES);
            System.out.println(
                    blueBold(zipPath.toString()) + " has been " +
                    greenBold("successfully") + " read!"
            );
            Employee[] employees = new Employee[EMPLOYEE_COUNT];
            try (DataInputStream dataIn = new DataInputStream(
                    new ByteArrayInputStream(fileBytes)
            )) {
                for (int i = 0; i < EMPLOYEE_COUNT; ++i) {
                    employees[i] = data.readEmployee(dataIn);
                }
            }
            zipIn.closeEntry(); // entry is closed
            System.out.println("Read employees:");
            for (int i = 0; i < EMPLOYEE_COUNT; i++) {
                System.out.printf("  %d. %s\n", i + 1, employees[i]);
            }
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
}
