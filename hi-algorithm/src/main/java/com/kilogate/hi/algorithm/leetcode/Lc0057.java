package com.kilogate.hi.algorithm.leetcode;

/**
 * 反转字符串中的单词 III
 *
 * 给定一个字符串 s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 *
 * @author fengquanwei
 * @create 2022/2/27 21:57
 **/
public class Lc0057 {
    public static void main(String[] args) {
        Lc0057 lc0057 = new Lc0057();
        String res = lc0057.reverseWords("Hello Mr's DJ");
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
