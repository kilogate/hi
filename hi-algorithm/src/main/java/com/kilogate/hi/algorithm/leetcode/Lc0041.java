package com.kilogate.hi.algorithm.leetcode;

/**
 * 缺失的第一个正数
 * <p>
 * https://leetcode-cn.com/problems/first-missing-positive/
 *
 * @author fengquanwei
 * @create 2022/3/13 16:44
 **/
public class Lc0041 {
    public static void main(String[] args) {
        Lc0041 lc0041 = new Lc0041();
        System.out.println(lc0041.firstMissingPositive(new int[]{1, 2, 3, 0}));
    }

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] >= 1 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }
}
