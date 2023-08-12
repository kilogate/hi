package com.kilogate.hi.algorithm.leetcode;

/**
 * 位1的个数
 * <p>
 * https://leetcode-cn.com/problems/number-of-1-bits/
 *
 * @author kilogate
 * @create 2022/3/13 14:54
 **/
public class Lc0191 {
    public static void main(String[] args) {
        Lc0191 lc0191 = new Lc0191();
        System.out.println(lc0191.hammingWeight(123));
    }

    public int hammingWeight(int n) {
        int res = 0;

        while (n != 0) {
            n &= n - 1;
            res++;
        }

        return res;
    }
}
