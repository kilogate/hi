package com.kilogate.hi.algorithm.leetcode;

/**
 * 反转字符串中的单词 III
 * <p>
 * https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/
 *
 * @author kilogate
 * @create 2022/3/1 01:04
 **/
public class Lc0557 {
    public static void main(String[] args) {
        Lc0557 lc0557 = new Lc0557();
        String res = lc0557.reverseWords("How are you");
        System.out.println(res);
    }

    public String reverseWords(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        StringBuilder res = new StringBuilder();

        int l = 0, r = 0;
        while (r <= s.length() - 1) {
            while (r <= s.length() - 1 && s.charAt(r) != ' ') {
                r++;
            }

            for (int i = r - 1; i >= l; i--) {
                res.append(s.charAt(i));
            }

            while (r <= s.length() - 1 && s.charAt(r) == ' ') {
                res.append(' ');
                r++;
            }

            l = r;
        }

        return res.toString();
    }
}
