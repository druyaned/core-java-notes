package com.github.druyaned.learn_java.vol1.chapter10;

import static com.github.druyaned.ConsoleColors.bold;
import com.github.druyaned.learn_java.Chapterable;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;

/**
 * Practice implementation of the chapter 10.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol1.Volume1
 */
public class Chapter10 implements Chapterable {

    @Override
    public void run() {
        System.out.println(bold("Running Chapter10: Graphics"));
        
        EventQueue.invokeLater(() -> {
            GoodFrame frame = new GoodFrame("Good frame");
            GoodComponent component = new GoodComponent(frame.getWidth(), frame.getHeight());
            frame.getContentPane().add(component);
            frame.pack();
            frame.setVisible(true);

            LikeFrame likeFrame = new LikeFrame(frame);
            LikeComponent likeComponent = new LikeComponent(likeFrame,
                          likeFrame.getLikeImage());
            likeFrame.getContentPane().add(likeComponent);
            likeFrame.setVisible(true);
        });

        GraphicsEnvironment locGrEnv =
                            GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontFamilyNames = locGrEnv.getAvailableFontFamilyNames();
        int ind = Arrays.binarySearch(fontFamilyNames, "Monospaced");
        System.out.println("Monospaced's index=" + ind);
    }

    @Override
    public int getNumber() { return 10; }
    
    @Override
    public String getTitle() { return "Graphics"; }
    
    @Override
    public boolean passed() { return true; }
}
