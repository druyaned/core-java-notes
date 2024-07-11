package com.github.druyaned.horstmann.corejava.vol1.ch12;

import com.github.druyaned.horstmann.corejava.Chapter;
import com.github.druyaned.horstmann.corejava.vol1.ch12.src.calc.TestCalculator;
import com.github.druyaned.horstmann.corejava.vol1.ch12.src.dialog.TestDialog;
import com.github.druyaned.horstmann.corejava.vol1.ch12.src.gbc.TestGridBag;
import com.github.druyaned.horstmann.corejava.vol1.ch12.src.menu.TestMenu;
import com.github.druyaned.horstmann.corejava.vol1.ch12.src.text.TestText;
import com.github.druyaned.horstmann.corejava.vol1.ch12.src.user.TestUser;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Practice implementation of the Chapter#12: GUI.
 * @author druyaned
 */
public class GUI extends Chapter {
    
    /**
     * Creates the Chapter#12: GUI.
     * @param volDataDir the path to the volume's data-directory
     */
    public GUI(Path volDataDir) {
        super(volDataDir, 12);
    }
    
    @Override public String getTitle() {
        return "GUI";
    }
    
    @Override public void run() {
        List<Runnable> parts = new ArrayList<>();
        parts.add(new TestCalculator());
        parts.add(new TestText());
        parts.add(new TestMenu());
        parts.add(new TestGridBag());
        parts.add(new TestDialog());
        parts.add(new TestUser(getDataDir()));
        choosePartAndRun(parts);
    }
    
}
