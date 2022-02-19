package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 * <p>
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 进阶：
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *
 * @author fengquanwei
 * @create 2022/2/19 10:54
 **/
public class Lc0034 {
    public static void main(String[] args) {
        Lc0034 lc0034 = new Lc0034();
        int[] result = lc0034.searchRange(new int[]{1, 2, 2, 2, 3, 5}, 2);
        System.out.println(Arrays.toString(result));
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int from = getFirstBiggerIndex(nums, target - 1);

        if (from == nums.length || nums[from] != target) {
            return new int[]{-1, -1};
        }

        int to = getFirstBiggerIndex(nums, target) - 1;

        return new int[]{from, to};
    }

    private int getFirstBiggerIndex(int[] nums, int target) {
        int left = 0, right = nums.length - 1, result = nums.length;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > target) {
                right = mid - 1;
                result = mid;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }
}
