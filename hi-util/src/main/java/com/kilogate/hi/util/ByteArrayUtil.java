package com.kilogate.hi.util;

/**
 * 字节数组工具类
 *
 * @author kilogate
 * @create 2020/12/27 17:45
 **/
public class ByteArrayUtil {
    /**
     * 字节数组转十六进制字符串（一个字节转为两个字符）
     */
    public static String byteArrayToHexString(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        String result = "";

        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;

            if (v < 16) {
                result += "0";
            }

            result += Integer.toString(v, 16).toUpperCase();
        }

        return result;
    }
}
