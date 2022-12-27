package com.github.druyaned.learn_java.vol2.chapter02;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.vol2.Volume2;
import com.github.druyaned.learn_java.util.Stopwatch;
import com.github.druyaned.learn_java.vol2.chapter01.Text;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Part 7 of the chapter 2 to practice in files that are mapped in a memory.
 * 
 * @author druyaned
 */
public class P07FileMap {
    
    public static void run() {
        System.out.println("\n" + bold("Part 07 FileMap"));
        
        // declarations of the directory and files
        Path textPath = Text.getTextPath();
        String outDirName = "p07-file-map";
        Path outDirPath = Volume2.getDataDirPath().resolve("chapter02").resolve(outDirName);
        final String[] FILE_NAMES = { "random-access", "file-stream", "buffer-stream", "channel" };
        final int N = FILE_NAMES.length;
        Path[] filePaths = new Path[N];
        for (int i = 0; i < N; ++i)
            filePaths[i] = outDirPath.resolve(FILE_NAMES[i]);
        
        try {
            
            // creation of the directory, files and a stopwatch
            if (!Files.exists(outDirPath))
                Files.createDirectories(outDirPath);
            for (int i = 0; i < N; ++i)
                if (!Files.exists(filePaths[i]))
                    Files.createFile(filePaths[i]);
            Stopwatch stopwatch = new Stopwatch();
            
            // random-access
            try (RandomAccessFile randomIn = new RandomAccessFile(textPath.toFile(), "r");
                 FileOutputStream fileOut = new FileOutputStream(filePaths[0].toFile());
                 BufferedOutputStream bufferOut = new BufferedOutputStream(fileOut)) {
                
                stopwatch.start();
                final long RANDOM_N = randomIn.length();
                for (long i = 0; i < RANDOM_N; ++i) {
                    randomIn.seek(i);
                    bufferOut.write(randomIn.readByte());
                }
                Duration spent = stopwatch.stop().getSpent();
                System.out.println(blueBold(FILE_NAMES[0]) + ": spent=" + spent);
            }
            
            // file-stream
            try (FileInputStream fileIn = new FileInputStream(textPath.toFile());
                 FileOutputStream fileOut = new FileOutputStream(filePaths[1].toFile());
                 BufferedOutputStream bufferOut = new BufferedOutputStream(fileOut)) {
                
                stopwatch.start();
                int b;
                while ((b = fileIn.read()) != -1)
                    bufferOut.write(b);
                Duration spent = stopwatch.stop().getSpent();
                System.out.println(blueBold(FILE_NAMES[1]) + ": spent=" + spent);
            }
            
            // buffer-stream
            try (FileInputStream fileIn = new FileInputStream(textPath.toFile());
                 BufferedInputStream bufferIn = new BufferedInputStream(fileIn);
                 FileOutputStream fileOut = new FileOutputStream(filePaths[2].toFile());
                 BufferedOutputStream bufferOut = new BufferedOutputStream(fileOut)) {
                
                stopwatch.start();
                int b;
                while ((b = bufferIn.read()) != -1)
                    bufferOut.write(b);
                Duration spent = stopwatch.stop().getSpent();
                System.out.println(blueBold(FILE_NAMES[2]) + ": spent=" + spent);
            }
            
            // channel
            try (FileChannel channel = FileChannel.open(textPath, StandardOpenOption.READ);
                 FileOutputStream fileOut = new FileOutputStream(filePaths[3].toFile());
                 BufferedOutputStream bufferOut = new BufferedOutputStream(fileOut)) {
                
                stopwatch.start();
                MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_ONLY,
                                                          0, channel.size());
                while (byteBuffer.remaining() > 0)
                    bufferOut.write(byteBuffer.get());
                Duration spent = stopwatch.stop().getSpent();
                System.out.println(blueBold(FILE_NAMES[3]) + ": spent=" + spent);
                
                System.out.println(blueBold("ByteBuffer befor rewind") + ":");
                printByteBuffer(byteBuffer);
                byteBuffer.rewind();
                System.out.println(blueBold("ByteBuffer after rewind") + ":");
                printByteBuffer(byteBuffer);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(P07FileMap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void printByteBuffer(MappedByteBuffer b) {
        System.out.println("  position=" + b.position());
        System.out.println("  limit=" + b.limit());
        System.out.println("  capacity=" + b.capacity());
    }
}
