package com.kilogate.hi.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 全排列
 * <p>
 * https://leetcode-cn.com/problems/permutations/
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
        List<List<Integer>> res = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return res;
        }

        doPermute(nums, res, 0);

        return res;
    }

    private void doPermute(int[] nums, List<List<Integer>> res, int i) {
        if (i == nums.length) {
            res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }

        for (int j = i; j < nums.length; j++) {
            swap(nums, i, j);
            doPermute(nums, res, i + 1);
            swap(nums, j, i);
        }
    }

    private void swap(int[] nums, int a, int b) {
        if (a == b) {
            return;
        }

        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
