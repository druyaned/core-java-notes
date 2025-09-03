package druyaned.corejava.vol2.ch08anno;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.vol2.ch08anno.t02anno.AnnouncementHandler;
import druyaned.corejava.vol2.ch08anno.t02anno.Announcement;

public class T02Annotations extends Topic {
    
    public T02Annotations(Chapter chapter) {
        super(chapter, 2);
    }
    
    @Override public String title() {
        return "Topic 02 Annotations";
    }
    
    @Announcement(color="purple")
    @Override
    public void run() {
        try {
            AnnouncementHandler.handle(T02Annotations.class, "run");
        } catch (NoSuchMethodException | SecurityException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println("The announcement has been successfully completed.");
    }
    
}
