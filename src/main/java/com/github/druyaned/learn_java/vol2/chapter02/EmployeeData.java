package com.github.druyaned.learn_java.vol2.chapter02;

import com.github.druyaned.learn_java.vol2.Volume2;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Determines the way of writing the data-file of the employees.
 * 
 * @author druyaned
 */
class EmployeeData {
    
    /** The amount of {@link Employee employees} in data-file. */
    public static final int AMOUNT = 4;
    
    /** The size of an {@link Employee employee} in {@code bytes}. */
    public static final int SIZE = Employee.NAME_SIZE * 2 + 8;
    
    private static final Path FILE_PATH;
    
    static {
        final String FILE_NAME = "employees.dat";
        Path dataDirPath = Volume2.getDataDirPath().resolve("chapter02");
        FILE_PATH = dataDirPath.resolve(FILE_NAME);
        
        try {
            if (!Files.exists(dataDirPath))
                Files.createDirectories(dataDirPath);
            if(!Files.exists(FILE_PATH))
                Files.createFile(FILE_PATH);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
//-Static-Methods-----------------------------------------------------------------------------------
    
    /**
     * Creates {@link EmployeeData#AMOUNT N} employees, where the last one is a manager also.
     * 
     * @return {@link EmployeeData#AMOUNT N} employees, where the last one is a manager also.
     */
    public static Employee[] getEmployees() {
        Employee[] employees = new Employee[EmployeeData.AMOUNT];
        employees[0] = new Employee("Oleg", 52000D * 12D);
        employees[1] = new Employee("Anna", 57270D * 12D);
        employees[2] = new Employee("Gleb", 54530D * 12D);
        employees[3] = new Manager("Ivan", 61000D * 12D)
                .setBonus(12000D)
                .setSecretary(employees[1]);
        return employees;
    }
    
    /** @return the path of the file with data of the employees. */
    public static Path getFilePath() { return FILE_PATH; }
    
    /**
     * Writes the data of an employee next to the {@code dataOut}.
     * 
     * @param dataOut the binary stream of a file to write data of the employees.
     * @param employee an employee to be written.
     * @throws IOException if an I/O error occurred.
     */
    static void writeEmployee(DataOutput dataOut, Employee employee) throws IOException {
        
        // name
        String name = employee.getName();
        for (int i = 0; i < Employee.NAME_SIZE; ++i) {
            if (i < name.length())
                dataOut.writeChar(name.charAt(i));
            else
                dataOut.writeChar(0);
        }

        // salary
        dataOut.writeDouble(employee.getSalary());
    }
    
    /**
     * Reads the data of an employee next in the {@code dataIn}.
     * 
     * @param dataIn the binary stream of a file with data of the employees.
     * @return an employee by read data.
     * @throws IOException if an I/O error occurred.
     */
    static Employee readEmployee(DataInput dataIn) throws IOException {
        
        // name
        StringBuilder builder = new StringBuilder(Employee.NAME_SIZE);
        int leftToRead = Employee.NAME_SIZE;
        while (leftToRead-- > 0) {
            char ch = dataIn.readChar();
            if (ch == 0)
                break;
            builder.append(ch);
        }
        dataIn.skipBytes(leftToRead * 2); // 1 char = 2 bytes
        String name = builder.toString();

        // salary
        double salary = dataIn.readDouble();

        return new Employee(name, salary);
    }

}
