package druyaned.corejava.vol1.ch14.src.balls;

import druyaned.corejava.vol1.ch14.src.ball.BallImage;
import java.util.Random;

public class ThreadListener implements Runnable {

    public static final int MAX_CIRCLES = 5;
    public static final int MAX_DELTA = 10;
    private static final Random GENERATOR = new Random();

    public static int nextDelta() {
        return GENERATOR.nextInt(MAX_DELTA * 2 + 1) - MAX_DELTA;
    }

    private final BallImage circleImg;
    private boolean isStopped;
    private final int delay;
    private final int xMin, xMax;
    private final int yMin, yMax;
    private int x;
    private int y;
    private int deltaX;
    private int deltaY;

    /** Default: {@code deltaY = -1}, {@code y = yMax}. */
    public ThreadListener(
            BallImage circleImg, int delay, int xMin, int xMax, int yMin, int yMax
    ) {
        this.circleImg = circleImg;
        this.isStopped = false;
        this.delay = delay;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        x = (xMin + xMax) / 2;
        y = yMax;
        deltaX = nextDelta();
        deltaY = -1;
//        circleImg.setXY(x, y);
        circleImg.repaint();
    }

    /** Also invokes {@link BallImage#setXY(int, int)}. */
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
//        circleImg.setXY(x, y);
        circleImg.repaint();
    }

    public void setDeltaY(int newDeltaY) {
        boolean negDY = deltaY < 0;
        boolean negNewDY = newDeltaY < 0;
        if (negDY == negNewDY) {
            deltaY = newDeltaY;
        } else {
            deltaY = -newDeltaY;
        }
    }

    public void setIsStopped() {
        this.isStopped = true;
    }
    
    public void unsetIsStopped() {
        this.isStopped = false;
    }

    @Override public void run() {
        int newY;
        int newX;
        long timeLimit = System.currentTimeMillis() + 300000;
        while (System.currentTimeMillis() < timeLimit) {
            if (isStopped) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            newY = y + deltaY;
            newX = x + deltaX;
            if (newY > yMax || newY < yMin) {
                deltaY *= -1;
                y += deltaY;
                deltaX = nextDelta();
            } else {
                y = newY;
            }
            if (newX > xMax || newX < xMin) {
                deltaX *= -1;
                x += deltaX;
            } else {
                x = newX;
            }
//            circleImg.setXY(x, y);
            circleImg.repaint();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
