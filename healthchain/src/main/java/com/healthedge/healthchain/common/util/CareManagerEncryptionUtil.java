package com.healthedge.healthchain.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.util.Base64;


/**
 * Encrypts and decrypts any string by using the 3DES algorithm.
 *
 * @author Karoly_Olah
 */
@Component
public class CareManagerEncryptionUtil {
    /**
     * The magic.
     */
    public static final String MAGIC = "[3DES]";
    /**
     * 8-byte Salt.
     */
    private static final byte[] SALT =
            {(byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03};
    /**
     * Iteration count.
     */
    private static final int ITERATIONCOUNT = 19;
    /**
     * Log4J logger instance.
     */
    private static Logger LOG = LoggerFactory.getLogger(CareManagerEncryptionUtil.class);
    /**
     * The encipher instance.
     */
    private Cipher ecipher;

    /**
     * The decipher instance.
     */
    private Cipher dcipher;

    /**
     * The pass phrase used for (en|de)cryption.
     */
    private String passPhrase;

    /**
     * Constructor.
     */
    public CareManagerEncryptionUtil() {
        super();
        initialize();
    }

    /**
     * Initializes the engine.
     */
    private void initialize() {
        //TODO: kRoy use an installation-specific passphrase here. (f.ex embeddedLDAP's credential number)
        this.passPhrase = "P655PHR6z34P6s5w0rd43ncr1pti0n";

        try {
            // Create the key
            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), SALT, ITERATIONCOUNT);
            SecretKey key = SecretKeyFactory.getInstance("PBEWITHMD5ANDDES").generateSecret(keySpec);
            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());

            // Prepare the parameter to the ciphers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT, ITERATIONCOUNT);

            // Create the ciphers
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        } catch (java.security.InvalidAlgorithmParameterException e) {
            LOG.error("Error initializing crypto engine", e);
            throw new RuntimeException(e);
        } catch (java.security.spec.InvalidKeySpecException e) {
            LOG.error("Error initializing crypto engine", e);
            throw new RuntimeException(e);
        } catch (javax.crypto.NoSuchPaddingException e) {
            LOG.error("Error initializing crypto engine", e);
            throw new RuntimeException(e);
        } catch (java.security.NoSuchAlgorithmException e) {
            LOG.error("Error initializing crypto engine", e);
            throw new RuntimeException(e);
        } catch (java.security.InvalidKeyException e) {
            LOG.error("Error initializing crypto engine", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Encrypts the given string.
     *
     * @param str the string to be encrypted.
     * @return the DES-encrypted and BASE64-encoded string or null if fails.
     * @throws RuntimeException
     */
    public String encrypt(String str) {
        String result = null;

        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            result = MAGIC + new String(Base64.getEncoder().encode(enc));
        } catch (javax.crypto.BadPaddingException e) {
            LOG.error("Error encrypting password", e);
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            LOG.error("Error encrypting password", e);
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            LOG.error("Error encrypting password", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * Decrypts the given string.
     *
     * @param str the the DES-encrypted and BASE64-encoded string.
     * @return the decrypted string or null if fails.
     * @throws RuntimeException
     */
    public String decrypt(String str) {
        String result = null;

        try {
            String encryptedString = str;

            if (encryptedString.startsWith(MAGIC)) {
                encryptedString = encryptedString.substring(MAGIC.length(), encryptedString.length());

                // Decode base64 to get bytes

                byte[] dec = Base64.getDecoder().decode(encryptedString.getBytes());

                // Decrypt
                byte[] utf8 = dcipher.doFinal(dec);

                // Decode using utf-8
                result = new String(utf8, "UTF8");
            } else {
                result = str;
            }
        } catch (javax.crypto.BadPaddingException e) {
            LOG.error("Error decrypting password", e);
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            LOG.error("Error decrypting password", e);
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            LOG.error("Error decrypting password", e);
            throw new RuntimeException(e);
        }

        return result;
    }



}
