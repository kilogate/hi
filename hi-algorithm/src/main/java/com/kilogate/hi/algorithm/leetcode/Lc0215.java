package com.kilogate.hi.algorithm.leetcode;

/**
 * 数组中的第K个最大元素
 * <p>
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 *
 * @author kilogate
 * @create 2022/3/22 23:15
 **/
public class Lc0215 {
    public static void main(String[] args) {
        Lc0215 lc0215 = new Lc0215();
        int res = lc0215.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2);
        System.out.println(res);
    }

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) {
            return -1;
        }

        return doFindKthLargest(nums, nums.length - k + 1, 0, nums.length - 1);
    }

    private int doFindKthLargest(int[] nums, int k, int l, int r) {
        int i = doPartition(nums, l, r);
        int n = i - l + 1;

        if (n == k) {
            return nums[i];
        } else if (n > k) {
            return doFindKthLargest(nums, k, l, i - 1);
        } else {
            return doFindKthLargest(nums, k - n, i + 1, r);
        }
    }

    private int doPartition(int[] nums, int l, int r) {
        if (l == r) {
            return l;
        }

        int temp = nums[l];
        while (l < r) {
            while (l < r && nums[r] >= temp) {
                r--;
            }
            nums[l] = nums[r];

            while (l < r && nums[l] <= temp) {
                l++;
            }
            nums[r] = nums[l];
        }

        nums[l] = temp;
        return l;
    }
}
