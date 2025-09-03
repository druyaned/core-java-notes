package druyaned.corejava.vol1.ch13deploy;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.util.FileUtil;
import druyaned.corejava.util.ScreenSize;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Properties;
import java.util.Set;
import javax.swing.JFrame;

public class T01Properties extends Topic {

    public T01Properties(Chapter chapter) {
        super(chapter, 1);
    }
    
    @Override public String title() {
        return "Topic 01 Properties";
    }
    
    @Override public void run() {
        // Stores previous location
        Rectangle bounds = ScreenSize.get(0.5);
        Properties props = new Properties();
        Path propPath = dataDir().resolve("frame-location.properties");
        if (!Files.exists(propPath)) {
            FileUtil.createFileOnDemand(propPath);
            save(props, bounds.x, bounds.y, propPath);
        } else {
            String perms = "rw-r--r--";
            Set<PosixFilePermission> permSet = PosixFilePermissions.fromString(perms);
            try {    
                Files.setPosixFilePermissions(propPath, permSet);
                try (
                        FileReader fileReader = new FileReader(propPath.toFile());
                        BufferedReader reader = new BufferedReader(fileReader);
                ) {
                    props.load(reader);
                }
            } catch (IOException | NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame(title());
            int x = Integer.parseInt(props.getProperty("x"));
            int y = Integer.parseInt(props.getProperty("y"));
            frame.setBounds(x, y, bounds.width, bounds.height);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.addWindowListener(new WindowAdapter() {
                @Override public void windowClosing(WindowEvent event) {
                    save(props, frame.getX(), frame.getY(), propPath);
                }
            });
            String text = "Move the window and close it by the GUI-button";
            Label label = new Label(text, Label.CENTER);
            frame.setLayout(new BorderLayout());
            frame.getContentPane().add(label, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
    
    private void save(Properties props, int x, int y, Path propPath) {
        props.put("x", Integer.toString(x));
        props.put("y", Integer.toString(y));
        try (
                FileWriter fileWriter = new FileWriter(propPath.toFile());
                BufferedWriter writer = new BufferedWriter(fileWriter);
        ) {
            props.store(writer, title());
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
}
