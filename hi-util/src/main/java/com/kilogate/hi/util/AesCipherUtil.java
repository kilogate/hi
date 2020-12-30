package com.kilogate.hi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * AES 密码工具类
 *
 * @author kilogate
 * @create 2020/12/27 23:39
 **/
public class AesCipherUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(AesCipherUtil.class);

    /**
     * AES 加密
     */
    public static String encrypt(String data, String seed) {
        if (data == null || data.length() == 0 || seed == null || seed.length() == 0) {
            return null;
        }

        // 获取密钥
        Key key = getKeyBySeed(seed);
        if (key == null) {
            return null;
        }

        // 获取密码
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("encrypt error", e);
            return null;
        } catch (NoSuchPaddingException e) {
            LOGGER.error("encrypt error", e);
            return null;
        }

        // 初始化密码
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            LOGGER.error("encrypt error", e);
            return null;
        }

        // 转换数据
        byte[] encrypt;
        try {
            encrypt = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        } catch (IllegalBlockSizeException e) {
            LOGGER.error("encrypt error", e);
            return null;
        } catch (BadPaddingException e) {
            LOGGER.error("encrypt error", e);
            return null;
        }

        // base64编码
        return Base64Util.doEncode(encrypt);
    }

    /**
     * AES 解密
     */
    public static String decrypt(String encrypt, String seed) {
        if (encrypt == null || encrypt.length() == 0 || seed == null || seed.length() == 0) {
            return null;
        }

        // 获取密钥
        Key key = getKeyBySeed(seed);
        if (key == null) {
            return null;
        }

        // 获取密码
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("decrypt error", e);
            return null;
        } catch (NoSuchPaddingException e) {
            LOGGER.error("decrypt error", e);
            return null;
        }

        // 初始化密码
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            LOGGER.error("decrypt error", e);
            return null;
        }

        // base64解码
        byte[] in = Base64Util.doDecode(encrypt);

        // 转换数据
        byte[] decrypt;
        try {
            decrypt = cipher.doFinal(in);
        } catch (IllegalBlockSizeException e) {
            LOGGER.error("decrypt error", e);
            return null;
        } catch (BadPaddingException e) {
            LOGGER.error("decrypt error", e);
            return null;
        }

        return new String(decrypt, StandardCharsets.UTF_8);
    }

    private static Key getKeyBySeed(String seed) {
        if (seed == null || seed.length() == 0) {
            return null;
        }

        byte[] key = MessageDigestUtil.md5(seed);

        return new SecretKeySpec(key, "AES");
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        String data = "哈喽";
        String seed = "种子";

        String encrypt = encrypt(data, seed);
        System.out.println(encrypt);

        String decrypt = decrypt(encrypt, seed);
        System.out.println(decrypt);
    }
}
