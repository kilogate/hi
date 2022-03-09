package com.kilogate.hi.algorithm.leetcode;

/**
 * 丑数
 * <p>
 * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ugly-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
