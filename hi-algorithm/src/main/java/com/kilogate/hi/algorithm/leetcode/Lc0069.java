package com.kilogate.hi.algorithm.leetcode;

/**
 * x 的平方根
 * <p>
 * https://leetcode-cn.com/problems/sqrtx
 *
 * @author fengquanwei
 * @create 2022/2/18 00:10
 **/
public class Lc0069 {
    public static void main(String[] args) {
        Lc0069 lc0069 = new Lc0069();
        System.out.println(lc0069.mySqrt(2147395599));
    }

    public int mySqrt(int x) {
        if (x < 0) {
            return -1;
        }

        int l = 0, r = x, result = 0;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if ((long) m * m <= x) {
                result = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return result;
    }
}
