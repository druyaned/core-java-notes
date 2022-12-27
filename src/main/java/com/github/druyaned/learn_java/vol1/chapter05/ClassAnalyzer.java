package com.github.druyaned.learn_java.vol1.chapter05;

import static com.github.druyaned.ConsoleColors.*;
import java.lang.reflect.*;

public class ClassAnalyzer {
    
    public static void run(Class<?> c) throws NullPointerException, SecurityException {
        Class<?> cl = c;
        if (cl == null) {
            throw new NullPointerException("Given class is a null");
        }
        
        // Print class
        int modifier = cl.getModifiers();
        if (modifier != 0) {
            System.out.print(Modifier.toString(modifier) + " ");
        }
        System.out.print("class " + cl.getName());
        Class<?> clSuper = cl.getSuperclass();
        if (clSuper != null) {
            System.out.print(" extends " + clSuper.getName());
        }
        Class<?>[] clInterfaces = cl.getInterfaces();
        if (clInterfaces.length > 0) {
            System.out.print(" implements " + clInterfaces[0].getName());
        }
        for (int i = 1; i < clInterfaces.length; ++i) {
            System.out.print(", " + clInterfaces[i].getName());
        }

        System.out.println(" {");
        
        // Print fields
        Field[] fields = cl.getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            String fieldType = fields[i].getType().getName();
            modifier = fields[i].getModifiers();
            String fieldMod = Modifier.toString(modifier);
            System.out.println("\t" + fieldMod + " " + fieldType + " " +
                               fields[i].getName() + ";");
        }
        
        // Print constructors
        Constructor<?>[] constructors = cl.getDeclaredConstructors();
        if (constructors.length > 0) {
            System.out.println();
        }
        for (int i = 0; i < constructors.length; ++i) {
            modifier = constructors[i].getModifiers();
            String constrMod = Modifier.toString(modifier);
            Class<?>[] paramTypes = constructors[i].getParameterTypes();
            System.out.print("\t" + constrMod + " " + constructors[i].getName() + "(");
            if (paramTypes.length > 0) {
                System.out.print(paramTypes[0].getName());
                for (int j = 1; j < paramTypes.length; ++j) {
                    System.out.print(", " + paramTypes[j].getName());
                }
            }
            System.out.println(");");
        }
        // Print methods
        Method[] methods = cl.getDeclaredMethods();
        if (methods.length > 0) {
            System.out.println();
        }
        for (int i = 0; i < methods.length; ++i) {
            modifier = methods[i].getModifiers();
            String methMod = Modifier.toString(modifier);
            Class<?>[] paramTypes = methods[i].getParameterTypes();
            Class<?> returnType = methods[i].getReturnType();
            System.out.print("\t" + methMod + " " + returnType.getName() +
                             " " + methods[i].getName()+"(");
            if (paramTypes.length > 0) {
                System.out.print(paramTypes[0].getName());
                for (int j = 1; j < paramTypes.length; ++j) {
                    System.out.print(", " + paramTypes[j].getName());
                }
            }
            System.out.println(");");
        }
        
        // The end
        System.out.println("}");
    }

    public static void showClass(Class<?> c) throws NullPointerException, SecurityException {
        if (c.getSimpleName().length() != 0) {
            System.out.println("\nShowing " + bold(c.getSimpleName()));
        } else {
            System.out.println("\nShowing " + bold(c.getName()));
        }
        ClassAnalyzer.run(c);
    }
}
