package druyaned.corejava.vol2;

import druyaned.corejava.App;
import druyaned.corejava.Book;
import druyaned.corejava.Volume;
import druyaned.corejava.vol2.ch01streamapi.C01StreamAPI;
import druyaned.corejava.vol2.ch02io.C02InputOutput;
import druyaned.corejava.vol2.ch03xml.C03XML;
import druyaned.corejava.vol2.ch04network.C04Networking;
import druyaned.corejava.vol2.ch05db.C05Databases;
import druyaned.corejava.vol2.ch06datetime.C06DateTimeAPI;
import druyaned.corejava.vol2.ch07international.C07Internationalization;
import druyaned.corejava.vol2.ch08anno.C08ScriptCompileAnnotate;
import druyaned.corejava.vol2.ch09security.C09Security;
import druyaned.corejava.vol2.ch10swing.C10AdvancedSwing;
import druyaned.corejava.vol2.ch11awt.C11AdvancedAWT;
import druyaned.corejava.vol2.ch12native.C12NativeMethods;

/**
 * The second volume of the book.
 * @author druyaned
 * @see App
 */
public final class Volume2 extends Volume {
    
    /**
     * Creates the second volume of the book.
     * @param book to which the volume belongs
     */
    public Volume2(Book book) {
        super(book, 2);
        chapters().add(new C01StreamAPI(this));
        chapters().add(new C02InputOutput(this));
        chapters().add(new C03XML(this));
        chapters().add(new C04Networking(this));
        chapters().add(new C05Databases(this));
        chapters().add(new C06DateTimeAPI(this));
        chapters().add(new C07Internationalization(this));
        chapters().add(new C08ScriptCompileAnnotate(this));
        chapters().add(new C09Security(this));
        chapters().add(new C10AdvancedSwing(this));
        chapters().add(new C11AdvancedAWT(this));
        chapters().add(new C12NativeMethods(this));
    }
    
    @Override public String title() {
        return "Volume 2: Advanced Features";
    }
    
}
