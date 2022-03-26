package com.kilogate.hi.algorithm.leetcode;

/**
 * 阶乘后的零
 * <p>
 * https://leetcode-cn.com/problems/factorial-trailing-zeroes/
 *
 * @author fengquanwei
 * @create 2022/3/26 12:47
 **/
public class Lc0172 {
    public static void main(String[] args) {
        Lc0172 lc0172 = new Lc0172();
        for (int i = 1; i < 1000; i++) {
            System.out.println(i + ":" + lc0172.trailingZeroes(i));
        }
    }

    public int trailingZeroes(int n) {
        int ans = 0;

        while (n != 0) {
            int c = n / 5;
            ans += c;
            n = c;
        }

        return ans;
    }
}
