package com.kilogate.hi.algorithm.leetcode;

/**
 * 找出所有子集的异或总和再求和
 * <p>
 * https://leetcode-cn.com/problems/sum-of-all-subset-xor-totals/
 *
 * @author kilogate
 * @create 2022/3/12 15:45
 **/
public class Lc1863 {
    public static void main(String[] args) {
        Lc1863 lc1863 = new Lc1863();
        int res = lc1863.subsetXORSum(new int[]{5, 1, 6});
        System.out.println(res);
    }

    public int subsetXORSum(int[] nums) {
        int[] res = new int[]{0};
        if (nums == null || nums.length == 0) {
            return res[0];
        }

        getXor(res, 0, nums, 0);
        return res[0];
    }

    private void getXor(int[] res, int xor, int[] nums, int i) {
        if (i == nums.length) {
            res[0] += xor;
            return;
        }

        getXor(res, xor, nums, i + 1);
        getXor(res, xor ^ nums[i], nums, i + 1);
    }
}
