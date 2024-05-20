package academic.planner.utils;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

@Component
public class EncryptionManager {

    public String digestSH512(String dataToDigest) {
        return hashWithSpecificDigestAlgorithmic(dataToDigest, "SHA-512");
    }

    public String digest(String value, String salt) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] passBytes = (salt+value).getBytes();
        md.reset();
        byte[] digested = md.digest(passBytes);
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<digested.length;i++){
            sb.append(Integer.toHexString(0xff & digested[i]));
        }
        String res = sb.toString();

        if (res.length() > 30){
            res = res.substring(0,30);
        }
        return res;
    }

    public byte[] encrypt(byte[] keyBytes, byte[] ivBytes, byte[] mes)  {
        byte[] encryptedBytes = null;
        try {
            AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = null;
            cipher = Cipher.getInstance("AES/CBC/ISO10126Padding");
            cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
            encryptedBytes = cipher.doFinal(mes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedBytes;
    }

    public byte[] decrypt(byte[] keyBytes, byte[] ivBytes, byte[] bytes) {
        byte[] decryptedBytes = null;
        try {
            AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/ISO10126Padding");
            cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
            decryptedBytes = cipher.doFinal(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedBytes;
    }

    //------------------------------------------------------------------------------------------------------------------------------
    // PROTECTED FUNCTIONS
    //------------------------------------------------------------------------------------------------------------------------------

    protected String hashWithSpecificDigestAlgorithmic(String input, String hash) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        // digest() method is called
        // to calculate message digest of the input string
        // returned as array of byte
        byte[] messageDigest = md.digest(input.getBytes());

        // Convert byte array into signum representation
        BigInteger no = new BigInteger(1, messageDigest);

        // Convert message digest into hex value
        String hashtext = no.toString(16);

        // Add preceding 0s to make it 32 bit
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }

        // return the HashText
        return hashtext;
    }
}
