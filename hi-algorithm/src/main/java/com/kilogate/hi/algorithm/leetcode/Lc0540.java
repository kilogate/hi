package com.kilogate.hi.algorithm.leetcode;

/**
 * 有序数组中的单一元素
 * <p>
 * https://leetcode-cn.com/problems/single-element-in-a-sorted-array/
 *
 * @author kilogate
 * @create 2022/3/13 20:45
 **/
public class Lc0540 {
    public static void main(String[] args) {
        Lc0540 lc0540 = new Lc0540();
        int res = lc0540.singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8});
        System.out.println(res);
    }

    public int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = (r - l) / 2 + l;
            if (nums[m] == nums[m ^ 1]) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return nums[l];
    }
}
