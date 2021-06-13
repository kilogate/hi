package com.kilogate.hi.algorithm.sort.insert;

import java.util.Arrays;

/**
 * 插入排序三：希尔排序
 *
 * @author kilogate
 * @create 2021/6/13 16:48
 **/
public class ShellSort {
    /**
     * 希尔排序
     *
     * @param datas
     */
    public static void shellSort(int[] datas) {
        if (datas == null || datas.length <= 1) {
            return;
        }

        // 初始增量
        int d = datas.length / 2;

        while (d > 0) {
            // 分组向有序区直接插入第 i 个元素
            for (int i = d; i < datas.length; i++) {

                // 直接插入排序
                int temp = datas[i];
                int j = i - d;
                while (j >= 0 && temp < datas[j]) {
                    datas[j + d] = datas[j];
                    j = j - d;
                }

                datas[j + d] = temp;
            }

            // 减少增量
            d = d / 2;
        }
    }

    public static void main(String[] args) {
//        int[] datas = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] datas = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};

        shellSort(datas);

        System.out.println(Arrays.toString(datas));
    }
}
