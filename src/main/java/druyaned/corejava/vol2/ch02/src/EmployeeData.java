package druyaned.corejava.vol2.ch02.src;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Determines the way of writing the data-file of the employees.
 * @author druyaned
 */
public class EmployeeData {
    
    private final Path filePath;
    private final Employee[] employees;
    
    public EmployeeData(Path dataDir) {
        filePath = dataDir.resolve("employees.dat");
        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
        employees = new Employee[] {
            new Employee("Oleg", 52000D * 12D),
            new Employee("Anna", 57270D * 12D),
            new Employee("Gleb", 54530D * 12D),
            new Manager("Ivan", 61000D * 12D)
        };
        ((Manager)employees[3])
                .setBonus(12000D)
                .setSecretary(employees[1]);
    }
    
    public Path getFilePath() {
        return filePath;
    }
    
    public Employee[] getEmployees() {
        return employees;
    }
    
    /**
     * Appends data of the {@code employee} to the {@code dataOut}.
     * @param dataOut the binary stream of a file to be appended
     * @param employee to write the data
     */
    public synchronized void writeEmployee(DataOutput dataOut, Employee employee) {
        try {
            // name
            char[] nameArray = employee.getName().toCharArray();
            for (char ch : nameArray) {
                dataOut.writeChar(ch);
            }
            for (int i = nameArray.length; i < Employee.MAX_NAME_LENGTH; i++) {
                dataOut.writeChar(0);
            }
            // salary
            dataOut.writeDouble(employee.getSalary());
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
    /**
     * Reads next data of an employee in the {@code dataIn}.
     * @param dataIn the binary stream of a file with data of employees
     * @return read employee
     */
    public synchronized Employee readEmployee(DataInput dataIn) {
        try {
            // name
            StringBuilder builder = new StringBuilder(Employee.MAX_NAME_LENGTH);
            int leftToRead = Employee.MAX_NAME_LENGTH;
            while (leftToRead > 0) {
                char ch = dataIn.readChar();
                leftToRead--;
                if (ch == 0) {
                    break;
                }
                builder.append(ch);
            }
            dataIn.skipBytes(leftToRead * 2); // 1 char = 2 bytes
            String name = builder.toString();
            // salary
            double salary = dataIn.readDouble();
            return new Employee(name, salary);
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
}
