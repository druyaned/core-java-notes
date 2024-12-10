package druyaned.corejava.util.crypt;

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
 * Password cryptographer.
 * @author druyaned
 */
public class Cryptographer {
    
    private final Charset CHARSET = StandardCharsets.UTF_16;
    private final String ENCRYPTION_SCHEME = "DESede";
    private final Cipher cipher;
    private final SecretKey secretKey;
    
    /**
     * Constructs a cryptographer for passwords.
     * @throws GeneralSecurityException see
     *      {@link SecretKeyFactory#getInstance(java.lang.String)},
     *      {@link Cipher#getInstance(java.lang.String)},
     *      {@link DESedeKeySpec#DESedeKeySpec(byte[])},
     *      {@link SecretKeyFactory#generateSecret(java.security.spec.KeySpec)}
     */
    public Cryptographer() throws GeneralSecurityException {
        SecretKeyFactory skf = SecretKeyFactory.getInstance(ENCRYPTION_SCHEME);
        cipher = Cipher.getInstance(ENCRYPTION_SCHEME);
        String encryptionKey = "WhiteSabbathWhiteSabbathWhiteSabbath";
        KeySpec keySpec = new DESedeKeySpec(encryptionKey.getBytes(CHARSET));
        secretKey = skf.generateSecret(keySpec);
    }
    
    /**
     * Returns encrypted password or <u>null</u> if the encryption failed.
     * @param plain the password to be encrypted
     * @return encrypted password or <u>null</u> if the encryption failed
     * @throws GeneralSecurityException see
     *      {@link Cipher#init(int, java.security.Key)},
     *      {@link Cipher#doFinal(byte[])}
     */
    public String encrypt(char[] plain) throws GeneralSecurityException {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] plainBytes = new String(plain).getBytes(CHARSET);
        byte[] encryptedBytes = cipher.doFinal(plainBytes);
        return new String(Base64.encodeBase64(encryptedBytes)); // encrypted
    }
    
    /**
     * Returns decrypted password or <u>null</u> if the decryption failed.
     * @param encrypted the password to be decrypted
     * @return decrypted password or <u>null</u> if the decryption failed
     * @throws java.security.GeneralSecurityException see
     *      {@link Cipher#init(int, java.security.Key)},
     *      {@link Cipher#doFinal(byte[])}
     */
    public char[] decrypt(String encrypted) throws GeneralSecurityException {
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.decodeBase64(encrypted);
        byte[] plainBytes = cipher.doFinal(encryptedBytes);
        return new String(plainBytes, CHARSET).toCharArray(); // plain
    }
    
}
