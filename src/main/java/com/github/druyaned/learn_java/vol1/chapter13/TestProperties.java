package com.github.druyaned.learn_java.vol1.chapter13;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Properties;
import java.util.Set;

import javax.swing.*;

import static com.github.druyaned.ConsoleColors.bold;
import com.github.druyaned.learn_java.vol1.Volume1;
import java.io.UncheckedIOException;

public class TestProperties {
    public static final int WIDTH;
    public static final int HEIGHT;
    public static final int X;
    public static final int Y;
    private static final double SCREEN_PART = 1.5;
    private static final String TITLE = "Testing properties";
    
    static {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        WIDTH = (int)(d.getWidth() / SCREEN_PART);
        HEIGHT = (int)(d.getHeight() / SCREEN_PART);
        X = (int)(d.getWidth() / 2) - (int)(WIDTH / 2);
        Y = (int)(d.getHeight() / 2) - (int)(HEIGHT / 2);
    }

    public static void run() {
        System.out.println("\n" + bold(TITLE));
        final int x, y;
        Properties properties = new Properties(); // Stores previous location

        Path dirPath = Volume1.getDataDirPath().resolve("chapter13");
        Path propPath = dirPath.resolve("properties");
        
        if (!Files.exists(propPath)) {
            try {
                if (!Files.exists(dirPath)) {
                    Files.createDirectories(dirPath);
                }
                Files.createFile(propPath);
            } catch (IOException exc) {
                throw new UncheckedIOException(exc);
            }
            properties.put("x", Integer.toString(X));
            properties.put("y", Integer.toString(Y));
            x = X;
            y = Y;
        } else {
            String perms = "rw-r--r--";
            Set<PosixFilePermission> permSet = PosixFilePermissions.fromString(perms);
            try {    
                Files.setPosixFilePermissions(propPath, permSet);
                FileReader fileReader = new FileReader(propPath.toFile());
                BufferedReader reader = new BufferedReader(fileReader);
                properties.load(reader);
                x = Integer.parseInt((String) properties.get("x"));
                y = Integer.parseInt((String) properties.get("y"));
            } catch (IOException | NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }
        
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame(TITLE);
            frame.setBounds(x, y, WIDTH, HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent event) {
                    int lastX = frame.getX();
                    int lastY = frame.getY();
                    properties.put("x", Integer.toString(lastX));
                    properties.put("y", Integer.toString(lastY));
                    try (FileWriter fileReader = new FileWriter(propPath.toFile());
                         BufferedWriter writer = new BufferedWriter(fileReader);)
                    {
                        properties.store(writer, TITLE);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            frame.setVisible(true);
        });
    }
}
