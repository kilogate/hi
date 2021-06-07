package com.kilogate.hi.util;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA 密码工具类
 * <p>
 * <p>
 * 使用 openssl 生成公私密钥对
 * 1 生成私钥
 * openssl genrsa -out rsa_private_key_pkcs1.pem 1024
 * 2 根据私钥生成公钥
 * openssl rsa -in rsa_private_key_pkcs1.pem -pubout -out rsa_public_key.pub
 * 3 将私钥转换成 pkcs8 格式
 * openssl pkcs8 -topk8 -inform PEM -in rsa_private_key_pkcs1.pem -outform PEM -nocrypt > rsa_private_key.pem
 *
 * @author kilogate
 * @create 2020/12/29 23:12
 **/
public class RsaCipherUtil {
    /**
     * RSA 加密
     */
    private static String encrypt(String data, Key key) {
        if (data == null || data.length() == 0 || key == null) {
            return null;
        }

        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);

        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            // 1024 位密钥最多支持 117 字节明文加密，超过则分段加密
            int length = bytes.length;
            if (length < 128) {
                return Base64Util.doEncode(cipher.doFinal(bytes));
            }

            // 分段加密
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            int offset = 0;
            byte[] buffer;

            while (length > offset) {
                if (length - offset > 117) {
                    buffer = cipher.doFinal(bytes, offset, 117);
                } else {
                    buffer = cipher.doFinal(bytes, offset, length - offset);
                }

                output.write(buffer, 0, buffer.length);

                offset += 117;
            }

            return Base64Util.doEncode(output.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * RSA 解密
     */
    private static String decrypt(String data, Key key) {
        if (data == null || data.length() == 0 || key == null) {
            return null;
        }

        byte[] bytes = Base64Util.doDecode(data);

        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, key);

            // 1024 位密钥最多支持 128 字节密文解密，超过则分段解密
            int length = bytes.length;
            if (length < 128) {
                return Base64Util.doEncode(cipher.doFinal(bytes));
            }

            // 分段解密
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            int offset = 0;
            byte[] buffer;

            while (length > offset) {
                if (length - offset > 128) {
                    buffer = cipher.doFinal(bytes, offset, 128);
                } else {
                    buffer = cipher.doFinal(bytes, offset, length - offset);
                }

                output.write(buffer, 0, buffer.length);

                offset += 128;
            }

            return new String(output.toByteArray(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * RSA 签名
     */
    public static String sign(String data, PrivateKey privateKey, String algorithm) {
        try {
            Signature signature = Signature.getInstance(algorithm);
            signature.initSign(privateKey);
            signature.update(data.getBytes(StandardCharsets.UTF_8));
            return Base64Util.doEncode(signature.sign());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * RSA 验签
     */
    public static boolean verify(String data, String sign, PublicKey publicKey, String algorithm) {
        try {
            Signature signature = Signature.getInstance(algorithm);
            signature.initVerify(publicKey);
            signature.update(data.getBytes(StandardCharsets.UTF_8));
            return signature.verify(Base64Util.doDecode(sign));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取公钥
     */
    public static PublicKey getPublicKey(String base64PublicKey) {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64Util.doDecode(base64PublicKey));

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取私钥
     */
    public static PrivateKey getPrivateKey(String base64PrivateKey) {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64Util.doDecode(base64PrivateKey));

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void generateAndPrintKeyPair() {
        // 密钥生成器
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return;
        }

        // 密钥长度：1024 位
        keyPairGenerator.initialize(1024, new SecureRandom());

        // 生成公私密钥对（公钥：x.509 格式，私钥：pkcs8 格式）
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        System.out.println("========== public key start ==========");
        System.out.println(Base64Util.doEncode(publicKey.getEncoded()));
        System.out.println("========== public key end ==========");

        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("========== private key start ==========");
        System.out.println(Base64Util.doEncode(privateKey.getEncoded()));
        System.out.println("========== private key end ==========");
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        // 生成并打印公私密钥对
//        generateAndPrintKeyPair();

        String base64PublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCf6Gkx4j1cqOLNBATZd3tn2aICDCvYkLCOAa6itJEEiY3aduU8C1WeqQBkLM4FJ1D1Zeuf57stm1TiDW0zDpn27pjp1c2jno5c8W4g+5RAQm1ApP5hID7AgzkY7JLvm5DCyclE67jGKd5pX4GvBSiNbQPVlmShHJqD4VslpqHu/wIDAQAB";

        String base64PrivateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJ/oaTHiPVyo4s0EBNl3e2fZogIMK9iQsI4BrqK0kQSJjdp25TwLVZ6pAGQszgUnUPVl65/nuy2bVOINbTMOmfbumOnVzaOejlzxbiD7lEBCbUCk/mEgPsCDORjsku+bkMLJyUTruMYp3mlfga8FKI1tA9WWZKEcmoPhWyWmoe7/AgMBAAECgYBVQxbUAk21mldT+VCco5ipRuGbNeIrFvsMvNjg6GZqhO2eDscrvN9qObJ7t2giV1RThISePnEPYENiRMZ08tQke8FDjhmE6lA0sJP6Ez1a5JoHCU1RUeI+zOCyfLl+4NdbzugHvox8R5vakTlulyz6evj5OxWaQR4aX88BRuy0AQJBAOv9DBo4o6tAtaBBeIV2gkjHw9/tDUYxnvqAp8PcNyZD+4XjHx+kM76JL9iVIIlnJBDlpABhI9G/8OTvrwUgX2MCQQCtd8TFNxZthbCwSlpXs+jcqHMiOPxqFXLFcS3lWArJEFHEzwHhf/rULocHPxP89cuHWGtYA81NQQ3YA5oamuq1AkABY9IobWD5Zz4qnXgaQSJvT03mCb1kRnKLQyq5yphAj3Hxze6m/w6W9rPjcK1qYcMinlei8O+HErxWfNrpCtHFAkAZ01PzPJFVOuskQ92RYxKPD3Ecrjk+HfVZWdFIbMQmTQyvbVZNWT1hq4+kz4pNRdbITqykjORa1Q2XlGmz/k5lAkAFelHPOaWZ6grDbVGuTtHglXIvtoSz6BX6L5TNNb6Wm/bnmAKDazVR2imuW0P1Brz872xldg7o4T/q9k1Y7MPx";

        // 获取公私密钥
        PublicKey publicKey = getPublicKey(base64PublicKey);
        PrivateKey privateKey = getPrivateKey(base64PrivateKey);

        // 待加密数据
        String data = "一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十";

        System.out.println("========== 使用公钥加密私钥解密 ==========");

        // 使用公钥加密
        String encrypt = encrypt(data, publicKey);
        System.out.println(encrypt);

        // 使用私钥解密
        String decrypt = decrypt(encrypt, privateKey);
        System.out.println(decrypt);

        System.out.println("========== 使用私钥加密公钥解密 ==========");

        // 使用私钥加密
        encrypt = encrypt(data, privateKey);
        System.out.println(encrypt);

        // 使用公钥解密
        decrypt = decrypt(encrypt, publicKey);
        System.out.println(decrypt);

        System.out.println("========== 使用私钥签名公钥验签 ==========");

        /**
         * 常用签名算法
         * SHA1WithRSA
         * SHA256WithRSA
         * MD5withRSA
         */
        String algorithm = "SHA1WithRSA";

        // 使用私钥签名
        String sign = sign(data, privateKey, algorithm);
        System.out.println(sign);

        // 使用公钥验签
        boolean verify = verify(data, sign, publicKey, algorithm);
        System.out.println(verify);
    }
}
