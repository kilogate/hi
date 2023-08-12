package com.kilogate.hi.algorithm.leetcode;

/**
 * 搜索旋转排序数组
 * <p>
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 *
 * @author kilogate
 * @create 2022/2/19 10:57
 **/
public class Lc0033 {
    public static void main(String[] args) {
        Lc0033 lc0033 = new Lc0033();
        int result = lc0033.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0);
        System.out.println(result);
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] >= nums[0]) {
                if (target >= nums[0] && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target >= nums[mid] && target <= nums[nums.length - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
