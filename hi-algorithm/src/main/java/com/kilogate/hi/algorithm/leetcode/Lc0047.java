package com.kilogate.hi.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 * <p>
 * https://leetcode-cn.com/problems/permutations-ii/
 *
 * @author fengquanwei
 * @create 2022/2/17 23:10
 **/
public class Lc0047 {
    public static void main(String[] args) {
        Lc0047 lc0047 = new Lc0047();
        List<List<Integer>> result = lc0047.permute(new int[]{1, 1, 2, 2, 3, 3, 4});
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

        List<Integer> visited = new ArrayList<>();
        for (int i = index; i < nums.length; i++) {
            if (visited.contains(nums[i])) {
                continue;
            }

            visited.add(nums[i]);

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
