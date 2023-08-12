package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;

/**
 * 轮转数组
 * <p>
 * https://leetcode-cn.com/problems/rotate-array/
 *
 * @author kilogate
 * @create 2022/2/13 19:38
 **/
public class Lc0189 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        new Lc0189().rotate2(nums, 2);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 方法一：环状替换
     *
     * @param nums
     * @param k
     */
    public void rotate1(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k <= 0) {
            return;
        }

        int length = nums.length;
        k = k % length;

        if (k == 0) {
            return;
        }

        int a = 0;
        int b = addIndex(a, k, length);
        int tempA = nums[a];
        int loop = a;

        for (int i = 0; i < length; i++) {
            int tempB = nums[b];
            nums[b] = tempA;
            tempA = tempB;

            a = b;
            b = addIndex(b, k, length);

            if (a == loop) {
                a++;
                b++;
                tempA = nums[a];
                loop++;
            }
        }
    }

    private int addIndex(int a, int k, int length) {
        return (a + k) % length;
    }

    /**
     * 方法二：反转数组
     *
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k <= 0) {
            return;
        }

        k %= nums.length;

        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++;
            end--;
        }
    }
}
