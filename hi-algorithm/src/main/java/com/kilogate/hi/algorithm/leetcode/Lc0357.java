package com.kilogate.hi.algorithm.leetcode;

/**
 * 统计各位数字都不同的数字个数
 * <p>
 * https://leetcode-cn.com/problems/count-numbers-with-unique-digits/
 *
 * @author fengquanwei
 * @create 2022/4/11 23:11
 **/
public class Lc0357 {
    public static void main(String[] args) {
        System.out.println(new Lc0357().countNumbersWithUniqueDigits(8));
    }

    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return 10;
        }

        int res = 10, curr = 9;
        for (int i = 0; i < n - 1; i++) {
            curr *= 9 - i;
            res += curr;
        }
        return res;
    }
}
