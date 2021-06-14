package com.kilogate.hi.algorithm.sort.swap;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author kilogate
 * @create 2021/6/13 22:38
 **/
public class QuickSort {
    /**
     * 快速排序
     *
     * @param data
     */
    public static void quickSort(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }

        doQuickSort(data, 0, data.length - 1);
    }

    private static void doQuickSort(int[] data, int s, int t) {
        if (s >= t) {
            return;
        }

        // 一趟划分
        int i = partition(data, s, t);

        // 递归排序左区间
        doQuickSort(data, s, i - 1);

        // 递归排序右区间
        doQuickSort(data, i + 1, t);
    }

    /**
     * 一趟划分
     *
     * @param data
     * @param s
     * @param t
     * @return
     */
    private static int partition(int[] data, int s, int t) {
        int i = s;
        int j = t;

        // 设第一个元素为基准元素
        int temp = data[i];

        while (i < j) {
            // 找小于基准元素的
            while (j > i && data[j] >= temp) {
                j--;
            }

            // 小于基准元素的前移
            data[i] = data[j];

            // 找大于基准元素的
            while (i < j && data[i] <= temp) {
                i++;
            }

            // 大于基准元素的后移
            data[j] = data[i];
        }

        // 基准元素归位
        data[i] = temp;

        // 返回基准元素的位置，此时 i == j
        return i;
    }

    public static void main(String[] args) {
        int[] data = new int[]{6, 7, 8, 9, 0, 1, 3, 2, 4, 5};

        quickSort(data);

        System.out.println(Arrays.toString(data));
    }
}
