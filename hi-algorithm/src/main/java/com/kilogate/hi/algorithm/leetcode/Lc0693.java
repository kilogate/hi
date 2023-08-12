package com.kilogate.hi.algorithm.leetcode;

/**
 * 交替位二进制数
 * <p>
 * https://leetcode-cn.com/problems/binary-number-with-alternating-bits/
 *
 * @author kilogate
 * @create 2022/3/28 23:47
 **/
public class Lc0693 {
    public static void main(String[] args) {
        boolean res = new Lc0693().hasAlternatingBits(5);
        System.out.println(res);
    }

    public boolean hasAlternatingBits(int n) {
        int a = n ^ (n >>> 1);
        return (a & (a + 1)) == 0;
    }
}
