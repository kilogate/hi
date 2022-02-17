package com.kilogate.hi.algorithm.leetcode;

/**
 * 全排列
 * <p>
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
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
