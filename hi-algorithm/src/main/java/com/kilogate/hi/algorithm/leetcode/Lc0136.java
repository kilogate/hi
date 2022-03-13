package com.kilogate.hi.algorithm.leetcode;

/**
 * 只出现一次的数字
 * <p>
 * https://leetcode-cn.com/problems/single-number/
 *
 * @author fengquanwei
 * @create 2022/3/13 15:31
 **/
public class Lc0136 {
    public static void main(String[] args) {
        Lc0136 lc0136 = new Lc0136();
        int res = lc0136.singleNumber(new int[]{4, 1, 2, 1, 2});
        System.out.println(res);
    }

    public int singleNumber(int[] nums) {
        int res = 0;

        for (int num : nums) {
            res ^= num;
        }

        return res;
    }
}
