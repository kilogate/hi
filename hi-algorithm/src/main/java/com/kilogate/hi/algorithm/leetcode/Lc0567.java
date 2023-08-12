package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;

/**
 * 字符串的排列
 * <p>
 * https://leetcode-cn.com/problems/permutation-in-string
 *
 * @author kilogate
 * @create 2022/3/5 22:14
 **/
public class Lc0567 {
    public static void main(String[] args) {
        Lc0567 lc0567 = new Lc0567();
        boolean res = lc0567.checkInclusion("adc", "dcda");
        System.out.println(res);
    }

    public boolean checkInclusion(String str1, String str2) {
        if (str1 == null || str1.length() == 0) {
            return true;
        }

        if (str2 == null || str2.length() < str1.length()) {
            return false;
        }

        int[] stat1 = new int[26];
        int[] stat2 = new int[26];

        for (char c : str1.toCharArray()) {
            stat1[c - 'a']++;
        }

        for (int i = 0; i < str2.length(); i++) {
            stat2[str2.charAt(i) - 'a']++;

            if (i >= str1.length()) {
                stat2[str2.charAt(i - str1.length()) - 'a']--;
            }

            if (i >= str1.length() - 1 && Arrays.equals(stat1, stat2)) {
                return true;
            }
        }

        return false;
    }
}
