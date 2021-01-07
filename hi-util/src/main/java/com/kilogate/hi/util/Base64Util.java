package com.kilogate.hi.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * base64 工具类
 *
 * @author kilogate
 * @create 2020/12/28 00:04
 **/
public class Base64Util {
    /**
     * base64编码
     */
    public static String encode(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        return doEncode(data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * base64编码
     */
    public static String doEncode(byte[] data) {
        if (data == null || data.length == 0) {
            return null;
        }

        return Base64.getEncoder().encodeToString(data);
    }

    /**
     * base64解码
     */
    public static String decode(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        return new String(doDecode(data), StandardCharsets.UTF_8);
    }

    /**
     * base64解码
     */
    public static byte[] doDecode(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        return Base64.getDecoder().decode(data);
    }

    /**
     * URL安全的base64编码
     */
    public static String urlEncode(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        return doUrlEncode(data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * URL安全的base64编码
     */
    public static String doUrlEncode(byte[] data) {
        if (data == null || data.length == 0) {
            return null;
        }

        return Base64.getUrlEncoder().encodeToString(data);
    }

    /**
     * URL安全的base64解码
     */
    public static String urlDecode(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        return new String(doUrlDecode(data), StandardCharsets.UTF_8);
    }

    /**
     * URL安全的base64解码
     */
    public static byte[] doUrlDecode(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        return Base64.getUrlDecoder().decode(data);
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        String data = "这是测试数据";

        // base64编码
        String encode = encode(data);
        System.out.println(encode);

        // base64解码
        String decode = decode(encode);
        System.out.println(decode);

        // URL安全的base64编码
        String urlEncode = urlEncode(data);
        System.out.println(urlEncode);

        // URL安全的base64解码
        String urlDecode = urlDecode(urlEncode);
        System.out.println(urlDecode);
    }
}
