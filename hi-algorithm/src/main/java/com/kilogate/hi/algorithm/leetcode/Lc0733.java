package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;

/**
 * 图像渲染
 * <p>
 * https://leetcode-cn.com/problems/flood-fill
 *
 * @author kilogate
 * @create 2022/3/5 23:41
 **/
public class Lc0733 {
    public static void main(String[] args) {
        Lc0733 lc0733 = new Lc0733();

        int[][] image = new int[][]{{0, 0, 0}, {0, 1, 1}};
        int[][] res = lc0733.floodFill(image, 1, 1, 1);
        System.out.println(Arrays.toString(res));
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return image;
        }

        if (sr >= image.length || sc >= image[0].length) {
            return image;
        }

        if (image[sr][sc] == newColor) {
            return image;
        }

        boolean[][] visited = new boolean[image.length][image[0].length];
        doFloodFill(image, sr, sc, newColor, image[sr][sc], visited);

        return image;
    }

    private void doFloodFill(int[][] image, int sr, int sc, int newColor, int oldColor, boolean[][] visited) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length) {
            return;
        }

        if (visited[sr][sc]) {
            return;
        }

        if (image[sr][sc] != oldColor) {
            return;
        }

        image[sr][sc] = newColor;
        visited[sr][sc] = true;

        doFloodFill(image, sr - 1, sc, newColor, oldColor, visited);
        doFloodFill(image, sr + 1, sc, newColor, oldColor, visited);
        doFloodFill(image, sr, sc - 1, newColor, oldColor, visited);
        doFloodFill(image, sr, sc + 1, newColor, oldColor, visited);
    }
}
