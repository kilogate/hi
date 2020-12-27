package com.kilogate.hi.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * base64 工具类
 *
 * @author kilogate
 * @create 2020/12/28 00:04
 **/
public class Base64Util {
    /**
     * URL 安全的 base64 编码
     */
    public static String urlSafeEncode(String data) {
        String encode = encode(data);

        if (encode == null || encode.length() == 0) {
            return null;
        }

        return encode.replaceAll("\\+", "-").replaceAll("/", "_").replaceAll("=", "\\.");
    }

    /**
     * base64 编码
     */
    public static String encode(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        return doEncode(data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * base64 编码
     */
    public static String doEncode(byte[] data) {
        if (data == null || data.length == 0) {
            return null;
        }

        return new BASE64Encoder().encodeBuffer(data);
    }

    /**
     * URL 安全的 base64 解码
     */
    public static String urlSafeDecode(String data) throws IOException {
        if (data == null || data.length() == 0) {
            return null;
        }

        data = data.replaceAll("-", "\\+").replaceAll("_", "/").replaceAll("\\.", "=");

        return decode(data);
    }

    /**
     * base64 解码
     */
    public static String decode(String data) throws IOException {
        if (data == null || data.length() == 0) {
            return null;
        }

        return new String(doDecode(data), StandardCharsets.UTF_8);
    }

    /**
     * base64 解码
     */
    public static byte[] doDecode(String data) throws IOException {
        if (data == null || data.length() == 0) {
            return null;
        }

        return new BASE64Decoder().decodeBuffer(data);
    }

    /**
     * 测试
     */
    public static void main(String[] args) throws IOException {
        // 编码
        String urlSafeEncode = urlSafeEncode("哈喽");
        System.out.println(urlSafeEncode);

        String encode = encode("哈喽");
        System.out.println(encode);

        // 解码
        String urlSafeDecode = urlSafeDecode(urlSafeEncode);
        String decode = decode(encode);

        System.out.println();
    }
}
