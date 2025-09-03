package druyaned.corejava.vol1.ch03constr;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;

public class T05ControlLogic extends Topic {
    
    public T05ControlLogic(Chapter chapter) {
        super(chapter, 5);
    }
    
    @Override public String title() {
        return "Topic 05 Control Logic";
    }
    
    @Override public void run() {
        // chance to win in lottery: n*(n-1)*...*(n-k+1)/(1*2*...*k)
        int n = 50, k = 6;
        double chanceToWinInLottery = 1d;
        for (int i = 1; i <= k; i++) {
            chanceToWinInLottery *= (double)i / (double)(n + 1 - i);
        }
        System.out.println("Chance to win in lottery");
        System.out.printf("Guessing %d numbers from %d: %.12f\n", k, n, chanceToWinInLottery);
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
    
    private enum Size {SMALL, MEDIUM, LARGE, EXTRA_LARGE};
    
}
