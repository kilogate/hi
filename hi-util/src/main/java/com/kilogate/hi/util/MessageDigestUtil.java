package com.kilogate.hi.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 消息摘要工具类
 *
 * @author kilogate
 * @create 2020/12/27 17:32
 **/
public class MessageDigestUtil {
    public static void main(String[] args) {
        System.out.println("MD5");
        System.out.println(Arrays.toString(md5("哈哈")));
        System.out.println();

        System.out.println("MD5");
        System.out.println(md5ToHexString("哈哈"));
        System.out.println();

        System.out.println("SHA-1");
        System.out.println(sha1ToHexString("哈哈"));
        System.out.println();

        System.out.println("SHA-256");
        System.out.println(sha256ToHexString("哈哈"));
        System.out.println();

        System.out.println("SHA-384");
        System.out.println(sha384ToHexString("哈哈"));
        System.out.println();

        System.out.println("SHA-512");
        System.out.println(sha512ToHexString("哈哈"));
        System.out.println();
    }

    public static byte[] md5(String message) {
        return digest(message, "MD5");
    }

    public static String md5ToHexString(String message) {
        return digestToHexString(message, "MD5");
    }

    public static String sha1ToHexString(String message) {
        return digestToHexString(message, "SHA-1");
    }

    public static String sha256ToHexString(String message) {
        return digestToHexString(message, "SHA-256");
    }

    public static String sha384ToHexString(String message) {
        return digestToHexString(message, "SHA-384");
    }

    public static String sha512ToHexString(String message) {
        return digestToHexString(message, "SHA-512");
    }

    private static String digestToHexString(String message, String algorithm) {
        byte[] digest = digest(message, algorithm);

        if (digest == null || digest.length == 0) {
            return null;
        }

        return ByteArrayUtil.byteArrayToHexString(digest);
    }

    private static byte[] digest(String message, String algorithm) {
        if (message == null || message.length() == 0 || algorithm == null || algorithm.length() == 0) {
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

        byte[] input = message.getBytes(StandardCharsets.UTF_8);

        byte[] digest = messageDigest.digest(input);

        return digest;
    }
}
