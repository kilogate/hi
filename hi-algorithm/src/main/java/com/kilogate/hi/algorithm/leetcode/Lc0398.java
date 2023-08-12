package com.kilogate.hi.algorithm.leetcode;

import java.util.Random;

/**
 * 随机数索引
 * <p>
 * https://leetcode-cn.com/problems/random-pick-index/
 *
 * @author kilogate
 * @create 2022/4/29 16:13
 **/
public class Lc0398 {
    private int[] nums;
    private Random random;

    public static void main(String[] args) {
        Lc0398 lc0398 = new Lc0398(new int[]{1, 2, 3, 3, 3});
        System.out.println(lc0398.pick(3));
        System.out.println(lc0398.pick(1));
        System.out.println(lc0398.pick(3));
        System.out.println(lc0398.pick(3));
        System.out.println(lc0398.pick(3));
        System.out.println(lc0398.pick(3));
        System.out.println(lc0398.pick(3));
        System.out.println(lc0398.pick(3));
    }

    public Lc0398(int[] nums) {
        this.nums = nums;
        this.random = new Random();
    }

    /**
     * 水塘抽样
     *
     * @param target
     * @return
     */
    public int pick(int target) {
        int count = 0, ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) {
                continue;
            }

            count++;
            if (random.nextInt(count) == 0) {
                ans = i;
            }
        }
        return ans;
    }
}
