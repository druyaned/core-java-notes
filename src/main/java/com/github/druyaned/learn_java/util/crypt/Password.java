package com.github.druyaned.learn_java.util.crypt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.GeneralSecurityException;

/**
 * Provides a creation of the encrypted password to store in the file
 * and its extraction by decryption.
 * 
 * @author druyaned
 */
public class Password {
    
    /**
     * Returns a decrypted password from the provided file ({@code passPath})
     * where the password is stored and which is encrypted.
     * 
     * @param passPath the file where the password is stored and which is encrypted.
     * @param passFor a brief (1 - 3 words) description of what is password-protected
     *        to create the file with the encrypted password;
     *        <u>can be a null</u> if the the file has already been created.
     * @return a decrypted password from the provided file ({@code passPath})
     *         where the password is stored and which is encrypted.
     * @throws java.io.IOException if an I/O error occurs.
     * @throws java.security.GeneralSecurityException see {@link Cryptographer#Cryptographer},
     *         {@link Cryptographer#encrypt} and {@link Cryptographer#decrypt}.
     */
    public static String get(Path passPath, String passFor)
            throws IOException, GeneralSecurityException {
        
        if (!Files.exists(passPath)) {
            Path dirPath = passPath.getParent();
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
            Files.createFile(passPath);
            String message = String.format("Password for \"%s\": ", passFor);
            String pass = new String(System.console().readPassword(message));
            Cryptographer cryptographer = new Cryptographer();
            Files.writeString(passPath, cryptographer.encrypt(pass), StandardOpenOption.WRITE);
        }
        String encrypted = Files.readString(passPath);
        return new Cryptographer().decrypt(encrypted);
    }
}
