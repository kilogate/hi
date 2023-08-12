package com.kilogate.hi.algorithm.leetcode;

/**
 * 搜索插入位置
 * <p>
 * https://leetcode-cn.com/problems/search-insert-position/
 *
 * @author kilogate
 * @create 2022/2/13 16:14
 **/
public class Lc0035 {
    public static void main(String[] args) {
        Lc0035 lc0035 = new Lc0035();

        System.out.println(lc0035.searchInsert(new int[]{1, 3, 5, 7}, 4));
    }

    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if (nums[left] < target) {
            return left + 1;
        }

        return left;
    }
}
