package com.kilogate.hi.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合总和
 * <p>
 * https://leetcode-cn.com/problems/combination-sum/
 *
 * @author kilogate
 * @create 2022/3/12 01:17
 **/
public class Lc0039 {
    public static void main(String[] args) {
        Lc0039 lc0039 = new Lc0039();
        List<List<Integer>> lists = lc0039.combinationSum(new int[]{2, 3, 6, 7}, 7);
        System.out.println(lists);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0 || target <= 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();

        getSolution(candidates, target, res, solution, 0);

        return res;
    }

    public void getSolution(int[] candidates, int target, List<List<Integer>> res, List<Integer> solution, int from) {
        if (target == 0) {
            res.add(new ArrayList<>(solution));
            return;
        }

        for (int i = from; i < candidates.length; i++) {
            int num = candidates[i];

            if (target < num) {
                continue;
            }

            // add num to solution
            solution.add(num);
            target -= num;

            // try next num
            getSolution(candidates, target, res, solution, i);

            // remove num from solution
            target += num;
            solution.remove(solution.size() - 1);
        }
    }

}
