package druyaned.corejava.vol1.ch03constr;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.util.Arrays;

public class T07Arrays extends Topic {
    
    public T07Arrays(Chapter chapter) {
        super(chapter, 7);
    }
    
    @Override public String title() {
        return "Topic 07 Arrays";
    }
    
    @Override public void run() {
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
    
    private void showArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] += 1;
            System.out.print(" " + a[i]);
        }
        System.out.println();
    }
    
    private void showArray2D(int[][] arr) {
        for (int[] a : arr) {
            System.out.print(a[0]);
            for (int j = 1; j < a.length; j++) {
                System.out.print(" " + a[j]);
            }
            System.out.println();
        }
    }
    
}
