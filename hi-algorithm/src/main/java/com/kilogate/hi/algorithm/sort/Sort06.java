package com.kilogate.hi.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序 - 简单选择排序
 *
 * @author kilogate
 * @create 2021/6/13 23:14
 **/
public class Sort06 {
    /**
     * 简单选择排序
     *
     * @param data
     */
    public static void selectSort(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }

        // 选择第 i 个最小的元素
        for (int i = 0; i < data.length - 1; i++) {
            // 找最小元素的下标
            int k = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[k]) {
                    k = j;
                }
            }

            // 移动最小元素
            if (k != i) {
                swap(data, k, i);
            }
        }
    }

    private static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void main(String[] args) {
        int[] data = new int[]{6, 7, 8, 9, 0, 1, 3, 2, 4, 5};

        selectSort(data);

        System.out.println(Arrays.toString(data));
    }
}
