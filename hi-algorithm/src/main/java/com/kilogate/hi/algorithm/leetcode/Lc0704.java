package com.kilogate.hi.algorithm.leetcode;

/**
 * 二分查找
 * <p>
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target
 * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1
 *
 * @author fengquanwei
 * @create 2022/2/13 14:52
 **/
public class Lc0704 {
    public static void main(String[] args) {
        Lc0704 lc0704 = new Lc0704();
        System.out.println(lc0704.binarySearch(new int[]{1}, 4));
        System.out.println(lc0704.binarySearch(new int[]{1, 2, 3, 4, 5}, 4));
    }

    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (right - left) / 2 + left;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}