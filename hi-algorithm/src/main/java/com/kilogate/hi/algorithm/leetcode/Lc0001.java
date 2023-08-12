package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * <p>
 * https://leetcode-cn.com/problems/two-sum/
 *
 * @author kilogate
 * @create 2022/2/19 12:05
 **/
public class Lc0001 {
    public static void main(String[] args) {
        Lc0001 lc0001 = new Lc0001();
        int[] result = lc0001.twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(Arrays.toString(result));
    }

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }

            map.put(nums[i], i);
        }

        return null;
    }
}
