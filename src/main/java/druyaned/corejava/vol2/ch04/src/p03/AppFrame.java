package druyaned.corejava.vol2.ch04.src.p03;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import javax.swing.*;

/**
 * The frame of the app to compare interruptible and blocking work of sockets
 * ({@link Socket} and {@link SocketChannel}).
 * 
 * @author druyaned
 * @see P03Interruptible
 */
public class AppFrame extends JFrame {
    
    private static final int W = 640;
    private static final int H = 512;
    private static final int X = 400;
    private static final int Y = 200;
    private static final String APP_FONT_NAME = Font.MONOSPACED;
    private static final Font TEXT_FONT = new Font(APP_FONT_NAME, Font.PLAIN, 14);
    private static final Font BUTTON_FONT = new Font(APP_FONT_NAME, Font.BOLD, 18);
    private Thread interruptThread;
    private Thread blockThread;
    private final JPanel contentPane;
    private final JPanel buttonsPane;
    private final JPanel areasPane;
    private final JPanel interruptPane;
    private final JPanel blockPane;
    private final JLabel interruptLabel;
    private final JLabel blockLabel;
    private final JTextArea interruptArea;
    private final JTextArea blockArea;
    private final JButton startButton;
    private final JButton interruptButton;
    
    /**
     * Constructs the frame of the app to compare interruptible and blocking work of sockets.
     * @see Socket
     * @see SocketChannel
     */
    public AppFrame() {
        setLocation(X, Y);
        // panes
        contentPane = new JPanel(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(W, H));
        setContentPane(contentPane);
        buttonsPane = new JPanel();
        areasPane = new JPanel(new GridLayout(1, 2));
        contentPane.add(buttonsPane, BorderLayout.NORTH);
        contentPane.add(areasPane, BorderLayout.CENTER);
        interruptPane = new JPanel(new BorderLayout());
        blockPane = new JPanel(new BorderLayout());
        // labels
        interruptLabel = new JLabel("Interruptible");
        blockLabel = new JLabel("Blocking");
        interruptLabel.setHorizontalAlignment(JLabel.CENTER);
        blockLabel.setHorizontalAlignment(JLabel.CENTER);
        // interruptPane and blockPane
        interruptArea = new JTextArea();
        blockArea = new JTextArea();
        interruptArea.setEditable(false);
        interruptArea.setFocusable(false);
        interruptArea.setLineWrap(true);
        blockArea.setEditable(false);
        blockArea.setFocusable(false);
        blockArea.setLineWrap(true);
        final JScrollPane interruptScroll = new JScrollPane(interruptArea);
        final JScrollPane blockScroll = new JScrollPane(blockArea);
        interruptPane.add(interruptLabel, BorderLayout.NORTH);
        interruptPane.add(interruptScroll, BorderLayout.CENTER);
        blockPane.add(blockLabel, BorderLayout.NORTH);
        blockPane.add(blockScroll, BorderLayout.CENTER);
        areasPane.add(interruptPane);
        areasPane.add(blockPane);
        // buttons
        startButton = new JButton("Start");
        interruptButton = new JButton("Interrupt");
        startButton.setEnabled(true);
        interruptButton.setEnabled(false);
        buttonsPane.add(startButton);
        buttonsPane.add(interruptButton);
        // setting fonts
        interruptLabel.setFont(BUTTON_FONT);
        blockLabel.setFont(BUTTON_FONT);
        interruptArea.setFont(TEXT_FONT);
        blockArea.setFont(TEXT_FONT);
        startButton.setFont(BUTTON_FONT);
        interruptButton.setFont(BUTTON_FONT);
        // action listeners for the buttons
        startButton.addActionListener((event) -> {
            startButton.setEnabled(false);
            interruptButton.setEnabled(true);
            setThreads();
            interruptThread.start();
            blockThread.start();
        });
        interruptButton.addActionListener((event) -> {
            interruptThread.interrupt();
            blockThread.interrupt();
            interruptButton.setEnabled(false);
        });
        AppServer.run(interruptArea, blockArea); // launching the server
        pack();
    }
    
    private void setThreads() {
        final String addr = "localhost";
        final int port = 8189;
        interruptThread = new Thread(() -> {
            try (
                    SocketChannel interruptible = SocketChannel.open(
                            new InetSocketAddress(addr, port)
                    )
            ) {
                ByteBuffer bufIn = ByteBuffer.allocate(SocketTask.LETTER_SIZE);
                int readCount;
                while (!interruptThread.isInterrupted()) {
                    EventQueue.invokeLater(() -> interruptArea.append("In unicode "));
                    bufIn.clear();
                    if ((readCount = interruptible.read(bufIn)) == -1) {
                        break;
                    }
                    bufIn.position(0);
                    if (readCount == SocketTask.LETTER_SIZE) {
                        int letter = bufIn.getInt();
                        EventQueue.invokeLater(() -> interruptArea.append(
                                String.format("'%c'=0x%h\n", (char)letter, letter)
                        ));
                    } else {
                        EventQueue.invokeLater(() -> interruptArea.append(
                                "Wrong format\n"
                        ));
                    }
                }
            } catch (IOException exc) {
                EventQueue.invokeLater(() -> interruptArea.append(exc + "\n"));
            } finally {
                String message = "Interruptible channel is closed\n";
                EventQueue.invokeLater(() -> interruptArea.append(message));
            }
        });
        blockThread = new Thread(() -> {
            try (
                    InputStream blockIn = new Socket(addr, port).getInputStream();
            ) {
                byte[] bytes = new byte[SocketTask.LETTER_SIZE];
                int readCount;
                while (!Thread.interrupted()) {
                    EventQueue.invokeLater(() -> blockArea.append("In unicode "));
                    if ((readCount = blockIn.read(bytes)) == -1) {
                        break;
                    }
                    if (readCount == SocketTask.LETTER_SIZE) {
                        int letter = ByteBuffer.wrap(bytes).getInt();
                        EventQueue.invokeLater(() -> blockArea.append(
                                String.format("'%c'=0x%h\n", (char)letter, letter)
                        ));
                    } else {
                        EventQueue.invokeLater(() -> blockArea.append(
                                "Wrong format\n"
                        ));
                    }
                }
            } catch (IOException exc) {
                EventQueue.invokeLater(() -> blockArea.append(exc.getMessage() + "\n"));
            } finally {
                String message = "Blocking input is closed\n";
                EventQueue.invokeLater(() -> blockArea.append(message));
            }
        });
    }
    
}
