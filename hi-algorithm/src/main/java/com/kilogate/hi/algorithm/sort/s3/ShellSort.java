package com.kilogate.hi.algorithm.sort.s3;

import java.util.Arrays;

/**
 * 插入排序 - 希尔排序
 *
 * @author kilogate
 * @create 2021/6/13 16:48
 **/
public class ShellSort {
    /**
     * 希尔排序
     *
     * @param data
     */
    public static void shellSort(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }

        // 初始增量
        int d = data.length / 2;

        while (d > 0) {
            // 分组向有序区直接插入第 i 个元素
            for (int i = d; i < data.length; i++) {

                // 直接插入排序
                int temp = data[i];
                int j = i - d;
                while (j >= 0 && temp < data[j]) {
                    data[j + d] = data[j];
                    j = j - d;
                }

                data[j + d] = temp;
            }

            // 减少增量
            d = d / 2;
        }
    }

    public static void main(String[] args) {
//        int[] data = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] data = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};

        shellSort(data);

        System.out.println(Arrays.toString(data));
    }
}
