package com.github.druyaned.corejava.vol2.ch04;

import com.github.druyaned.corejava.Chapter;
import com.github.druyaned.corejava.vol2.ch04.src.P01Socket;
import com.github.druyaned.corejava.vol2.ch04.src.P02Server;
import com.github.druyaned.corejava.vol2.ch04.src.P03Interruptible;
import com.github.druyaned.corejava.vol2.ch04.src.P04URL;
import com.github.druyaned.corejava.vol2.ch04.src.P05POST;
import com.github.druyaned.corejava.vol2.ch04.src.P06Mail;
import com.github.druyaned.corejava.vol2.ch04.src.P07HttpGET;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Practical implementation of the Chapter#04: Networking.
 * @author druyaned
 */
public class Networking extends Chapter {
    
    /**
     * Creates the Chapter#04: Networking.
     * @param volDataDir the path to the volume's data-directory
     */
    public Networking(Path volDataDir) {
        super(volDataDir, 4);
    }
    
    @Override public String getTitle() {
        return "Networking";
    }
    
    @Override public void run() {
        List<Runnable> parts = new ArrayList<>();
        parts.add(new P01Socket());
        parts.add(new P02Server());
        parts.add(new P03Interruptible());
        parts.add(new P04URL());
        parts.add(new P05POST());
        parts.add(new P06Mail(getDataDir()));
        parts.add(new P07HttpGET());
        choosePartAndRun(parts);
    }
    
}
