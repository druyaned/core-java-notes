package com.github.druyaned.corejava;

import java.util.Scanner;

/**
 * App of a practice implementation of Horstmann's book
 * "Core Java" (tenth edition); the entry point of the program.
 * 
 * @author druyaned
 * @see Book
 */
public class App {
    
    /** The app {@link Scanner scanner} of the {@link System#in}. */
    public static final Scanner sin = new Scanner(System.in);
    
    /**
     * Creates the {@link Book book} and accepts the volume number
     * which is the only argument of the executable command.
     * @param args the only argument of the executable command
     *      which is the volume number
     */
    public static void main(String[] args) {
        // create book
        Book book = new Book();
        // validate args.length = 1
        String wrongArgsMessage = "One integer argument is required:"
                + " the volume number from 1 to " + book.getVolumes().size();
        if (args.length != 1) {
            System.out.println(wrongArgsMessage);
            return;
        }
        // validate args[0] = volumeNumber
        int volumeNumber;
        try {
            volumeNumber = Integer.parseInt(args[0]);
        } catch (NumberFormatException exc) {
            System.out.println(wrongArgsMessage);
            return;
        }
        // run volume
        book.accept(volumeNumber);
    }
    
    /**
     * Returns the name of the project.
     * @return project's name
     */
    public static String getProjectName() {
        return "core-java-notes";
    }
    
}
