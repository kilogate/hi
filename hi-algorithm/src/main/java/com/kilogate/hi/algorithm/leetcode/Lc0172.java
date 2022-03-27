package com.kilogate.hi.algorithm.leetcode;

/**
 * 阶乘后的零
 * <p>
 * https://leetcode-cn.com/problems/factorial-trailing-zeroes/
 *
 * @author fengquanwei
 * @create 2022/3/28 00:22
 **/
public class Lc0172 {
    public static void main(String[] args) {
        System.out.println(new Lc0172().trailingZeroes(125));
    }

    public int trailingZeroes(int n) {
        int ans = 0;

        while (n != 0) {
            ans += n / 5;
            n /= 5;
        }

        return ans;
    }
}
