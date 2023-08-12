package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;

/**
 * 两数之和 II - 输入有序数组
 * <p>
 * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
 *
 * @author kilogate
 * @create 2022/2/27 20:58
 **/
public class Lc0167 {
    public static void main(String[] args) {
        Lc0167 lc0167 = new Lc0167();
        int[] res = lc0167.twoSum(new int[]{2, 3, 5, 7, 8, 99}, 9);
        System.out.println(Arrays.toString(res));
    }

    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length <= 1) {
            return new int[]{-1, -1};
        }

        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) {
                return new int[]{l + 1, r + 1};
            } else if (sum < target) {
                l++;
            } else {
                r--;
            }
        }

        return new int[]{-1, -1};
    }
}
