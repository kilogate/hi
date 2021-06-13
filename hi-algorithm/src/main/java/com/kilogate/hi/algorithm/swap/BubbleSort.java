package com.kilogate.hi.algorithm.swap;

import java.util.Arrays;

/**
 * 交换排序一：冒泡排序
 *
 * @author kilogate
 * @create 2021/6/13 22:08
 **/
public class BubbleSort {
    /**
     * 冒泡排序
     *
     * @param datas
     */
    public static void bubbleSort(int[] datas) {
        if (datas == null || datas.length <= 1) {
            return;
        }

        // 使得第 i 个元素归位
        for (int i = 0; i < datas.length - 1; i++) {
            // 从后往前冒泡，将小的元素迁移
            for (int j = datas.length - 1; j > i; j--) {
                if (datas[j] < datas[j - 1]) {
                    swap(datas, j, j - 1);
                }
            }
        }
    }

    /**
     * 冒泡排序（优化：已排好序就不再比较了）
     *
     * @param datas
     */
    public static void bubbleSort1(int[] datas) {
        if (datas == null || datas.length <= 1) {
            return;
        }

        // 使得第 i 个元素归位
        for (int i = 0; i < datas.length - 1; i++) {
            boolean exchange = false;

            // 从后往前冒泡，将小的元素迁移
            for (int j = datas.length - 1; j > i; j--) {
                if (datas[j] < datas[j - 1]) {
                    exchange = true;
                    swap(datas, j, j - 1);
                }
            }

            // 已排好序就不再比较了
            if (!exchange) {
                return;
            }
        }
    }

    private static void swap(int[] datas, int i, int j) {
        int temp = datas[i];
        datas[i] = datas[j];
        datas[j] = temp;
    }

    public static void main(String[] args) {
//        int[] datas = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] datas = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};

        bubbleSort1(datas);

        System.out.println(Arrays.toString(datas));
    }
}
