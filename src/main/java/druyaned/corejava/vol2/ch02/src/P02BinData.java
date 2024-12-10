package druyaned.corejava.vol2.ch02.src;

import static druyaned.ConsoleColors.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Part 2 of the chapter 2 to practice in some reading and writing a binary data.
 * @author druyaned
 */
public class P02BinData implements Runnable {
    
    private final EmployeeData data;
    
    public P02BinData(EmployeeData data) {
        this.data = data;
    }
    
    @Override public void run() {
        System.out.println("\n" + bold("Part 02 BinData"));
        // initializing employees
        Employee[] employees = data.getEmployees();
        final int EMPLOYEE_COUNT = employees.length;
        Path filePath = data.getFilePath();
        // writing
        try (DataOutputStream dataOut = new DataOutputStream(
                new FileOutputStream(filePath.toFile())
        )) {
            System.out.println(blueBold("Writing employees into " + filePath) + "...");
            for (Employee employee : employees) {
                data.writeEmployee(dataOut, employee);
            }
            System.out.println(greenBold("Employees was successfully written") + "!");
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
        // reading
        try (DataInputStream dataIn = new DataInputStream(
                new FileInputStream(filePath.toFile())
        )) {
            List<Employee> employeeList = new ArrayList<>();
            System.out.println(blueBold("Reading employees from " + filePath) + "...");
            while (dataIn.available() != 0) {
                employeeList.add(data.readEmployee(dataIn));
            }
            System.out.println(greenBold("Employees was successfully read") + "!");
            System.out.println("Employees:");
            for (int i = 0; i < EMPLOYEE_COUNT; i++) {
                System.out.printf("  %d. %s\n", i + 1, employeeList.get(i));
            }
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
}
