package com.kilogate.hi.algorithm.sort.insert;

import java.util.Arrays;

/**
 * 插入排序一：直接插入排序
 *
 * @author kilogate
 * @create 2021/6/13 16:07
 **/
public class InsertSort {
    /**
     * 直接插入排序
     *
     * @param datas
     */
    public static void insertSort(int[] datas) {
        if (datas == null || datas.length <= 1) {
            return;
        }

        // 向有序区直接插入第 i 个元素
        for (int i = 1; i < datas.length; i++) {
            // 本元素有序，下一个
            if (datas[i] >= datas[i - 1]) {
                continue;
            }

            // 从后向前找插入位置，边找边后移元素
            int temp = datas[i];
            int j = i - 1;
            do {
                datas[j + 1] = datas[j];
                j--;
            } while (j >= 0 && datas[j] > temp);

            // 在 j + 1 位置处插入元素
            datas[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
//        int[] datas = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] datas = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};

        insertSort(datas);

        System.out.println(Arrays.toString(datas));
    }
}
