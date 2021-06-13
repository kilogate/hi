package com.kilogate.hi.algorithm.sort.select;

import java.util.Arrays;

/**
 * 选择排序一：简单选择排序
 *
 * @author kilogate
 * @create 2021/6/13 23:14
 **/
public class SelectSort {
    /**
     * 简单选择排序
     *
     * @param datas
     */
    public static void selectSort(int[] datas) {
        if (datas == null || datas.length <= 1) {
            return;
        }

        // 选择第 i 个最小的元素
        for (int i = 0; i < datas.length - 1; i++) {
            // 找最小元素的下标
            int k = i;
            for (int j = i + 1; j < datas.length; j++) {
                if (datas[j] < datas[k]) {
                    k = j;
                }
            }

            // 移动最小元素
            if (k != i) {
                swap(datas, k, i);
            }
        }
    }

    private static void swap(int[] datas, int i, int j) {
        int temp = datas[i];
        datas[i] = datas[j];
        datas[j] = temp;
    }

    public static void main(String[] args) {
        int[] datas = new int[]{6, 7, 8, 9, 0, 1, 3, 2, 4, 5};

        selectSort(datas);

        System.out.println(Arrays.toString(datas));
    }
}
