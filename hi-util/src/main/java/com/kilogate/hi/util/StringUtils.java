package com.kilogate.hi.util;

/**
 * StringUtils
 *
 * @author fengquanwei
 * @create 2020/4/8 下午4:39
 **/
public class StringUtils {
    public static String toBinaryString(byte b) {
        return String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
    }

    public static String toBinaryString(int i) {
        return String.format("%32s", Integer.toBinaryString(i)).replace(' ', '0');
    }

    public static String toBinaryString(long l) {
        return String.format("%64s", Long.toBinaryString(l)).replace(' ', '0');
    }

    public static void main(String[] args) {
        for (byte i = -10; i < 10; i++) {
            System.out.println(toBinaryString(i));
        }
    }
}
