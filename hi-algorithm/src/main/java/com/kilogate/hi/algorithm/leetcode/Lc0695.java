package com.kilogate.hi.algorithm.leetcode;

/**
 * 岛屿的最大面积
 * <p>
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-area-of-island
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
