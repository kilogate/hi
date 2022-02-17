package com.kilogate.hi.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 * <p>
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * @author fengquanwei
 * @create 2022/2/17 19:10
 **/
public class Lc0046 {
    public static void main(String[] args) {
        Lc0046 lc0046 = new Lc0046();
        List<List<Integer>> result = lc0046.permute(new int[]{1, 2, 3, 4});
        result.forEach(System.out::println);
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return result;
        }

        doPermute(nums, 0, result);

        return result;
    }

    private void doPermute(int[] nums, int index, List<List<Integer>> result) {
        if (index == nums.length - 1) {
            result.add(buildList(nums));
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swapNum(nums, index, i);
            doPermute(nums, index + 1, result);
            swapNum(nums, i, index);
        }
    }

    private void swapNum(int[] nums, int a, int b) {
        if (a == b) {
            return;
        }

        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    private List<Integer> buildList(int[] nums) {
        List<Integer> result = new ArrayList<>();

        for (int num : nums) {
            result.add(num);
        }

        return result;
    }
}
