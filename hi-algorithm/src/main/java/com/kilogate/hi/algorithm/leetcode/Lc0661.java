package com.kilogate.hi.algorithm.leetcode;

import com.kilogate.hi.algorithm.util.ArrayUtil;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 图片平滑器
 * <p>
 * https://leetcode-cn.com/problems/image-smoother/
 *
 * @author kilogate
 * @create 2022/3/24 22:19
 **/
public class Lc0661 {
    public static void main(String[] args) {
        Lc0661 lc0661 = new Lc0661();
        int[][] res = lc0661.imageSmoother(ArrayUtil.buildBinaryArray("[[100,200,100],[200,50,200],[100,200,100]]"));
        Stream.of(res).map(Arrays::toString).forEach(System.out::println);
    }

    public int[][] imageSmoother(int[][] img) {
        if (img == null || img.length == 0 || img[0].length == 0) {
            return null;
        }

        int m = img.length, n = img[0].length;
        int[][] res = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int sum = 0, count = 0;
                for (int x = i - 1; x <= i + 1; x++) {
                    for (int y = j - 1; y <= j + 1; y++) {
                        if (x >= 0 && x < m && y >= 0 && y < n) {
                            sum += img[x][y];
                            count++;
                        }
                    }
                }
                res[i][j] = sum / count;
            }
        }

        return res;
    }
}
