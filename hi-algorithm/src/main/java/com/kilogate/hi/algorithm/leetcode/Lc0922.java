package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;

/**
 * 按奇偶排序数组 II
 * <p>
 * https://leetcode-cn.com/problems/sort-array-by-parity-ii
 *
 * @author kilogate
 * @create 2022/3/3 01:01
 **/
public class Lc0922 {
    public static void main(String[] args) {
        Lc0922 lc0922 = new Lc0922();
        int[] res = lc0922.sortArrayByParityII(new int[]{2, 3, 1, 1, 4, 0, 0, 4, 3, 3});
        System.out.println(Arrays.toString(res));
    }

    public int[] sortArrayByParityII(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            boolean iMatch = i % 2 == nums[i] % 2;
            boolean jMatch = j % 2 == nums[j] % 2;
            if (iMatch && jMatch) {
                i++;
                j--;
            } else if (iMatch && !jMatch) {
                i++;
            } else if (!iMatch && jMatch) {
                j--;
            } else {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }

        return nums;
    }
}
