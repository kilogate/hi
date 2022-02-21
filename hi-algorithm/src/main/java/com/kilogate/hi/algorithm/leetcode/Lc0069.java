package com.kilogate.hi.algorithm.leetcode;

/**
 * x 的平方根
 * <p>
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
