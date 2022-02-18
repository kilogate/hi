package com.kilogate.hi.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序 - 直接插入排序
 *
 * @author kilogate
 * @create 2021/6/13 16:07
 **/
public class Sort01 {
    /**
     * 直接插入排序
     *
     * @param data
     */
    public static void insertSort(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }

        // 向有序区直接插入第 i 个元素
        for (int i = 1; i < data.length; i++) {
            // 本元素有序，下一个
            if (data[i] >= data[i - 1]) {
                continue;
            }

            // 从后向前找插入位置，边找边后移元素
            int temp = data[i];
            int j = i - 1;
            do {
                data[j + 1] = data[j];
                j--;
            } while (j >= 0 && data[j] > temp);

            // 在 j + 1 位置处插入元素
            data[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
//        int[] data = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] data = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};

        insertSort(data);

        System.out.println(Arrays.toString(data));
    }
}
