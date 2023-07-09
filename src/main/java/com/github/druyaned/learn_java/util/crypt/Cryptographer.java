package com.github.druyaned.learn_java.util.crypt;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 * A password cryptographer.
 * 
 * @author druyaned
 */
public class Cryptographer {
    private final Charset CHARSET = StandardCharsets.UTF_16;
    private final String ENCRYPTION_SCHEME = "DESede";
    private final Cipher cipher;
    private final SecretKey secretKey;
    
    /**
     * Constructs a cryptographer for passwords.
     * 
     * @throws GeneralSecurityException if
     *         no Provider supports a SecretKeyFactorySpi
     *         implementation for the specified algorithm, or if
     *         transformation is null, empty, in an invalid format, or if
     *         no Provider supports a CipherSpi implementation for the specified algorithm.
     * @throws IOException if the given key material is shorter than 24 bytes.
     */
    public Cryptographer() throws GeneralSecurityException, IOException {
        SecretKeyFactory skf = SecretKeyFactory.getInstance(ENCRYPTION_SCHEME);
        cipher = Cipher.getInstance(ENCRYPTION_SCHEME);
        String encryptionKey = "WhiteSabbathWhiteSabbathWhiteSabbath";
        KeySpec keySpec = new DESedeKeySpec(encryptionKey.getBytes(CHARSET));
        secretKey = skf.generateSecret(keySpec);
    }
    
    /**
     * Returns encrypted password or <u>null</u> if the encryption failed.
     * 
     * @param plain the password to be encrypted.
     * @return encrypted password or <u>null</u> if the encryption failed.
     * @throws java.security.GeneralSecurityException TODO
     */
    public String encrypt(String plain) throws GeneralSecurityException {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] plainBytes = plain.getBytes(CHARSET);
        byte[] encryptedBytes = cipher.doFinal(plainBytes);
        return new String(Base64.encodeBase64(encryptedBytes)); // encrypted
    }
    
    /**
     * Returns decrypted password or <u>null</u> if the decryption failed.
     * 
     * @param encrypted the password to be decrypted.
     * @return decrypted password or <u>null</u> if the decryption failed.
     * @throws java.security.GeneralSecurityException TODO
     */
    public String decrypt(String encrypted) throws GeneralSecurityException {
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.decodeBase64(encrypted);
        byte[] plainBytes = cipher.doFinal(encryptedBytes);
        return new String(plainBytes, CHARSET); // plain
    }
}
