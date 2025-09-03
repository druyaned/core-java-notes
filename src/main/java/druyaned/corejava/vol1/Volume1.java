package druyaned.corejava.vol1;

import druyaned.corejava.App;
import druyaned.corejava.Book;
import druyaned.corejava.Volume;
import druyaned.corejava.vol1.ch01intro.C01Introduction;
import druyaned.corejava.vol1.ch02progenv.C02ProgEnvironment;
import druyaned.corejava.vol1.ch03constr.C03Constructs;
import druyaned.corejava.vol1.ch04obj.C04ObjectsClasses;
import druyaned.corejava.vol1.ch05inherit.C05Inheritance;
import druyaned.corejava.vol1.ch06interface.C06InterfacesInternalClasses;
import druyaned.corejava.vol1.ch07exclog.C07ExceptionsLogging;
import druyaned.corejava.vol1.ch08generics.C08Generics;
import druyaned.corejava.vol1.ch09collections.C09Collections;
import druyaned.corejava.vol1.ch10graphics.C10Graphics;
import druyaned.corejava.vol1.ch11events.C11EventHandling;
import druyaned.corejava.vol1.ch12gui.C12GUI;
import druyaned.corejava.vol1.ch13deploy.C13AppDeploying;
import druyaned.corejava.vol1.ch14concurrency.C14Concurrency;

/**
 * The first volume of the book.
 * @author druyaned
 * @see App
 */
public final class Volume1 extends Volume {
    
    /**
     * Creates the first volume of the book.
     * @param book to which the volume belongs
     */
    public Volume1(Book book) {
        super(book, 1);
        chapters().add(new C01Introduction(this));
        chapters().add(new C02ProgEnvironment(this));
        chapters().add(new C03Constructs(this));
        chapters().add(new C04ObjectsClasses(this));
        chapters().add(new C05Inheritance(this));
        chapters().add(new C06InterfacesInternalClasses(this));
        chapters().add(new C07ExceptionsLogging(this));
        chapters().add(new C08Generics(this));
        chapters().add(new C09Collections(this));
        chapters().add(new C10Graphics(this));
        chapters().add(new C11EventHandling(this));
        chapters().add(new C12GUI(this));
        chapters().add(new C13AppDeploying(this));
        chapters().add(new C14Concurrency(this));
    }
    
    @Override public String title() {
        return "Volume 1: Fundamentals";
    }
    
}
