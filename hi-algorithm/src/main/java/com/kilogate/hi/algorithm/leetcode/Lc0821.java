package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;

/**
 * 字符的最短距离
 * <p>
 * https://leetcode-cn.com/problems/shortest-distance-to-a-character/
 *
 * @author fengquanwei
 * @create 2022/4/19 17:17
 **/
public class Lc0821 {
    public static void main(String[] args) {
        Lc0821 lc0821 = new Lc0821();
        int[] res = lc0821.shortestToChar2("loveleetcode", 'e');
        System.out.println(Arrays.toString(res));
    }

    public int[] shortestToChar1(String s, char c) {
        int[] res = new int[s.length()];
        int next = -s.length();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch != c) {
                res[i] = i - next;
                continue;
            }

            res[i] = 0;
            for (int j = Math.max(next, 0); j < i; j++) {
                res[j] = Math.min(res[j], i - j);
            }
            next = i;
        }
        return res;
    }

    public int[] shortestToChar2(String s, char c) {
        int[] res = new int[s.length()];

        for (int i = 0, idx = -s.length(); i < s.length(); i++) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            res[i] = i - idx;
        }

        for (int i = s.length() - 1, idx = 2 * s.length(); i >= 0; i--) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            res[i] = Math.min(res[i], idx - i);
        }

        return res;
    }
}
