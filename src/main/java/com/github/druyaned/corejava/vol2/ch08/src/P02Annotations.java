package com.github.druyaned.corejava.vol2.ch08.src;

import com.github.druyaned.corejava.vol2.ch08.src.p02.AnnouncementHandler;
import com.github.druyaned.corejava.vol2.ch08.src.p02.Announcement;

/**
 * Part 2 of the chapter 8 to practice with {@code Annotations}.
 * @author druyaned
 */
public class P02Annotations implements Runnable {
    
    @Announcement(color="purple")
    @Override
    public void run() {
        try {
            AnnouncementHandler.handle(P02Annotations.class, "run");
        } catch (NoSuchMethodException | SecurityException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println("The announcement has been successfully completed.");
    }
    
}
