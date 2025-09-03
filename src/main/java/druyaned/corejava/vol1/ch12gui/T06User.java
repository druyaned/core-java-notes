package druyaned.corejava.vol1.ch12gui;

import druyaned.corejava.Book;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.util.FileUtil;
import druyaned.corejava.util.ScreenSize;
import druyaned.corejava.vol1.ch12gui.t06user.AboutComponent;
import druyaned.corejava.vol1.ch12gui.t06user.CloseAction;
import druyaned.corejava.vol1.ch12gui.t06user.User;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class T06User extends Topic {
    
    public T06User(Chapter chapter) {
        super(chapter, 6);
    }
    
    @Override public String title() {
        return "Topic 06 User";
    }
    
    @Override public void run() {
        Rectangle bounds = ScreenSize.get(0.75);
        EventQueue.invokeLater(() -> {
            // Creating and setting a frame
            JFrame frame = new JFrame("Testing users");
            frame.setBounds(bounds);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Creating about-menu
            JMenu aboutMenu = new JMenu("About");
            JMenuItem aboutItem = new JMenuItem("About");
            // Creating dialog frame
            JDialog aboutDialog = new JDialog(frame, "About", false);
            JPanel aboutPanel = new JPanel(new BorderLayout());
            int dialogWidth = bounds.height / 2;
            int dialogHeight = bounds.height / 2;
            int dialogX = bounds.x + bounds.width / 4;
            int dialogY = bounds.y + bounds.height / 4;
            aboutPanel.setPreferredSize(new Dimension(dialogWidth, dialogHeight));
            aboutDialog.setLocation(dialogX, dialogY);
            aboutPanel.add(new AboutComponent(dialogWidth, dialogHeight));
            aboutDialog.add(aboutPanel, BorderLayout.CENTER);
            aboutDialog.pack();
            // Adding listener for about-item
            aboutItem.addActionListener((event) -> aboutDialog.setVisible(true));
            aboutItem.setMnemonic('A');
            KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.META_DOWN_MASK);
            aboutItem.setAccelerator(keyStroke);
            // Adding display
            JLabel display = new JLabel("No user input");
            display.setHorizontalAlignment(JLabel.CENTER);
            Font displayFont = new Font(Font.MONOSPACED, Font.PLAIN, 24);
            display.setFont(displayFont);
            frame.add(display);
            // Creating user-menu
            JDialog userDialog = new JDialog(frame, "User data", true);
            JMenu userMenu = new JMenu("User");
            JMenuItem userItem = new JMenuItem("Data input");
            userItem.setMnemonic('D');
            keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.META_DOWN_MASK);
            userItem.setAccelerator(keyStroke);
            User user = new User();
            JPanel userPanel = new JPanel(new GridLayout(2, 2));
            JLabel nameLabel = new JLabel("Name:");
            JLabel passLabel = new JLabel("Pass:");
            nameLabel.setHorizontalAlignment(JLabel.RIGHT);
            passLabel.setHorizontalAlignment(JLabel.RIGHT);
            int columns = 20;
            JTextField nameField = new JTextField(columns);
            JPasswordField passField = new JPasswordField(columns);
            userPanel.add(nameLabel);
            userPanel.add(nameField);
            userPanel.add(passLabel);
            userPanel.add(passField);
            // Creating ok-button
            JButton ok = new JButton("OK");
            ok.addActionListener((event) -> {
                String name = nameField.getText();
                String pass = new String(passField.getPassword());
                nameField.setText("");
                passField.setText("");
                userDialog.setVisible(false);
                display.setFont(displayFont);
                if (user.set(name, pass)) {
                    display.setText("User data is configured");
                } else if (user.checkUser(name, pass)) {
                    display.setText("Correct user data");
                } else {
                    display.setText("Incorrect user data");
                }
            });
            // Setting user-dialog
            userDialog.setLayout(new BorderLayout());
            userDialog.add(userPanel, BorderLayout.CENTER);
            userDialog.add(ok, BorderLayout.SOUTH);
            userDialog.pack();
            userDialog.getRootPane().setDefaultButton(ok);
            int userX = bounds.x + bounds.width / 2 - userDialog.getWidth() / 2;
            int userY = bounds.y + bounds.height / 2 - userDialog.getHeight() / 2;
            userDialog.setLocation(userX, userY);
            // Adding listener to the user-item
            userItem.addActionListener((event) -> userDialog.setVisible(true));
            // Creating file-menu
            JMenu fileMenu = new JMenu("File");
            JMenuItem openItem = new JMenuItem("Open");
            openItem.setMnemonic('O');
            JFileChooser fileChooser = new JFileChooser();
            String fileName1 = "file1.txt";
            String fileName2 = "file2.txt";
            Path file1Path = dataDir().resolve(fileName1);
            Path file2Path = dataDir().resolve(fileName2);
            FileUtil.createFileOnDemand(file1Path);
            FileUtil.createFileOnDemand(file2Path);
            Book book = chapter().volume().book();
            try (
                    InputStream fin1 = FileUtil.stream(book, this, fileName1);
                    InputStream fin2 = FileUtil.stream(book, this, fileName2);
            ) {
                Files.copy(fin1, file1Path, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(fin2, file2Path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException exc) {
                throw new UncheckedIOException(exc);
            }
            fileChooser.setCurrentDirectory(dataDir().toFile());
            if (fileChooser.isMultiSelectionEnabled()) {
                fileChooser.setMultiSelectionEnabled(false);
            }
            String description = "Plain text";
            String extension = "txt";
            FileFilter filter = new FileNameExtensionFilter(description, extension);
            fileChooser.setFileFilter(filter);
            fileChooser.setAcceptAllFileFilterUsed(false);
            // Adding listener to file-menu
            Font textFont = new Font(Font.MONOSPACED, Font.PLAIN, 12);
            openItem.addActionListener((event) -> {
                int out = fileChooser.showOpenDialog(frame);
                if (out == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    ArrayList<String> text = new ArrayList<>();
                    try (Scanner scanner = new Scanner(file)) {
                        while (scanner.hasNext()) { text.add(scanner.nextLine()); }
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    StringBuilder builder = new StringBuilder();
                    if (!text.isEmpty()) { builder.append(text.get(0)); }
                    for (int i = 1; i < text.size(); ++i) {
                        builder.append(' ').append(text.get(i));
                    }
                    display.setFont(textFont);
                    display.setText(builder.toString());
                }
            });
            keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.META_DOWN_MASK);
            openItem.setAccelerator(keyStroke);
            // Adding all menus
            fileMenu.add(openItem);
            userMenu.add(userItem);
            aboutMenu.add(aboutItem);
            JMenuBar menuBar = new JMenuBar();
            menuBar.add(fileMenu);
            menuBar.add(userMenu);
            menuBar.add(aboutMenu);
            frame.getContentPane().add(menuBar, BorderLayout.NORTH);
            // Adding close operation to the aboutPanel
            InputMap iMap = aboutPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap aMap = aboutPanel.getActionMap();
            keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.META_DOWN_MASK);
            iMap.put(keyStroke, "close_about_dialog");
            CloseAction closeAboutDialog = new CloseAction(aboutDialog);
            aMap.put("close_about_dialog", closeAboutDialog);
            // Adding close operation to the userPanel
            iMap = userPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            aMap = userPanel.getActionMap();
            iMap.put(keyStroke, "close_user_dialog");
            CloseAction closeUserDialog = new CloseAction(userDialog);
            aMap.put("close_user_dialog", closeUserDialog);
            frame.setVisible(true);
        });
    }
    
}
