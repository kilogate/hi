package com.kilogate.hi.algorithm.leetcode;

/**
 * 爬楼梯
 * <p>
 * https://leetcode-cn.com/problems/climbing-stairs/
 *
 * @author kilogate
 * @create 2022/2/20 01:13
 **/
public class Lc0070 {
    public static void main(String[] args) {
        Lc0070 lc0070 = new Lc0070();
        int res = lc0070.climbStairs(3);
        System.out.println(res);
    }

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        int a = 1, b = 2, c = 0;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }

        return c;
    }
}
