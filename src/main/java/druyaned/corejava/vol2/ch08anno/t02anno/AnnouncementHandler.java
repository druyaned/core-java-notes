package druyaned.corejava.vol2.ch08anno.t02anno;

import static druyaned.ConsoleColors.*;
import java.lang.reflect.Method;

public class AnnouncementHandler {
    
    public static void handle(Class<?> classObj, String methodName)
            throws NoSuchMethodException
    {
        Method method = classObj.getDeclaredMethod(methodName);
        Announcement announcement = method.getAnnotation(Announcement.class);
        if (announcement != null) {
            String color = announcement.color();
            StringBuilder messageBuilder = new StringBuilder("\nRunning \"");
            switch (color) {
                case "red" -> messageBuilder.append(redBold(methodName));
                case "green" -> messageBuilder.append(greenBold(methodName));
                case "blue" -> messageBuilder.append(blueBold(methodName));
                case "purple" -> messageBuilder.append(purpleBold(methodName));
                default -> messageBuilder.append(bold(methodName));
            }
            messageBuilder.append("\" method of \"")
                    .append(bold(classObj.getSimpleName()))
                    .append("\" class.");
            System.out.println(messageBuilder.toString());
        }
    }
    
}
