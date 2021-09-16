package com.kilogate.hi.algorithm.sort.s4;

import java.util.Arrays;

/**
 * 交换排序 - 冒泡排序
 *
 * @author kilogate
 * @create 2021/6/13 22:08
 **/
public class BubbleSort {
    /**
     * 冒泡排序
     *
     * @param data
     */
    public static void bubbleSort(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }

        // 使得第 i 个元素归位
        for (int i = 0; i < data.length - 1; i++) {
            // 从后往前冒泡，将小的元素迁移
            for (int j = data.length - 1; j > i; j--) {
                if (data[j] < data[j - 1]) {
                    swap(data, j, j - 1);
                }
            }
        }
    }

    /**
     * 冒泡排序（优化：已排好序就不再比较了）
     *
     * @param data
     */
    public static void bubbleSort1(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }

        // 使得第 i 个元素归位
        for (int i = 0; i < data.length - 1; i++) {
            boolean exchange = false;

            // 从后往前冒泡，将小的元素迁移
            for (int j = data.length - 1; j > i; j--) {
                if (data[j] < data[j - 1]) {
                    exchange = true;
                    swap(data, j, j - 1);
                }
            }

            // 已排好序就不再比较了
            if (!exchange) {
                return;
            }
        }
    }

    private static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void main(String[] args) {
//        int[] data = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] data = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};

        bubbleSort1(data);

        System.out.println(Arrays.toString(data));
    }
}
