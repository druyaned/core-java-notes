package com.github.druyaned.horstmann.corejava;

import java.util.Scanner;

/**
 * Practice implementation of learning Java by the Horstmann's book
 * "Core Java" (tenth edition).
 * 
 * @author druyaned
 */
public class App {
    
    /** The app {@link Scanner scanner} of the {@link System#in}. */
    public static final Scanner sin = new Scanner(System.in);
    
    /**
     * Gets the {@link Book book} and accepts the volume number
     * which is the only argument of the executable command.
     * @param args the only argument of the executable command
     *      which is the volume number
     */
    public static void main(String[] args) {
        Book book = new Book();
        String wrongArgsMessage = "One integer argument is required: " +
                "the volume number from 1 to " + book.getVolumes().size();
        if (args.length != 1) {
            System.out.println(wrongArgsMessage);
            return;
        }
        int volNumber;
        try {
            volNumber = Integer.parseInt(args[0]);
        } catch (NumberFormatException exc) {
            System.out.println(wrongArgsMessage);
            return;
        }
        book.accept(volNumber);
    }
    
    /**
     * Returns the name of the project.
     * @return the name of the project.
     */
    public static String getProjectName() {
        return "horstmann-notes";
    }
    
}
