package druyaned.corejava.vol1.ch12gui.t05dialog;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.JOptionPane.showOptionDialog;
import javax.swing.JTextField;

public class ShowListener implements ActionListener {
    
    private final PaneType typePane;
    private final PaneIcon iconPane;
    private final PaneMessageType messageTypePane;
    private final PaneConfirm confirmPane;
    private final PaneTitle titlePane;
    private final JTextField prevIn;
    
    public ShowListener(
            PaneType typePane,
            PaneIcon iconPane,
            PaneMessageType messageTypePane,
            PaneConfirm confirmPane,
            PaneTitle titlePane,
            JTextField prevOut
    ) {
        this.typePane = typePane;
        this.iconPane = iconPane;
        this.messageTypePane = messageTypePane;
        this.confirmPane = confirmPane;
        this.titlePane = titlePane;
        this.prevIn = prevOut;
    }
    
    @Override public void actionPerformed(ActionEvent event) {
        String iconKey = iconPane.getSelectedKey();
        ImageIcon imageIcon = null;
        if (!iconKey.equals(Icons.NONE)) {
            imageIcon = iconPane.icons().map().get(iconKey);
        }
        String title = titlePane.getTitle();
        int option = confirmPane.getSelectedOption();
        int messageType = messageTypePane.getSelectedMessageType();
        if (typePane.isSelected(PaneType.CONFIRM)) {
            String message = "Are we confirming something?";
            Component parentComponent = null;
            int in;
            in = showConfirmDialog(
                    parentComponent,
                    message,
                    title,
                    option,
                    messageType,
                    imageIcon
            );
            prevIn.setText("Previous input: " + getOption(in));
        } else if (typePane.isSelected(PaneType.INPUT)) {
            String message = "Let's do some input!";
            String[] toChoose = new String[] { "Variant A", "Variant B" };
            Object in = showInputDialog(
                    null,
                    message,
                    title,
                    messageType,
                    imageIcon,
                    toChoose,
                    toChoose[0]
            );
            prevIn.setText("Previous input: " + in);
        } else if (typePane.isSelected(PaneType.MESSAGE)) {
            String message = "Here is some kind of message.";
            showMessageDialog(null, message, title, messageType, imageIcon);
            prevIn.setText("Hmm... Previous input couldn't be seen.");
        } else if (typePane.isSelected(PaneType.OPTION)) {
            String message = "Here is some kind of message.";
            String[] toChoose = new String[] { "Variant 1", "Variant 2", "Variant 3" };
            int in = showOptionDialog(
                    null,
                    message,
                    title,
                    option,
                    messageType,
                    imageIcon,
                    toChoose,
                    toChoose[0]
            );
            if (in == -1) {
                prevIn.setText("Previous input: CLOSE_OPTION");
            } else {
                prevIn.setText("Previous input: " + toChoose[in]);
            }
        }
    }
    
    public static String getOption(int in) {
        return switch (in) {
            case JOptionPane.YES_OPTION -> "YES_OPTION"; // YES_OPTION == OK_OPTION == 0
            case JOptionPane.NO_OPTION -> "NO_OPTION";
            case JOptionPane.CANCEL_OPTION -> "CANCEL_OPTION";
            default -> "DEFAULT_OPTION";
        };
    }
    
}
