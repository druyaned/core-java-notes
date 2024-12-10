package druyaned.corejava.vol1.ch14.src.ball;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BallButtonPane extends JPanel {
    
    public static final int H = BallFrame.H / 8;
    public static final Color BACK_COLOR = new Color(224, 255, 224);
    
    public BallButtonPane(BallPane ballPane) {
        setBackground(BACK_COLOR);
        setPreferredSize(new Dimension(BallFrame.W, H));
        add(ballPane.getStop());
        add(ballPane.getStart());
        JLabel stepXLabel = new JLabel("StepX");
        JLabel stepYLabel = new JLabel("StepY");
        stepXLabel.setHorizontalAlignment(JLabel.RIGHT);
        stepYLabel.setHorizontalAlignment(JLabel.RIGHT);
        add(stepXLabel);
        add(ballPane.getStepXSlider());
        add(stepYLabel);
        add(ballPane.getStepYSlider());
    }
    
}
