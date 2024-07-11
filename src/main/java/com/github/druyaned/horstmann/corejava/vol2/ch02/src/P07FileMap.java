package com.github.druyaned.horstmann.corejava.vol2.ch02.src;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.horstmann.corejava.util.Stopwatch;
import com.github.druyaned.horstmann.corejava.vol2.ch01.src.Text;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UncheckedIOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;

/**
 * Part 7 of the chapter 2 to practice in files that are mapped in a memory.
 * @author druyaned
 */
public class P07FileMap implements Runnable {
    
    private final Path dataDir;
    
    public P07FileMap(Path dataDir) {
        this.dataDir = dataDir;
    }
    
    @Override public void run() {
        System.out.println("\n" + bold("Part 07 FileMap"));
        // declarations of the directory and files
        Path textPath = Text.TEXT_PATH;
        String outDirName = "p07-file-map";
        Path outDirPath = dataDir.resolve(outDirName);
        final String[] FILE_NAMES = {
            "random-access",
            "file-stream",
            "buffer-stream",
            "channel"
        };
        Path[] filePaths = new Path[FILE_NAMES.length];
        for (int i = 0; i < FILE_NAMES.length; ++i) {
            filePaths[i] = outDirPath.resolve(FILE_NAMES[i]);
        }
        try {
            // creation of the directory, files and a stopwatch
            if (!Files.exists(outDirPath)) {
                Files.createDirectories(outDirPath);
                creationInfo(outDirPath);
            }
            for (int i = 0; i < FILE_NAMES.length; ++i) {
                if (!Files.exists(filePaths[i])) {
                    Files.createFile(filePaths[i]);
                    creationInfo(filePaths[i]);
                }
            }
            Stopwatch stopwatch = new Stopwatch();
            // random-access
            try (
                    RandomAccessFile randomIn = new RandomAccessFile(
                            textPath.toFile(), "r"
                    );
                    BufferedOutputStream bufferOut = new BufferedOutputStream(
                            new FileOutputStream(filePaths[0].toFile())
                    );
            ) {
                stopwatch.start();
                final long RANDOM_N = randomIn.length();
                for (long i = 0; i < RANDOM_N; ++i) {
                    randomIn.seek(i);
                    bufferOut.write(randomIn.readByte());
                }
                Duration spent = stopwatch.stop().getSpent();
                System.out.println(
                        purpleBold(FILE_NAMES[0]) + ": spent=" + spent.toMillis()
                );
            }
            // file-stream
            try (
                    FileInputStream fileIn = new FileInputStream(textPath.toFile());
                    BufferedOutputStream bufferOut = new BufferedOutputStream(
                            new FileOutputStream(filePaths[1].toFile())
                    );
            ) {
                stopwatch.start();
                int b;
                while ((b = fileIn.read()) != -1) {
                    bufferOut.write(b);
                }
                Duration spent = stopwatch.stop().getSpent();
                System.out.println(
                        purpleBold(FILE_NAMES[1]) + ": spent=" + spent.toMillis()
                );
            }
            // buffer-stream
            try (
                    BufferedInputStream bufferIn = new BufferedInputStream(
                            new FileInputStream(textPath.toFile())
                    );
                    BufferedOutputStream bufferOut = new BufferedOutputStream(
                            new FileOutputStream(filePaths[2].toFile())
                    );
            ) {
                stopwatch.start();
                int b;
                while ((b = bufferIn.read()) != -1) {
                    bufferOut.write(b);
                }
                Duration spent = stopwatch.stop().getSpent();
                System.out.println(
                        purpleBold(FILE_NAMES[2]) + ": spent=" + spent.toMillis()
                );
            }
            // channel
            try (
                    FileChannel channel = FileChannel.open(
                            textPath, StandardOpenOption.READ
                    );
                    BufferedOutputStream bufferOut = new BufferedOutputStream(
                            new FileOutputStream(filePaths[3].toFile())
                    );
            ) {
                stopwatch.start();
                MappedByteBuffer byteBuffer = channel.map(
                        FileChannel.MapMode.READ_ONLY, 0, channel.size()
                );
                while (byteBuffer.remaining() > 0) {
                    bufferOut.write(byteBuffer.get());
                }
                Duration spent = stopwatch.stop().getSpent();
                System.out.println(
                        purpleBold(FILE_NAMES[3]) + ": spent=" + spent.toMillis()
                );
                System.out.println(blueBold("ByteBuffer befor rewind") + ":");
                printByteBuffer(byteBuffer);
                byteBuffer.rewind();
                System.out.println(blueBold("ByteBuffer after rewind") + ":");
                printByteBuffer(byteBuffer);
            }
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
    
    private void creationInfo(Path path) {
        System.out.println(
                blueBold(path.toString()) + " was " +
                greenBold("successfully") + " created!"
        );
    }
    
    private static void printByteBuffer(MappedByteBuffer b) {
        System.out.println("  position=" + b.position());
        System.out.println("  limit=" + b.limit());
        System.out.println("  capacity=" + b.capacity());
    }
    
}
