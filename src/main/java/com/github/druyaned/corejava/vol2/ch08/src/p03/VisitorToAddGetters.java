package com.github.druyaned.corejava.vol2.ch08.src.p03;

import static java.lang.Character.toUpperCase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import static org.objectweb.asm.Opcodes.ACC_PRIVATE;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.DRETURN;
import static org.objectweb.asm.Opcodes.FRETURN;
import static org.objectweb.asm.Opcodes.GETFIELD;
import static org.objectweb.asm.Opcodes.IRETURN;
import static org.objectweb.asm.Opcodes.LRETURN;
import org.objectweb.asm.Type;
import static org.objectweb.asm.Type.BOOLEAN_TYPE;
import static org.objectweb.asm.Type.BYTE_TYPE;
import static org.objectweb.asm.Type.CHAR_TYPE;
import static org.objectweb.asm.Type.DOUBLE_TYPE;
import static org.objectweb.asm.Type.FLOAT_TYPE;
import static org.objectweb.asm.Type.INT_TYPE;
import static org.objectweb.asm.Type.LONG_TYPE;
import static org.objectweb.asm.Type.SHORT_TYPE;
import static org.objectweb.asm.Type.getMethodDescriptor;
import static org.objectweb.asm.Type.getType;

/**
 * Adds getters into a file with the extension {@code .class}
 * after compilation of a given class.
 * 
 * @author druyaned
 */
public class VisitorToAddGetters extends ClassVisitor {
    
    private static class Field {
        private final int access;
        private final String descriptor;
        private final String name;
        public Field(int access, String descriptor, String name) {
            this.access = access;
            this.descriptor = descriptor;
            this.name = name;
        }
    }
    
    private static boolean isI(String descriptor) {
        if (descriptor.equals(BOOLEAN_TYPE.getDescriptor())) {
            return true;
        } else if (descriptor.equals(BYTE_TYPE.getDescriptor())) {
            return true;
        } else if (descriptor.equals(CHAR_TYPE.getDescriptor())) {
            return true;
        } else if (descriptor.equals(SHORT_TYPE.getDescriptor())) {
            return true;
        } else if (descriptor.equals(INT_TYPE.getDescriptor())) {
            return true;
        }
        return false;
    }
    
    private final String owner;
    private final List<Field> fields = new ArrayList<>();
    private final Map<String, String> methods = new HashMap<>();
    
    /**
     * Constructs a visitor to add getters into a file with the extension {@code .class}
     * after compilation of a given class.
     * 
     * @param cl a class to add getters
     * @param cv should be an instance of {@link org.objectweb.asm.ClassWriter ClassWriter}
     */
    public VisitorToAddGetters(Class<?> cl, ClassVisitor cv) {
        super(org.objectweb.asm.Opcodes.ASM9, cv);
        this.owner = Type.getInternalName(cl);
        this.cv = cv;
    }
    
    @Override public FieldVisitor visitField(
            int access, String name, String descriptor,
            String signature, Object value
    ) {
        fields.add(new Field(access, descriptor, name));
        return cv.visitField(access, name, descriptor, signature, value);
    }
    
    @Override public MethodVisitor visitMethod(
            int access, String name, String descriptor,
            String signature, String[] exceptions
    ) {
        methods.put(name, descriptor);
        return cv.visitMethod(access, name, descriptor, signature, exceptions);
    }
    
    @Override public void visitEnd() {
        for (Field field : fields) {
            String fieldName = field.name;
            String fieldDescriptor = field.descriptor;
            String postfix = toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            String methodName = "get" + postfix;
            String methodDescriptor = methods.get(methodName);
            if (
                    methodDescriptor == null &&
                    (field.access & ACC_PRIVATE) == ACC_PRIVATE
            ) {
                methodDescriptor = getMethodDescriptor(getType(fieldDescriptor));
                MethodVisitor mv = cv.visitMethod(
                        ACC_PUBLIC, methodName, methodDescriptor,
                        null, null
                );
                mv.visitCode();
                mv.visitVarInsn(ALOAD, 0);
                mv.visitFieldInsn(GETFIELD, owner, fieldName, fieldDescriptor);
                if (fieldDescriptor.equals(LONG_TYPE.getDescriptor())) {
                    mv.visitInsn(LRETURN);
                    mv.visitMaxs(2, 2);
                } else if (fieldDescriptor.equals(DOUBLE_TYPE.getDescriptor())) {
                    mv.visitInsn(DRETURN);
                    mv.visitMaxs(2, 2);
                } else if (fieldDescriptor.equals(FLOAT_TYPE.getDescriptor())) {
                    mv.visitInsn(FRETURN);
                    mv.visitMaxs(1, 1);
                } else if (isI(fieldDescriptor)) {
                    mv.visitInsn(IRETURN);
                    mv.visitMaxs(1, 1);
                } else {
                    mv.visitInsn(ARETURN);
                    mv.visitMaxs(1, 1);
                }
                mv.visitEnd();
            }
        }
        cv.visitEnd();
    }
    
}
