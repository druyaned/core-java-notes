package com.github.druyaned.learn_java.vol2.chapter02;

import static com.github.druyaned.ConsoleColors.*;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Part 4 of the chapter 2 to practice in some reading and writing zip-files.
 * 
 * @author druyaned
 */
public class P04Zip {
    
    public static void run() {
        System.out.println("\n" + bold("Part 04 Zip"));
        
        // determination of the zip-file-path
        Path filePath = EmployeeData.getFilePath();
        Path zipPath = filePath.resolveSibling("employees.zip");
        if (!Files.exists(zipPath)) {
            try {
                Files.createFile(zipPath);
            } catch (IOException ex) {
                Logger.getLogger(P04Zip.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }
        
        // writing the zip-file
        final int AVAILABLE_BYTES;
        try (FileOutputStream zipFileOut = new FileOutputStream(zipPath.toFile());
             ZipOutputStream zipOut = new ZipOutputStream(zipFileOut)) {
            
            ZipEntry zipEntry = new ZipEntry(filePath.getFileName().toString());
            zipOut.putNextEntry(zipEntry); // entry is opened
            FileInputStream fileIn = new FileInputStream(filePath.toFile());
            byte[] fileBytes = new byte[AVAILABLE_BYTES = fileIn.available()];
            fileIn.read(fileBytes);
            zipOut.write(fileBytes, 0, AVAILABLE_BYTES);
            zipOut.closeEntry(); // entry is closed
            System.out.println(blueBold("Data file has been successfully zip-archived"));
            
        } catch (IOException ex) {
            Logger.getLogger(P04Zip.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        // reading the zip-file
        try (FileInputStream zipFileIn = new FileInputStream(zipPath.toFile());
             ZipInputStream zipIn = new ZipInputStream(zipFileIn)) {
            
            zipIn.getNextEntry(); // entry is opened
            byte[] fileBytes = new byte[AVAILABLE_BYTES];
            zipIn.read(fileBytes, 0, AVAILABLE_BYTES);
            ByteArrayInputStream bytesIn = new ByteArrayInputStream(fileBytes);
            Employee[] employees = new Employee[EmployeeData.AMOUNT];
            try (DataInputStream dataIn = new DataInputStream(bytesIn)) {
                for (int i = 0; i < EmployeeData.AMOUNT; ++i)
                    employees[i] = EmployeeData.readEmployee(dataIn);
            }
            zipIn.closeEntry(); // entry is closed
            
            System.out.println(blueBold("Zip-archive has successfully been read"));
            System.out.println("Read employees:");
            for (Employee employee : employees)
                System.out.println("  " + employee);
            
        } catch (IOException ex) {
            Logger.getLogger(P04Zip.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
