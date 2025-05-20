package druyaned.corejava.vol1.ch10;

import druyaned.corejava.vol1.ch10.p02.LikeComponent;
import druyaned.corejava.vol1.ch10.p01.GoodFrame;
import druyaned.corejava.vol1.ch10.p01.GoodComponent;
import druyaned.corejava.vol1.ch10.p02.LikeFrame;
import druyaned.corejava.Chapter;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * Practical implementation of the Chapter#10: Graphics.
 * @author druyaned
 */
public class Graphics extends Chapter {
    
    /**
     * Creates the Chapter#10: Graphics.
     * @param volDataDir the path to the volume's data-directory
     */
    public Graphics(Path volDataDir) {
        super(volDataDir, 10);
    }
    
    @Override public String getTitle() {
        return "Graphics";
    }
    
    @Override public void run() {
        EventQueue.invokeLater(() -> {
            GoodFrame frame = new GoodFrame("Good frame");
            GoodComponent component = new GoodComponent(frame.getWidth(), frame.getHeight());
            frame.getContentPane().add(component);
            frame.pack();
            frame.setVisible(true);
            LikeFrame likeFrame = new LikeFrame(frame);
            LikeComponent likeComponent = new LikeComponent(
                    likeFrame,
                    likeFrame.getLikeImage()
            );
            likeFrame.getContentPane().add(likeComponent);
            likeFrame.setVisible(true);
            
        });
        GraphicsEnvironment locGrEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontFamilyNames = locGrEnv.getAvailableFontFamilyNames();
        int ind = Arrays.binarySearch(fontFamilyNames, "Monospaced");
        System.out.println("Monospaced's index=" + ind);
    }
    
}
