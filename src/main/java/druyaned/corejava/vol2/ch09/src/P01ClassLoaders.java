package druyaned.corejava.vol2.ch09.src;

import static druyaned.ConsoleColors.*;

public class P01ClassLoaders implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Running P01 Socket"));
        ClassLoader mathLoader = java.lang.Math.class.getClassLoader();
        ClassLoader sqlLoader = java.sql.SQLPermission.class.getClassLoader();
        ClassLoader appLoader = P01ClassLoaders.class.getClassLoader();
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
