package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 01 矩阵
 * <p>
 * https://leetcode-cn.com/problems/01-matrix/
 *
 * @author kilogate
 * @create 2022/3/12 17:29
 **/
public class Lc0542 {
    public static void main(String[] args) {
        Lc0542 lc0542 = new Lc0542();
        int[][] res = lc0542.updateMatrix(new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}});
        Arrays.stream(res).forEach(arr -> {
            System.out.println(Arrays.toString(arr));
        });
    }

    public int[][] updateMatrix(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return null;
        }

        int m = mat.length, n = mat[0].length;

        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(res[i], -1);
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    res[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] element = queue.poll();
            int i = element[0];
            int j = element[1];
            int distance = res[i][j];

            if (i - 1 >= 0 && res[i - 1][j] == -1) {
                res[i - 1][j] = distance + 1;
                queue.offer(new int[]{i - 1, j});
            }
            if (i + 1 < m && res[i + 1][j] == -1) {
                res[i + 1][j] = distance + 1;
                queue.offer(new int[]{i + 1, j});
            }
            if (j - 1 >= 0 && res[i][j - 1] == -1) {
                res[i][j - 1] = distance + 1;
                queue.offer(new int[]{i, j - 1});
            }
            if (j + 1 < n && res[i][j + 1] == -1) {
                res[i][j + 1] = distance + 1;
                queue.offer(new int[]{i, j + 1});
            }
        }

        return res;
    }

}
