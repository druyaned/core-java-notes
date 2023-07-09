package com.github.druyaned.learn_java.vol2.chapter08;

import static com.github.druyaned.ConsoleColors.*;
import java.lang.reflect.Method;

/**
 * TODO: write description
 * 
 * @author druyaned
 */
public class P02AnnouncementHandler {
    
    /**
     * TODO: write description
     * 
     * @param cl
     * @param methodName
     * @throws java.lang.NoSuchMethodException see {@link Class#getDeclaredMethod}.
     */
    public static void handle(Class<?> cl, String methodName) throws NoSuchMethodException {
        Method m = cl.getDeclaredMethod(methodName);
        P02Announcement a = m.getAnnotation(P02Announcement.class);
        if (a != null) {
            String color = a.color();
            StringBuilder messageBuilder = new StringBuilder("\n").append(bold("Running "));
            switch (color) {
                case "red" -> messageBuilder.append(redBold(methodName));
                case "green" -> messageBuilder.append(greenBold(methodName));
                case "purple" -> messageBuilder.append(purpleBold(methodName));
                default -> messageBuilder.append(bold(methodName));
            }
            messageBuilder.append(bold(" of " + cl.getSimpleName()));
            System.out.println(messageBuilder.toString());
        }
    }

}
