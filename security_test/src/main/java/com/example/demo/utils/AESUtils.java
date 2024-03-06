package com.example.demo.utils;

import org.springframework.context.annotation.Configuration;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author Phuong Oanh
 *  Lớp AESUtils cung cấp các phương thức để mã hóa và giải mã với thuật toán AES.
 */
@Configuration
public class AESUtils {

    private static final String AES_SECRET_KEY = "this-is-test-key";

    /**
     * Tạo một khóa AES với thuật toán và kích thước khóa được chỉ định.
     *
     * @param algorithm Loại thuật toán sẽ được sử dụng để tạo khóa, ví dụ: "AES".
     * @param keySize Kích thước của khóa được tạo, ví dụ: 128, 192 hoặc 256.
     * @return Khóa AES được tạo ra.
     * @throws Exception
     */
    public static SecretKey generateAESKey(String algorithm, int keySize) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(keySize);
        return keyGenerator.generateKey();
    }

    /**
     * Tạo một khóa AES mặc định với thuật toán AES và kích thước khóa 256 bit.
     *
     * @return Khóa AES được tạo ra.
     * @throws Exception
     */
    public static SecretKey generateAESKey() throws Exception {
        return generateAESKey("AES", 256);
    }

    /**
     * Mã hóa một chuỗi sử dụng thuật toán AES với khóa được xác định.
     *
     * @param plaintext Chuỗi cần được mã hóa.
     * @return Chuỗi đã được mã hóa dưới dạng Base64.
     * @throws Exception
     */
    public static String encrypt(String plaintext) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(AES_SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    /**
     * Giải mã một chuỗi đã được mã hóa sử dụng thuật toán AES với khóa được xác định.
     *
     * @param ciphertext Chuỗi đã được mã hóa cần được giải mã.
     * @param secretKey Khóa AES được sử dụng để giải mã.
     * @return Chuỗi đã được giải mã.
     * @throws Exception
     */

    public static String decrypt(String ciphertext, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
