package com.kilogate.hi.util;

/**
 * StringUtils
 *
 * @author fengquanwei
 * @create 2020/4/8 下午4:39
 **/
public class StringUtils {
    public static String toBinaryString(int num) {
        StringBuffer result = new StringBuffer();

        String binaryString = Integer.toBinaryString(num);

        while (binaryString.length() + result.length() < 32) {
            result.append('0');
        }

        result.append(binaryString);

        return result.toString();
    }

    public static String toBinaryString(long num) {
        StringBuffer result = new StringBuffer();

        String binaryString = Long.toBinaryString(num);

        while (binaryString.length() + result.length() < 64) {
            result.append('0');
        }

        result.append(binaryString);

        return result.toString();
    }

    public static void main(String[] args) {
        for (long i = -10; i < 10; i++) {
            System.out.println(toBinaryString(i));
        }
    }
}
