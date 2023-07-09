package com.github.druyaned.learn_java.vol2.chapter08;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Part 2 of the chapter 8 to practice with {@code Annotations}.
 * 
 * @author druyaned
 */
public class P02Annotations {
    
    @P02Announcement(color="purple")
    public static void run() {
        try {
            P02AnnouncementHandler.handle(P02Annotations.class, "run");
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(P02Annotations.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        System.out.println("The announcement has been successfully completed.");
    }

}
