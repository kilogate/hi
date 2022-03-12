package com.kilogate.hi.algorithm.leetcode;

/**
 * 岛屿的最大面积
 * <p>
 * https://leetcode-cn.com/problems/max-area-of-island
 *
 * @author fengquanwei
 * @create 2022/3/6 00:17
 **/
public class Lc0695 {
    public static void main(String[] args) {
        Lc0695 lc0695 = new Lc0695();

        int[][] grid = new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};

        int res = lc0695.maxAreaOfIsland(grid);
        System.out.println(res);
    }

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int res = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                res = Math.max(res, getArea(grid, i, j));
            }
        }

        return res;
    }

    private int getArea(int[][] grid, int row, int cloumn) {
        if (row < 0 || row >= grid.length || cloumn < 0 || cloumn >= grid[0].length || grid[row][cloumn] == 0) {
            return 0;
        }

        grid[row][cloumn] = 0;

        int res = 1;
        res += getArea(grid, row - 1, cloumn);
        res += getArea(grid, row + 1, cloumn);
        res += getArea(grid, row, cloumn - 1);
        res += getArea(grid, row, cloumn + 1);
        return res;
    }
}
