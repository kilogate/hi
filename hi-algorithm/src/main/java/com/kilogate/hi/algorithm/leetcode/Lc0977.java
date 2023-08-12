package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;

/**
 * 有序数组的平方
 * <p>
 * https://leetcode-cn.com/problems/squares-of-a-sorted-array/
 *
 * @author kilogate
 * @create 2022/2/13 22:49
 **/
public class Lc0977 {
    public static void main(String[] args) {
        Lc0977 lc0977 = new Lc0977();

        int[] ints = lc0977.sortedSquares2(new int[]{2, 3, 3, 4});
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 方法一：顺序双指针
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        // 寻找中点（最后一个负数，没有负数则是第一个正数）
        int mid = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                break;
            }

            mid = i;
        }

        // 构造结果
        int[] result = new int[nums.length];
        int index = 0;

        int left = mid;
        int right = mid + 1;
        while (left >= 0 || right <= nums.length - 1) {
            if (left == -1) {
                result[index] = nums[right] * nums[right];
                right++;
                index++;
            } else if (right == nums.length) {
                result[index] = nums[left] * nums[left];
                left--;
                index++;
            } else if (Math.abs(nums[left]) < nums[right]) {
                result[index] = nums[left] * nums[left];
                left--;
                index++;
            } else {
                result[index] = nums[right] * nums[right];
                right++;
                index++;
            }
        }

        return result;
    }

    /**
     * 方法二：逆序双指针
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int[] result = new int[nums.length];
        int index = nums.length - 1;

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (Math.abs(nums[left]) >= Math.abs(nums[right])) {
                result[index] = nums[left] * nums[left];
                index--;
                left++;
            } else {
                result[index] = nums[right] * nums[right];
                index--;
                right--;
            }
        }

        return result;
    }
}