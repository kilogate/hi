package com.kilogate.hi.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 腐烂的橘子
 * <p>
 * https://leetcode-cn.com/problems/rotting-oranges/
 *
 * @author fengquanwei
 * @create 2022/3/12 18:33
 **/
public class Lc0994 {
    public static void main(String[] args) {
        Lc0994 lc0994 = new Lc0994();
        int res = lc0994.orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}});
        System.out.println(res);
    }

    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        int m = grid.length, n = grid[0].length;

        int res = 0;
        List<int[]> list = new ArrayList();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    list.add(new int[]{i, j});
                }
            }
        }

        while (!list.isEmpty()) {
            List<int[]> newList = new ArrayList();

            for (int[] element : list) {
                int i = element[0], j = element[1];

                if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                    grid[i - 1][j] = 2;
                    newList.add(new int[]{i - 1, j});
                }
                if (i + 1 < m && grid[i + 1][j] == 1) {
                    grid[i + 1][j] = 2;
                    newList.add(new int[]{i + 1, j});
                }
                if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                    grid[i][j - 1] = 2;
                    newList.add(new int[]{i, j - 1});
                }
                if (j + 1 < n && grid[i][j + 1] == 1) {
                    grid[i][j + 1] = 2;
                    newList.add(new int[]{i, j + 1});
                }
            }

            if (!newList.isEmpty()) {
                res++;
            }

            list = newList;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return res;
    }
}
