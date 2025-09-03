package druyaned.corejava.vol1.ch12gui.t01calc.gui;

import java.awt.GridBagConstraints;

public class GBC extends GridBagConstraints {
    
    public GBC(int gridx, int gridy) {
        this.gridx = gridx;
        this.gridy = gridy;
    }
    
    public GBC(int gridx, int gridy, int gridw, int gridh) {
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridw;
        this.gridheight = gridh;
    }
    
    public GBC setWeight(double weightx, double weighty) {
        // By default weightx=0d, weighty=0d
        this.weightx = weightx;
        this.weighty = weighty;
        return this;
    }
    
    public GBC setSize(int gridwidth, int gridheight) {
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
        return this;
    }
    
    /**
     * @param anchor can be {@code GBC.NORTH}, {@code GBC.NORTHEAST},
     * {@code GBC.EAST}, {@code GBC.SOUTHEAST}, {@code GBC.SOUTH},
     * {@code GBC.SOUTHWEST}, {@code GBC.WEST}, {@code GBC.NORTHWEST}.
     * @return this GBC instance to set more
     */
    public GBC setAnchor(int anchor) {
        this.anchor = anchor;
        return this;
    }
    
    /**
     * @param fill can be {@code GBC.NONE}, {@code GBC.BOTH},
     * {@code GBC.HORIZONTAL}, {@code GBC.VERTICAL}; default NONE.
     * @return this GBC instance to set more
     */
    public GBC setFill(int fill) {
        this.fill = fill;
        return this;
    }
    
    public GBC setInsets(int distance) {
        this.insets.top = distance;
        this.insets.right = distance;
        this.insets.bottom = distance;
        this.insets.left = distance;
        return this;
    }
    
    public GBC setInsets(int top, int right, int bottom, int left) {
        this.insets.top = top;
        this.insets.right = right;
        this.insets.bottom = bottom;
        this.insets.left = left;
        return this;
    }
    
}
