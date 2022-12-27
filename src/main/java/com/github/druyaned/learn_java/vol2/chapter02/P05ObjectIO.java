package com.github.druyaned.learn_java.vol2.chapter02;

import static com.github.druyaned.ConsoleColors.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Part 5 of the chapter 2 to practice in serialization and object reading or writing.
 * 
 * @author druyaned
 */
public class P05ObjectIO {
    
    public static void run() {
        System.out.println("\n" + bold("Part 05 ObjectIO"));
        
        // determination of the file for the serialization
        Path dirPath = EmployeeData.getFilePath().getParent();
        String fileName = "serialized-employees.dat";
        Path filePath = dirPath.resolve(fileName);
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException ex) {
                Logger.getLogger(P05ObjectIO.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }
        
        // initializing employees
        Employee[] employees = EmployeeData.getEmployees();
        Employee[] readEmployees;
        
        // writing employees as objects
        try (FileOutputStream fileOut = new FileOutputStream(filePath.toFile());
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            
            objectOut.writeObject(employees); // also can be written by for-loop
            System.out.println(blueBold("Employees have been written as objects"));
            
        } catch (IOException ex) {
            Logger.getLogger(P05ObjectIO.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        // reading employees as objects
        try (FileInputStream fileIn = new FileInputStream(filePath.toFile());
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            
            readEmployees = (Employee[]) objectIn.readObject();
            System.out.println(blueBold("Employees have been read as objects"));
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(P05ObjectIO.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        System.out.println("Read employees:");
        for (int i = 0; i < readEmployees.length; ++i)
            System.out.println("  " + readEmployees[i]);
    }
}
