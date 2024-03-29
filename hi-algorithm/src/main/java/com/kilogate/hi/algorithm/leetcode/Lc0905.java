package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;

/**
 * 按奇偶排序数组
 * <p>
 * https://leetcode-cn.com/problems/sort-array-by-parity/
 *
 * @author kilogate
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
