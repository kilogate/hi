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
     * @param datas
     */
    public static void quickSort(int[] datas) {
        if (datas == null || datas.length <= 1) {
            return;
        }

        doQuickSort(datas, 0, datas.length - 1);
    }

    private static void doQuickSort(int[] datas, int s, int t) {
        if (s >= t) {
            return;
        }

        // 一趟划分
        int i = partition(datas, s, t);

        // 递归排序左区间
        doQuickSort(datas, s, i - 1);

        // 递归排序右区间
        doQuickSort(datas, i + 1, t);
    }

    /**
     * 一趟划分
     *
     * @param datas
     * @param s
     * @param t
     * @return
     */
    private static int partition(int[] datas, int s, int t) {
        int i = s;
        int j = t;

        // 设第一个元素为基准元素
        int temp = datas[i];

        while (i < j) {
            // 找小于基准元素的
            while (j > i && datas[j] >= temp) {
                j--;
            }

            // 小于基准元素的前移
            datas[i] = datas[j];

            // 找大于基准元素的
            while (i < j && datas[i] <= temp) {
                i++;
            }

            // 大于基准元素的后移
            datas[j] = datas[i];
        }

        // 基准元素归位
        datas[i] = temp;

        // 返回基准元素的位置，此时 i == j
        return i;
    }

    public static void main(String[] args) {
        int[] datas = new int[]{6, 7, 8, 9, 0, 1, 3, 2, 4, 5};

        quickSort(datas);

        System.out.println(Arrays.toString(datas));
    }
}
