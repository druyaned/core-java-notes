package com.github.druyaned.learn_java.vol1.chapter03;

import static com.github.druyaned.ConsoleColors.*;
import static com.github.druyaned.learn_java.App.APP_IN;
import com.github.druyaned.learn_java.Chapterable;
import com.github.druyaned.learn_java.vol1.Volume1;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import static java.util.Arrays.sort;
import java.util.Date;
import java.util.Scanner;

/**
 * Practice implementation of the chapter 3.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol1.Volume1
 */
public class Chapter03 implements Chapterable {

    private static void cout(Object line) { System.out.print(line); }
    private static final String ENDL = "\n";
    
    private static void testUTF16() {
        // code for ᯼ is 7164 in UTF-16
        String s1 = "᯼ wow!";
        cout("s1: " + s1 + ENDL);
        for (int i = 0; i < s1.length(); ++i) {
            cout("s1.charAt(" + i + ") = " + s1.charAt(i) + ENDL);
        }
        cout(ENDL);
        for (int i = 0; i < s1.length(); ++i) {
            cout("s1.codePointAt(" + i + ") = " + s1.codePointAt(i) + ENDL);
        }
        cout(ENDL);
    }

    private static void testIn() {
        cout("Write smth in a line: ");
        String line = APP_IN.nextLine();
        cout(line + ENDL);
        cout("Now type in 1 number: ");
        if (APP_IN.hasNextInt()) {
            int a = APP_IN.nextInt();
            cout("a = " + a + ENDL);
        }
        else {
            cout("Ops! It wasn't a number." + ENDL);
        }
    }

    private static void testOut() {
        double d = 10000.0 / 3D;
        cout("d:\n" + d + ENDL);
        System.out.printf("d:\n%010.2f\n", d);
        System.out.printf("%tc\n", new Date());
        System.out.printf("%tF; [%1$tT] or [%<tr]\n", new Date());
    }

    private static void testFileIO() {
        Scanner myIn;
        
        Path dataDirPath = Volume1.getDataDirPath();
        Path file1Path = dataDirPath.resolve("chapter03").resolve("text-file1.txt");
        Path file2Path = dataDirPath.resolve("chapter03").resolve("text-file2.txt");
        try {
            Path dirPath = file1Path.getParent();
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
            if (!Files.exists(file1Path)) {
                Files.createFile(file1Path);
            }
            if (!Files.exists(file2Path)) {
                Files.createFile(file2Path);
            }
            Files.writeString(file1Path, "hey yo", StandardOpenOption.WRITE);
            myIn = new Scanner(file1Path, "UTF-8");
            if (myIn.hasNext()) {
                String s1 = myIn.nextLine();
                cout("Line in a file:\n\t" + s1 + ENDL);
            }
            else {
                cout("The file is empty :(\n");
            }
        }
        catch (IOException exc) {
            exc.printStackTrace();
            return;
        }

        try (PrintWriter out = new PrintWriter(file2Path.toString(), "UTF-8")) {
            out.printf("Date: %1$tc\nJust wanna say it's gonna be a great file!\n", new Date());
        }
        catch (IOException exc) {
            exc.printStackTrace();
            return;
        }

        // a little fun with blocks
        {
            int a = 5;
            cout("a = " + a + ENDL);
        }
        {
            int a = 3;
            cout("a = " + a + ENDL);
        }
    }

    // test enum
    private enum Size { SMALL, MEDIUM, LARGE, EXTRA_LARGE };

    private static void testControlLogic() {
        // chance to win in lottery: n*(n-1)*...*(n-k+1)/(1*2*...*k)
        int n = 50, k = 6;
        double chanceToWinInLottery = 1D;
        for (int i = 1; i <= k; ++i) {
            chanceToWinInLottery *= (double)i / (double)(n + 1 - i);
        }
        cout("Chance to win in lottery\nguessing " + k + " numbers from " +
             n + ": " + chanceToWinInLottery + ENDL);

        // test enum
        Size sz = Size.MEDIUM;
        cout("sz = ");
        switch (sz) {
            case SMALL -> cout("SMALL\n");
            case MEDIUM -> cout("MEDIUM\n");
            case LARGE -> cout("LARGE\n");
            case EXTRA_LARGE -> cout("EXTRA_LARGE\n");
            default -> cout("Something was wrong\n");
        }

        // labeled cycle
        StringBuilder sBuilder = new StringBuilder();
        outer:
        for (int i = 2; i < 6; ++i) {
            cout("Showing \" wow\" " + i + " times:\n");
            for (int j = 0; j < i; ++j) {
                if (i == 4) {
                    cout("Wow! 4 times is too much!\n");
                    break outer; // continue can be with a label too
                }
                cout(" wow");
                sBuilder.append(" wow");
            }
            cout(ENDL);
        }
        String sTotal = sBuilder.toString();
        cout("In total output was:\n" + sTotal + ENDL);
    }

    private static void testBigNumbers() {
        cout("Max exponent: " + Double.MAX_EXPONENT + ENDL);
        cout("Max double:\n" + Double.MAX_VALUE + ENDL);

        BigDecimal bd;
        bd = (BigDecimal.valueOf(10)).multiply(BigDecimal.valueOf(Double.MAX_VALUE));
        cout("After multiply bd: " + bd + ENDL);

        BigInteger bi;
        bi = (BigInteger.valueOf(10)).add(BigInteger.valueOf(Integer.MAX_VALUE));
        cout("Max int:\n" + Integer.MAX_VALUE + ENDL);
        cout(bi + ENDL);
    }

    private static void testArrays() {
        int[] a1; // a1[i] = 0; for objects: obj[i] = null
        a1 = new int[] {1, 3, 2}; // anonymous array

        cout("a1:\n");
        for (int x : a1) {
            cout(" " + x);
        }
        cout(ENDL);

        cout("a1:\n");
        showArray(a1);
        cout("a1:\n");
        showArray(a1);

        sort(a1); // Arrays.sort(a1);
        cout("a1:\n");
        showArray(a1);

        // two-dimensional arrays
        final int Y = 2, X = 3;
        int[][] a2 = new int[Y][X];
        cout("a2:\n");
        for (int i = 0; i < a2.length; ++i) {
            for (int j = 0; j < a2[i].length; ++j) {
                a2[i][j] = i + j;
            }
        }
        showArray2D(a2);

        a2 = new int[X+Y][];
        for (int i = 0; i < a2.length; ++i) {
            a2[i] = new int[i + 1];
            for (int j = 0; j < a2[i].length; ++j) {
                a2[i][j] = i + j;
            }
        }
        cout("a2:\n");
        showArray2D(a2);
    }
    private static void showArray(int[] a) {
        for (int i = 0; i < a.length; ++i) {
            a[i] += 1;
            cout(" " + a[i]);
        }
        cout(ENDL);
    }
    private static void showArray2D(int[][] a) {
        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; j < a[i].length; ++j) {
                cout(" " + a[i][j]);
            }
            cout(ENDL);
        }
    }
    
//-Non-static---------------------------------------------------------------------------------------
    
    @Override
    public void run() {
        System.out.println(bold("Running Chapter03: Basic Constructs"));
        
        testUTF16();
        testIn();
        testOut();
        testFileIO();
        testControlLogic();
        testBigNumbers();
        testArrays();
    }

    @Override
    public int getNumber() { return 3; }
    
    @Override
    public String getTitle() { return "Basic Constructs"; }
    
    @Override
    public boolean passed() { return true; }
}
