package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;

/**
 * 移动零
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 * @author fengquanwei
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
