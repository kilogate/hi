package com.kilogate.hi.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * AES 加密工具类
 *
 * @author kilogate
 * @create 2020/12/27 23:39
 **/
public class AesCipherUtil {
    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
        String data = "哈喽";
        String seed = "种子";

        String encrypt = aesEncrypt(data, seed);
        System.out.println(encrypt);

        String decrypt = aesDecrypt(encrypt, seed);
        System.out.println(decrypt);

        Key randomKey = generateRandomKey("AES");
        System.out.println(randomKey);
    }

    public static String aesEncrypt(String data, String seed) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        if (data == null || data.length() == 0 || seed == null || seed.length() == 0) {
            return null;
        }

        // 生成密钥
        Key key = generateKey(seed, "AES");
        if (key == null) {
            return null;
        }

        // 获取加密对象
        Cipher cipher = Cipher.getInstance("AES");
        if (cipher == null) {
            return null;
        }

        // 初始化加密对象
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // 转换数据
        byte[] encrypt = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

        // base64编码
        return Base64Util.doEncode(encrypt);
    }

    public static String aesDecrypt(String encrypt, String seed) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        if (encrypt == null || encrypt.length() == 0 || seed == null || seed.length() == 0) {
            return null;
        }

        // 生成密钥
        Key key = generateKey(seed, "AES");
        if (key == null) {
            return null;
        }

        // 获取加密对象
        Cipher cipher = Cipher.getInstance("AES");
        if (cipher == null) {
            return null;
        }

        // 初始化加密对象
        cipher.init(Cipher.DECRYPT_MODE, key);

        // base64解码
        byte[] in = Base64Util.doDecode(encrypt);

        // 转换数据
        byte[] decrypt = cipher.doFinal(in);

        return new String(decrypt, StandardCharsets.UTF_8);
    }

    private static Key generateKey(String seed, String algorithm) {
        if (seed == null || seed.length() == 0) {
            return null;
        }

        byte[] key = MessageDigestUtil.md5(seed);

        return new SecretKeySpec(key, algorithm);
    }

    private static Key generateRandomKey(String algorithm) {
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (keyGenerator == null) {
            return null;
        }

        keyGenerator.init(new SecureRandom());

        return keyGenerator.generateKey();
    }
}
