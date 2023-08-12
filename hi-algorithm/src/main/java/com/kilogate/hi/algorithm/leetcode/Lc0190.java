package com.kilogate.hi.algorithm.leetcode;

/**
 * 颠倒二进制位
 * <p>
 * https://leetcode-cn.com/problems/reverse-bits/
 *
 * @author kilogate
 * @create 2022/3/13 15:06
 **/
public class Lc0190 {
    public static void main(String[] args) {
        Lc0190 lc0190 = new Lc0190();
        System.out.println(lc0190.reverseBits(0));
        System.out.println(lc0190.reverseBits(1));
    }

    public int reverseBits(int n) {
        int res = 0;

        for (int i = 1; i <= 32; i++) {
            res |= (n & 1) << (32 - i);
            n >>>= 1;
        }

        return res;
    }
}
