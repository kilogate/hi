package com.kilogate.hi.algorithm.leetcode;

/**
 * 最大回文数乘积
 * <p>
 * https://leetcode-cn.com/problems/largest-palindrome-product/
 *
 * @author kilogate
 * @create 2022/4/17 14:48
 **/
public class Lc0479 {
    public int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }

        int ans = 0;
        int upper = (int) Math.pow(10, n) - 1;
        for (int left = upper; ans == 0; left--) { // 枚举回文数的左半部分
            long palindrome = left;
            for (int x = left; x > 0; x /= 10) {
                palindrome = palindrome * 10 + x % 10; // 翻转左半部分到其自身末尾，构造回文数 palindrome
            }
            for (long x = upper; x * x >= palindrome; x--) {
                if (palindrome % x == 0) { // x 是 palindrome 的因子
                    ans = (int) (palindrome % 1337);
                    break;
                }
            }
        }
        return ans;
    }
}
