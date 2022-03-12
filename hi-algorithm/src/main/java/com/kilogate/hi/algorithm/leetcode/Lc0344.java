package com.kilogate.hi.algorithm.leetcode;

/**
 * 反转字符串
 * <p>
 * https://leetcode-cn.com/problems/reverse-string
 *
 * @author fengquanwei
 * @create 2022/2/27 21:43
 **/
public class Lc0344 {
    public static void main(String[] args) {
        Lc0344 lc0344 = new Lc0344();
        char[] s = "Hello".toCharArray();
        lc0344.reverseString(s);
        System.out.println(new String(s));
    }

    public void reverseString(char[] s) {
        if (s == null || s.length <= 1) {
            return;
        }

        int n = s.length / 2;
        for (int i = 0; i < n; i++) {
            int j = s.length - 1 - i;

            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }
    }
}
