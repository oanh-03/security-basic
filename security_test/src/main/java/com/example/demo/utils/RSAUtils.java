package com.example.demo.utils;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * @author Phuong Oanh
 * Lớp RSAUtils cung cấp các phương thức để mã hóa và giải mã với thuật toán RSA.
 */
public class RSAUtils {

    private static PublicKey PUBLIC_KEY;
    private static PrivateKey PRIVATE_KEY;

    static {
        generateKeyPair();
    }

    /**
     * Tạo một cặp khóa RSA công cộng và riêng tư.
     *
     * Phương thức này sử dụng thuật toán RSA để tạo cặp khóa.
     * Độ dài của cặp khóa được thiết lập là 2048 bit.
     *
     * Khóa công cộng và khóa riêng tư được lưu trữ trong các biến STATIC PUBLIC_KEY và PRIVATE_KEY của lớp.
     */
    public static void generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PUBLIC_KEY = keyPair.getPublic();
            PRIVATE_KEY = keyPair.getPrivate();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Mã hóa một chuỗi sử dụng khóa công cộng RSA.
     *
     * @param data Chuỗi cần được mã hóa.
     * @return Chuỗi đã được mã hóa dưới dạng Base64.
     * @throws Exception
     */
    public static String encrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, PUBLIC_KEY);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Giải mã một chuỗi đã được mã hóa bằng khóa riêng tư RSA.
     *
     * @param encryptedData Chuỗi đã được mã hóa cần được giải mã.
     * @return Chuỗi đã được giải mã.
     * @throws Exception
     */
    public static String decrypt(String encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, PRIVATE_KEY);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

