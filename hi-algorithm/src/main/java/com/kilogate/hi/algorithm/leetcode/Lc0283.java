package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;

/**
 * 移动零
 * <p>
 * https://leetcode-cn.com/problems/move-zeroes/
 *
 * @author kilogate
 * @create 2022/2/26 11:17
 **/
public class Lc0283 {
    public static void main(String[] args) {
        Lc0283 lc0283 = new Lc0283();
        int[] arr = {1, 2, 3, 0, 0, 4, 0, 5};
        lc0283.moveZeroes(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int a = 0, b = 0;
        while (b <= nums.length - 1) {
            if (nums[b] != 0) {
                int temp = nums[a];
                nums[a] = nums[b];
                nums[b] = temp;
                a++;
            }

            b++;
        }
    }
}
