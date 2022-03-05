package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;

/**
 * 图像渲染
 * <p>
 * 有一幅以 m x n 的二维整数数组表示的图画 image ，其中 image[i][j] 表示该图画的像素值大小。
 * 你也被给予三个整数 sr ,  sc 和 newColor 。你应该从像素 image[sr][sc] 开始对图像进行 上色填充 。
 * 为了完成 上色工作 ，从初始像素开始，记录初始坐标的 上下左右四个方向上 像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应 四个方向上 像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为 newColor 。
 * 最后返回 经过上色渲染后的图像 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flood-fill
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author fengquanwei
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
