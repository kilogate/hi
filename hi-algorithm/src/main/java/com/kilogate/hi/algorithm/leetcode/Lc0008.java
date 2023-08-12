package com.kilogate.hi.algorithm.leetcode;

/**
 * 字符串转换整数 (atoi)
 * <p>
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 *
 * @author kilogate
 * @create 2022/3/13 15:44
 **/
public class Lc0008 {
    public static void main(String[] args) {
        Lc0008 lc0008 = new Lc0008();
        int res = lc0008.myAtoi(" ");
        System.out.println(res);
    }

    public int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int i = 0;
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }

        boolean positive = true;
        if (i < s.length()) {
            if (s.charAt(i) == '-') {
                positive = false;
                i++;
            } else if (s.charAt(i) == '+') {
                i++;
            }
        }

        long val = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (!Character.isDigit(c)) {
                break;
            }

            val = val * 10 + (c - '0');
            i++;

            if (positive) {
                val = Math.min(val, Integer.MAX_VALUE);
            } else {
                val = Math.min(val, Integer.MAX_VALUE + 1L);
            }
        }

        if (val == 0) {
            return 0;
        }

        return (int) (positive ? val : -val);
    }
}
