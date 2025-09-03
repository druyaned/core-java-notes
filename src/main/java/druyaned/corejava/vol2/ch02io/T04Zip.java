package druyaned.corejava.vol2.ch02io;

import static druyaned.ConsoleColors.*;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.util.FileUtil;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class T04Zip extends Topic {
    
    private final EmployeeData data;
    private final Path zipPath;
    
    public T04Zip(Chapter chapter, EmployeeData data) {
        super(chapter, 4);
        this.data = data;
        this.zipPath = dataDir().resolve("employees.zip");
        FileUtil.createFileOnDemand(zipPath);
    }
    
    public Path zipPath() {
        return zipPath;
    }
    
    @Override public String title() {
        return "Topic 04 Zip";
    }
    
    @Override public void run() {
        // writing the zip-file
        final int AVAILABLE_BYTES;
        final int EMPLOYEE_COUNT = data.getEmployees().length;
        try (
                FileOutputStream fileOut = new FileOutputStream(zipPath.toFile());
                ZipOutputStream zipOut = new ZipOutputStream(fileOut);
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
            System.out.println(blueBold(zipPath.toString()) + " was "
                    + greenBold("successfully") + " written!");
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
        // reading the zip-file
        try (
                FileInputStream fileIn = new FileInputStream(zipPath.toFile());
                ZipInputStream zipIn = new ZipInputStream(fileIn);
        ) {
            zipIn.getNextEntry(); // entry is opened
            byte[] fileBytes = new byte[AVAILABLE_BYTES];
            System.out.println("Reading " + blueBold(zipPath.toString()) + "...");
            zipIn.read(fileBytes, 0, AVAILABLE_BYTES);
            System.out.println(blueBold(zipPath.toString()) + " has been "
                    + greenBold("successfully") + " read!");
            Employee[] employees = new Employee[EMPLOYEE_COUNT];
            try (
                    ByteArrayInputStream byteIn = new ByteArrayInputStream(fileBytes);
                    DataInputStream dataIn = new DataInputStream(byteIn);
            ) {
                for (int i = 0; i < EMPLOYEE_COUNT; ++i)
                    employees[i] = data.readEmployee(dataIn);
            }
            zipIn.closeEntry(); // entry is closed
            System.out.println("Read employees:");
            for (int i = 0; i < EMPLOYEE_COUNT; i++)
                System.out.printf("  %d. %s\n", i + 1, employees[i]);
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
}
