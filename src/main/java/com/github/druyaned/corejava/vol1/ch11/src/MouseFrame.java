package com.github.druyaned.corejava.vol1.ch11.src;

import static com.github.druyaned.corejava.vol1.ch11.src.ActionFrame.*;
import javax.swing.*;

public class MouseFrame extends JFrame {
    
    private final boolean[][] bits = new boolean[FRAME_W][FRAME_H];

    public MouseFrame() {
        setTitle("MouseFrame");
        setBounds(FRAME_X, FRAME_Y, FRAME_W, FRAME_H);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(new MouseComponent(this));
        pack(); // not too necessary
    }

    /**
     * Sets width and height bits of the frame.
     * @param xFrom x index to start setting (including).
     * @param xTo x index to end setting (excluding).
     * @param yFrom y index to start setting (including).
     * @param yTo y index to end setting (excluding).
     */
    public void setBits(int xFrom, int xTo, int yFrom, int yTo) {
        for (int i = xFrom; i <= xTo; ++i) {
            for (int j = yFrom; j <= yTo; ++j) {
                bits[i][j] = true;
            }
        }
    }

    /** Unsets width and height bits of the frame. */
    public void unsetBits() {
        for (int i = 0; i < FRAME_W; ++i) {
            for (int j = 0; j < FRAME_H; ++j) {
                bits[i][j] = false;
            }
        }
    }

    public boolean isSet(int x, int y) {
        return bits[x][y];
    }
    
}
