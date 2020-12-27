package com.kilogate.hi.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息摘要工具类
 *
 * @author kilogate
 * @create 2020/12/27 17:32
 **/
public class MessageDigestUtil {
    public static void main(String[] args) {
        System.out.println("MD5");
        System.out.println(md5("哈哈"));
        System.out.println();

        System.out.println("SHA-1");
        System.out.println(sha1("哈哈"));
        System.out.println();

        System.out.println("SHA-256");
        System.out.println(sha256("哈哈"));
        System.out.println();

        System.out.println("SHA-384");
        System.out.println(sha384("哈哈"));
        System.out.println();

        System.out.println("SHA-512");
        System.out.println(sha512("哈哈"));
        System.out.println();
    }

    public static String md5(String data) {
        return digest(data, "MD5");
    }

    public static String sha1(String data) {
        return digest(data, "SHA-1");
    }

    public static String sha256(String data) {
        return digest(data, "SHA-256");
    }

    public static String sha384(String data) {
        return digest(data, "SHA-384");
    }

    public static String sha512(String data) {
        return digest(data, "SHA-512");
    }

    private static String digest(String data, String algorithm) {
        if (data == null || data.length() == 0 || algorithm == null || algorithm.length() == 0) {
            return null;
        }

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (messageDigest == null) {
            return null;
        }

        byte[] input = data.getBytes(StandardCharsets.UTF_8);

        byte[] digest = messageDigest.digest(input);

        return ByteArrayUtil.byteArrayToHexString(digest);
    }
}
