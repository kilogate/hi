package com.kilogate.hi.algorithm.leetcode;

/**
 * 最长回文子串
 * <p>
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 * @author kilogate
 * @create 2022/2/19 17:32
 **/
public class Lc0005 {
    public static void main(String[] args) {
        System.out.println(new Lc0005().longestPalindrome("abcdcba"));
    }

    public String longestPalindrome(String str) {
        boolean[][] cache = new boolean[str.length()][str.length()];

        int gap = 0;
        int maxLen = 0;
        int from = 0;

        while (gap < str.length()) {
            for (int i = 0; i < str.length() - gap; i++) {
                if (isPalindrome(str, i, i + gap, cache)) {
                    cache[i][i + gap] = true;

                    if (maxLen < gap + 1) {
                        maxLen = gap + 1;
                        from = i;
                    }
                }
            }

            gap++;
        }

        return str.substring(from, from + maxLen);
    }

    private boolean isPalindrome(String str, int i, int j, boolean[][] cache) {
        // 单个字符
        if (i == j) {
            return true;
        }

        // 两个字符
        if (i + 1 == j) {
            return str.charAt(i) == str.charAt(j);
        }

        // 子串不回文
        if (!cache[i + 1][j - 1]) {
            return false;
        }

        // 子串回文判断首尾字符是否相等
        return str.charAt(i) == str.charAt(j);
    }
}
