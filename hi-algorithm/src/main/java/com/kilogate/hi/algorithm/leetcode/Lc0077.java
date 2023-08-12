package com.kilogate.hi.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合
 * <p>
 * https://leetcode-cn.com/problems/combinations/
 *
 * @author kilogate
 * @create 2022/3/12 21:56
 **/
public class Lc0077 {
    public static void main(String[] args) {
        Lc0077 lc0077 = new Lc0077();
        List<List<Integer>> res = lc0077.combine(4, 2);
        res.forEach(System.out::println);
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList();

        if (n < 1 || k < 1) {
            return res;
        }

        List<Integer> combine = new ArrayList();

        getCombine(res, combine, n, k, 1);

        return res;
    }

    private void getCombine(List<List<Integer>> res, List<Integer> combine, int n, int k, int from) {
        if (combine.size() == k) {
            res.add(new ArrayList(combine));
            return;
        }

        for (int i = from; i <= n; i++) {
            combine.add(i);
            getCombine(res, combine, n, k, i + 1);
            combine.remove(combine.size() - 1);
        }
    }
}
