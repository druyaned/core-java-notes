package com.github.druyaned.learn_java.vol2.chapter02;

import static com.github.druyaned.ConsoleColors.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Part 2 of the chapter 2 to practice in some reading and writing a binary data.
 * 
 * @author druyaned
 */
public class P02BinData {
    
    public static void run() {
        System.out.println("\n" + bold("Part 02 BinData"));
        
        // initializing employees
        Employee[] employees = EmployeeData.getEmployees();
        
        // declarations for the data file
        Path filePath = EmployeeData.getFilePath();
        
        try {
            writeEmployees(filePath, employees);
            readEmployees(filePath);
        } catch (IOException ex) {
            Logger.getLogger(P02BinData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//-Private-Static-Methods---------------------------------------------------------------------------
    
    private static void writeEmployees(Path filePath, Employee[] employees) throws IOException  {
        try (DataOutputStream dataOut = new DataOutputStream(
                new FileOutputStream(filePath.toFile()))) {
            
            for (Employee employee : employees)
                EmployeeData.writeEmployee(dataOut, employee);
            System.out.println(blueBold("Data of the employees has been successfully written"));
        }
    }
    
    private static void readEmployees(Path filePath) throws IOException {
        FileInputStream fileIn;
        ArrayList<Employee> readEmployees = new ArrayList<>();
        try (DataInputStream dataIn = new DataInputStream(fileIn =
                new FileInputStream(filePath.toFile()))) {
            
            while (fileIn.available() != 0)
                readEmployees.add(EmployeeData.readEmployee(dataIn));
            System.out.println(blueBold("Data of the employees has been successfully read"));
        }
        
        System.out.println("Read employees:");
        for (Employee employee : readEmployees)
            System.out.println("  " + employee);
    }
}
