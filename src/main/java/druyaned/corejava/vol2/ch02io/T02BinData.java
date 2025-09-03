package druyaned.corejava.vol2.ch02io;

import static druyaned.ConsoleColors.blueBold;
import static druyaned.ConsoleColors.greenBold;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class T02BinData extends Topic {
    
    private final EmployeeData data;
    
    public T02BinData(Chapter chapter, EmployeeData data) {
        super(chapter, 2);
        this.data = data;
    }
    
    @Override public String title() {
        return "Topic 02 BinData";
    }
    
    @Override public void run() {
        // initializing employees
        Employee[] employees = data.getEmployees();
        final int EMPLOYEE_COUNT = employees.length;
        Path filePath = data.getFilePath();
        // writing
        try (
                FileOutputStream fileOut = new FileOutputStream(filePath.toFile());
                DataOutputStream dataOut = new DataOutputStream(fileOut);
        ) {
            System.out.println(blueBold("Writing employees into " + filePath) + "...");
            for (Employee employee : employees)
                data.writeEmployee(dataOut, employee);
            System.out.println(greenBold("Employees was successfully written") + "!");
        } catch (IOException exc) {
            exc.printStackTrace();
            return;
        }
        // reading
        try (
                FileInputStream fileIn = new FileInputStream(filePath.toFile());
                DataInputStream dataIn = new DataInputStream(fileIn);
        ) {
            List<Employee> employeeList = new ArrayList<>();
            System.out.println(blueBold("Reading employees from " + filePath) + "...");
            while (dataIn.available() != 0)
                employeeList.add(data.readEmployee(dataIn));
            System.out.println(greenBold("Employees was successfully read") + "!");
            System.out.println("Employees:");
            for (int i = 0; i < EMPLOYEE_COUNT; i++)
                System.out.printf("  %d. %s\n", i + 1, employeeList.get(i));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
    
}
