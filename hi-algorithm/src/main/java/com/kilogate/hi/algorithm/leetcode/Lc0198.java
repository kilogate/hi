package com.kilogate.hi.algorithm.leetcode;

/**
 * 打家劫舍
 * <p>
 * https://leetcode-cn.com/problems/house-robber/
 *
 * @author kilogate
 * @create 2022/3/12 22:34
 **/
public class Lc0198 {
    public static void main(String[] args) {
        Lc0198 lc0198 = new Lc0198();
        int res = lc0198.rob(new int[]{1, 2});
        System.out.println(res);
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = second;
            second = Math.max(second, first + nums[i]);
            first = temp;
        }

        return second;
    }
}
