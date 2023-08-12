package com.kilogate.hi.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三角形最小路径和
 * <p>
 * https://leetcode-cn.com/problems/triangle/
 *
 * @author kilogate
 * @create 2022/3/13 14:29
 **/
public class Lc0120 {
    public static void main(String[] args) {
        Lc0120 lc0120 = new Lc0120();

        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(new ArrayList<>(Arrays.asList(2)));
        triangle.add(new ArrayList<>(Arrays.asList(3, 4)));
        triangle.add(new ArrayList<>(Arrays.asList(6, 5, 7)));
        triangle.add(new ArrayList<>(Arrays.asList(4, 1, 8, 3)));

        int res = lc0120.minimumTotal(triangle);
        System.out.println(res);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        int[][] f = new int[n][n];
        f[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < n; i++) {
            f[i][0] = f[i - 1][0] + triangle.get(i).get(0);

            for (int j = 1; j < i; j++) {
                f[i][j] = Math.min(f[i - 1][j - 1], f[i - 1][j]) + triangle.get(i).get(j);
            }

            f[i][i] = f[i - 1][i - 1] + triangle.get(i).get(i);
        }

        int res = Integer.MAX_VALUE;

        for (int j = 0; j < n; j++) {
            res = Math.min(res, f[n - 1][j]);
        }

        return res;
    }
}
