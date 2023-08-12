package com.kilogate.hi.algorithm.leetcode;

/**
 * 2 的幂
 * <p>
 * https://leetcode-cn.com/problems/power-of-two/
 *
 * @author kilogate
 * @create 2022/3/13 14:47
 **/
public class Lc0231 {
    public static void main(String[] args) {
        Lc0231 lc0231 = new Lc0231();
        boolean res = lc0231.isPowerOfTwo(12);
        System.out.println(res);
    }

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
