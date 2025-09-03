package druyaned.corejava.vol2.ch09security;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;

public class T01ClassLoaders extends Topic {
    
    public T01ClassLoaders(Chapter chapter) {
        super(chapter, 1);
    }
    
    @Override public String title() {
        return "Topic 01 ClassLoaders";
    }
    
    @Override public void run() {
        ClassLoader mathLoader = java.lang.Math.class.getClassLoader();
        ClassLoader sqlLoader = java.sql.SQLPermission.class.getClassLoader();
        ClassLoader appLoader = T01ClassLoaders.class.getClassLoader();
        System.out.println("mathLoader=" + loaderToString(mathLoader));
        System.out.println(" sqlLoader=" + loaderToString(sqlLoader));
        System.out.println(" appLoader=" + loaderToString(appLoader));
        printPackages(sqlLoader);
        printPackages(appLoader);
    }
    
    private String loaderToString(ClassLoader cl) {
        if (cl == null)
            return "[bootstrap class loader]";
        else
            return cl.getName() + '[' + cl.getClass() + ']';
    }
    
    private void printPackages(ClassLoader cl) {
        System.out.println(loaderToString(cl) + ".getDefinedPackages:");
        for (Package pack : cl.getDefinedPackages())
            System.out.println("  " + pack);
    }
    
}
