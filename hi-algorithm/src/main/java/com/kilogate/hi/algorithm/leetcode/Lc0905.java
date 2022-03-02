package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;

/**
 * 按奇偶排序数组
 * <p>
 * 给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。
 * 你可以返回满足此条件的任何数组作为答案。
 *
 * @author fengquanwei
 * @create 2022/3/3 00:50
 **/
public class Lc0905 {
    public static void main(String[] args) {
        Lc0905 lc0905 = new Lc0905();
        int[] res = lc0905.sortArrayByParity(new int[]{1, 2, 3, 4, 5});
        System.out.println(Arrays.toString(res));
    }

    public int[] sortArrayByParity(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        int i = 0, j = nums.length - 1;
        while (i < j) {
            if (nums[i] % 2 == 0) {
                if (nums[j] % 2 == 0) {
                    // 偶偶
                    i++;
                } else {
                    // 偶奇
                    i++;
                    j--;
                }
            } else {
                if (nums[j] % 2 == 0) {
                    // 奇偶
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    i++;
                    j--;
                } else {
                    // 奇奇
                    j--;
                }
            }
        }

        return nums;
    }
}
