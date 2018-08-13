package com.gree.airconditioner.util;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;


public class CryptoUtil {
    static String AES_General_Key = "a3K8Bx%2r8Y7#xDh";

    public static String getAESGeneralKey() {
        return AES_General_Key;
    }

    public static byte[] getAESGeneralKeyByteArray() {
        return AES_General_Key.getBytes();
    }

    public static String decryptPack(String message) {
        return decryptPack(getAESGeneralKeyByteArray(), message);
    }

    public static String decryptPack(byte[] keyarray, String message) {
        String descrytpedMessage = null;
        try {
            Key key = new SecretKeySpec(keyarray, "AES");
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] imageByte = decoder.decode(message);

            Cipher aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.DECRYPT_MODE, key);
            byte[] bytePlainText = aesCipher.doFinal(imageByte);

            descrytpedMessage = new String(bytePlainText);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return descrytpedMessage;
    }

    public static String encryptPack(String message) {
        return encryptPack(getAESGeneralKeyByteArray(), message);
    }

    public static String encryptPack(byte[] keyarray, String message) {
        String encrytpedMessage = null;
        try {
            Key key = new SecretKeySpec(keyarray, "AES");
            Cipher aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] bytePlainText = aesCipher.doFinal(message.getBytes());

            Base64.Encoder newencoder = Base64.getEncoder();
            encrytpedMessage = new String(newencoder.encode(bytePlainText));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return encrytpedMessage;
    }
}
