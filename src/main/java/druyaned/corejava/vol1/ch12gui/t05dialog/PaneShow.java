package druyaned.corejava.vol1.ch12gui.t05dialog;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

public class PaneShow extends PaneStyled {
    
    public PaneShow(ShowListener showListener, JTextField prevIn) {
        super("Show button");
        setLayout(new GridLayout(2, 1));
        JButton showButton = new JButton("Show");
        showButton.addActionListener(showListener);
        Font showFont = new Font(Font.MONOSPACED, Font.PLAIN, 32);
        showButton.setFont(showFont);
        add(showButton);
        add(prevIn);
    }
    
}
