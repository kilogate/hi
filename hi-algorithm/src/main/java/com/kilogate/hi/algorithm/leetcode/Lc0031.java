package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;

/**
 * 下一个排列
 * <p>
 * https://leetcode-cn.com/problems/next-permutation/
 *
 * @author fengquanwei
 * @create 2022/3/12 00:52
 **/
public class Lc0031 {
    public static void main(String[] args) {
        Lc0031 lc0031 = new Lc0031();
        int[] nums = new int[]{1, 2, 3};
        lc0031.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i == -1) {
            reverse(nums, i + 1, nums.length - 1);
            return;
        }

        int j = nums.length - 1;
        while (nums[i] >= nums[j]) {
            j--;
        }

        swap(nums, i, j);
        reverse(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }
}
