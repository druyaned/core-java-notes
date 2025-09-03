package druyaned.corejava.vol1.ch12gui.t05dialog;

import static druyaned.corejava.vol1.ch12gui.t05dialog.Icons.MESSAGE;
import static druyaned.corejava.vol1.ch12gui.t05dialog.Icons.NONE;
import static druyaned.corejava.vol1.ch12gui.t05dialog.Icons.WARNING;

public class PaneIcon extends PaneButton {
    
    private final Icons icons;
    
    public PaneIcon(Icons paneIcons) {
        super(
                "Icon",
                paneIcons.buttons().get(NONE).getModel(),
                paneIcons.buttons(),
                new String[] {MESSAGE, WARNING, NONE}
        );
        this.icons = paneIcons;
    }
    
    public String getSelectedKey() {
        if (isSelected(MESSAGE)) {
            return MESSAGE;
        } else if (isSelected(WARNING)) {
            return WARNING;
        } else {
            return NONE;
        }
    }
    
    public Icons icons() {
        return icons;
    }
    
}
