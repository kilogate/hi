package com.kilogate.hi.algorithm.leetcode;

/**
 * 丑数
 * <p>
 * https://leetcode-cn.com/problems/ugly-number
 *
 * @author fengquanwei
 * @create 2022/3/9 22:30
 **/
public class Lc0263 {
    public static void main(String[] args) {
        Lc0263 lc0263 = new Lc0263();
        System.out.println(lc0263.isUgly(17));
    }

    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }

        int[] arr = new int[]{2, 3, 5};
        for (int i : arr) {
            while (n % i == 0) {
                n /= i;
            }
        }

        return n == 1;
    }
}
