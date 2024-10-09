package com.github.druyaned.corejava.vol1.ch14.src.ball;

import static com.github.druyaned.corejava.vol1.ch14.src.ball.BallImage.DIAMETER;
import java.util.Random;

public class BallPosition {
    
    public static final int MAX_STEP = 15;
    public static final int DEFAULT_STEP = MAX_STEP / 2 + 1;
    public static final int MAX_DEVIATION = 3;
    public static final int X_MAX = BallFrame.W - DIAMETER;
    public static final int Y_MAX = BallPane.H - DIAMETER;
    public static final int DELAY = 16; // 1 / 60 = 0,016666666666667 = 16.(6) * 10^{-3}
    private static final Random GENERATOR = new Random();
    
    private volatile int stepX, stepY;
    private volatile int x, y;
    
    public BallPosition() {
        stepY = DEFAULT_STEP;
        stepX = GENERATOR.nextBoolean() ? DEFAULT_STEP : -DEFAULT_STEP;
        x = (BallFrame.W - DIAMETER) / 2;
        y = BallPane.H - DIAMETER;
    }
    
    public final int getStepX() {
        return (stepX < 0) ? -stepX : stepX;
    }
    
    public final int getStepY() {
        return (stepY < 0) ? -stepY : stepY;
    }
    
    public final int getX() {
        return x;
    }
    
    public final int getY() {
        return y;
    }
    
    public final synchronized void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public final synchronized void setSteps(int absXValue, int absYValue) {
        stepX = (stepX < 0 ? -1 : 1) * absXValue;
        stepY = (stepY < 0 ? -1 : 1) * absYValue;
    }
    
    /**
     * Resets the position and steps along the X and Y axes.
     * @see BallsFrame
     */
    public final synchronized void makeStep() {
        int newY = y + stepY;
        if (newY < 0) { // stepY is negative
            newY = -newY;
            stepY = -stepY;
        } else if (newY > Y_MAX) { // stepY is positive
            newY = 2 * Y_MAX - newY;
            stepY = -stepY;
        }
        y = newY;
        int newX = x + stepX;
        if (newX < 0) { // stepX is negative
            newX = -newX;
            stepX = -stepX;
        } else if (newX > X_MAX) { // stepX is positive
            newX = 2 * X_MAX - newX;
            stepX = -stepX;
        }
        x = newX;
    }
    
}
