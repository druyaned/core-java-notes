package druyaned.corejava.vol2.ch02io;

import static druyaned.ConsoleColors.blueBold;
import static druyaned.ConsoleColors.purpleBold;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.util.FileUtil;
import druyaned.corejava.util.Stopwatch;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UncheckedIOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;

public class T07FileMap extends Topic {
    
    private final Path textPath;
    
    public T07FileMap(Chapter chapter, Path textPath) {
        super(chapter, 7);
        this.textPath = textPath;
    }
    
    @Override public String title() {
        return "Topic 07 FileMap";
    }
    
    @Override public void run() {
        // declarations of the directory and files
        String outDirName = "file-map";
        Path outDirPath = dataDir().resolve(outDirName);
        final String[] FILE_NAMES = {
            "random-access",
            "file-stream",
            "buffer-stream",
            "channel"
        };
        Path[] filePaths = new Path[FILE_NAMES.length];
        for (int i = 0; i < FILE_NAMES.length; ++i)
            filePaths[i] = outDirPath.resolve(FILE_NAMES[i]);
        try {
            // create files and a stopwatch
            for (int i = 0; i < FILE_NAMES.length; ++i)
                FileUtil.createFileOnDemand(filePaths[i]);
            Stopwatch stopwatch = new Stopwatch();
            // random-access
            try (
                    RandomAccessFile randomIn = new RandomAccessFile(textPath.toFile(), "r");
                    FileOutputStream fileOut = new FileOutputStream(filePaths[0].toFile());
                    BufferedOutputStream bufferOut = new BufferedOutputStream(fileOut);
            ) {
                stopwatch.start();
                final long RANDOM_N = randomIn.length();
                for (long i = 0; i < RANDOM_N; ++i) {
                    randomIn.seek(i);
                    bufferOut.write(randomIn.readByte());
                }
                Duration spent = stopwatch.stop().getSpent();
                System.out.println(purpleBold(FILE_NAMES[0]) + ": spent=" + spent.toMillis());
            }
            // file-stream
            try (
                    FileInputStream fileIn = new FileInputStream(textPath.toFile());
                    FileOutputStream fileOut = new FileOutputStream(filePaths[1].toFile());
                    BufferedOutputStream bufferOut = new BufferedOutputStream(fileOut);
            ) {
                stopwatch.start();
                int b;
                while ((b = fileIn.read()) != -1)
                    bufferOut.write(b);
                Duration spent = stopwatch.stop().getSpent();
                System.out.println(purpleBold(FILE_NAMES[1]) + ": spent=" + spent.toMillis());
            }
            // buffer-stream
            try (
                    FileInputStream fileIn = new FileInputStream(textPath.toFile());
                    BufferedInputStream bufferIn = new BufferedInputStream(fileIn);
                    FileOutputStream fileOut = new FileOutputStream(filePaths[2].toFile());
                    BufferedOutputStream bufferOut = new BufferedOutputStream(fileOut);
            ) {
                stopwatch.start();
                int b;
                while ((b = bufferIn.read()) != -1)
                    bufferOut.write(b);
                Duration spent = stopwatch.stop().getSpent();
                System.out.println(purpleBold(FILE_NAMES[2]) + ": spent=" + spent.toMillis());
            }
            // channel
            try (
                    FileChannel channel = FileChannel.open(textPath, StandardOpenOption.READ);
                    FileOutputStream fileOut = new FileOutputStream(filePaths[3].toFile());
                    BufferedOutputStream bufferOut = new BufferedOutputStream(fileOut);
            ) {
                stopwatch.start();
                MappedByteBuffer byteBuffer = channel.map(
                        FileChannel.MapMode.READ_ONLY,
                        0,
                        channel.size()
                );
                while (byteBuffer.remaining() > 0)
                    bufferOut.write(byteBuffer.get());
                Duration spent = stopwatch.stop().getSpent();
                System.out.println(purpleBold(FILE_NAMES[3]) + ": spent=" + spent.toMillis());
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
    
    private void printByteBuffer(MappedByteBuffer b) {
        System.out.println("  position=" + b.position());
        System.out.println("  limit=" + b.limit());
        System.out.println("  capacity=" + b.capacity());
    }
    
}
