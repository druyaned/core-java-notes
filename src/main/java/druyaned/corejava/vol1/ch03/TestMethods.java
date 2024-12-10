package druyaned.corejava.vol1.ch03;

import druyaned.corejava.App;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class TestMethods {
    
    public static void testAll(Path chapterDataDir) {
        testUTF16();
        testIn();
        testOut();
        testFileIO(chapterDataDir);
        testControlLogic();
        testBigNumbers();
        testArrays();
    }
    
    public static void testUTF16() {
        System.out.println();
        String s = "\u1bfc wow!";
        System.out.println("s: " + s);
        for (int i = 0; i < s.length(); i++) {
            System.out.println("s.charAt(" + i + ") = " + s.charAt(i));
        }
        System.out.println();
        for (int i = 0; i < s.length(); i++) {
            System.out.printf("s.codePointAt(%d) = %x\n", i, s.codePointAt(i));
        }
    }

    public static void testIn() {
        System.out.println();
        System.out.print("Write smth in a line: ");
        String line = App.sin.nextLine();
        System.out.println("input: '" + line + "'");
        System.out.print("Now type in only one number: ");
        if (App.sin.hasNextInt()) {
            int a = App.sin.nextInt();
            System.out.println("a = " + a);
        } else {
            System.out.println("Ops! It wasn't a number.");
        }
        App.sin.nextLine(); // clean up the input stream
    }

    public static void testOut() {
        System.out.println();
        double d = 10_000.0 / 3d;
        System.out.println("d: " + d);
        System.out.printf("d: %010.2f\n", d);
        System.out.printf("date: %tc\n", new Date());
        System.out.printf("%tF; [%1$tT] or [%<tr]\n", new Date());
    }

    public static void testFileIO(Path chapterDataDir) {
        System.out.println();
        Path file1Path = chapterDataDir.resolve("text-file-1.txt");
        Path file2Path = chapterDataDir.resolve("text-file-2.txt");
        try {
            if (!Files.exists(file1Path)) {
                Files.createFile(file1Path);
            }
            if (!Files.exists(file2Path)) {
                Files.createFile(file2Path);
            }
            Files.writeString(file1Path, "Hey yo!\n", StandardOpenOption.WRITE);
            Scanner fin = new Scanner(file1Path, "UTF-8");
            if (fin.hasNext()) {
                String line = fin.nextLine();
                System.out.println("Line in a file: '" + line + "'");
            } else {
                System.out.println("No lines in the file.");
            }
        } catch (IOException exc) {
            exc.printStackTrace();
            return;
        }
        try (PrintWriter out = new PrintWriter(file2Path.toString(), "UTF-8")) {
            out.printf("Date: %1$tc\n", new Date());
            out.print("Just wanna say that's gonna be a great file!\n");
        } catch (IOException exc) {
            exc.printStackTrace();
            return;
        }
        { // a little fun with blocks
            int a = 5;
            System.out.println("a = " + a);
        }
        {
            int a = 3;
            System.out.println("a = " + a);
        }
    }

    private enum Size { SMALL, MEDIUM, LARGE, EXTRA_LARGE }; // to test the enum

    public static void testControlLogic() {
        System.out.println();
        // chance to win in lottery: n*(n-1)*...*(n-k+1)/(1*2*...*k)
        int n = 50, k = 6;
        double chanceToWinInLottery = 1d;
        for (int i = 1; i <= k; i++) {
            chanceToWinInLottery *= (double)i / (double)(n + 1 - i);
        }
        System.out.println("Chance to win in lottery");
        System.out.printf("Guessing %d numbers from %d: %f\n", k, n, chanceToWinInLottery);
        // test enum
        Size size = Size.MEDIUM;
        System.out.print("Testing the enum. Size=" + size + ": ");
        switch (size) {
            case SMALL -> System.out.println(size);
            case MEDIUM -> System.out.println("Medium");
            case LARGE -> System.out.println("Large");
            case EXTRA_LARGE -> System.out.println("Extra large");
            default -> System.out.println("Something went wrong");
        }
        // labeled cycle
        StringBuilder builder = new StringBuilder();
        outer: for (int i = 2; i < 6; i++) {
            System.out.println("Showing \"wow\" " + i + " times:");
            for (int j = 0; j < i; ++j) {
                if (i == 4) {
                    System.out.println(" Wow! 4 times is too much!");
                    break outer; // continue can be with a label too
                }
                System.out.print(" wow");
                builder.append(" wow");
            }
            System.out.println();
        }
        String total = builder.toString();
        System.out.println("Total output is: " + total);
    }

    public static void testBigNumbers() {
        System.out.println();
        System.out.println("Max exponent: " + Double.MAX_EXPONENT);
        System.out.println("Max double:   " + Double.MAX_VALUE);
        BigDecimal bigDecimal;
        bigDecimal = BigDecimal.valueOf(10).multiply(BigDecimal.valueOf(Double.MAX_VALUE));
        System.out.println("After multiplication the bigDecimal is " + bigDecimal);
        BigInteger bigInteger;
        bigInteger = BigInteger.valueOf(10).add(BigInteger.valueOf(Integer.MAX_VALUE));
        System.out.println("MaxInteger: " + Integer.MAX_VALUE);
        System.out.println("BigInteger: " + bigInteger);
    }

    public static void testArrays() {
        System.out.println();
        int[] a1; // a1[i] = 0; for objects: obj[i] = null
        a1 = new int[] {1, 3, 2}; // anonymous array
        System.out.print("a1:");
        for (int x : a1) {
            System.out.print(" " + x);
        }
        System.out.println();
        System.out.print("a1:");
        showArray(a1);
        System.out.print("a1:");
        showArray(a1);
        Arrays.sort(a1);
        System.out.print("a1:");
        showArray(a1);
        // two-dimensional arrays
        final int Y = 2, X = 3;
        int[][] a2 = new int[Y][X];
        for (int i = 0; i < a2.length; i++) {
            for (int j = 0; j < a2[i].length; ++j) {
                a2[i][j] = i + j;
            }
        }
        System.out.println("a2:");
        showArray2D(a2);
        a2 = new int[X+Y][];
        for (int i = 0; i < a2.length; i++) {
            a2[i] = new int[i + 1];
            for (int j = 0; j < a2[i].length; ++j) {
                a2[i][j] = i + j;
            }
        }
        System.out.println("a2:");
        showArray2D(a2);
    }
    
    private static void showArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] += 1;
            System.out.print(" " + a[i]);
        }
        System.out.println();
    }
    
    private static void showArray2D(int[][] arr) {
        for (int[] a : arr) {
            System.out.print(a[0]);
            for (int j = 1; j < a.length; j++) {
                System.out.print(" " + a[j]);
            }
            System.out.println();
        }
    }
    
}
