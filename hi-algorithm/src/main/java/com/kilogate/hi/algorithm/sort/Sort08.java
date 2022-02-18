package com.kilogate.hi.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序 - 二路归并排序
 *
 * @author kilogate
 * @create 2021/6/14 17:56
 **/
public class Sort08 {
    /**
     * 归并排序
     *
     * @param data
     */
    public static void mergeSort(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }

        int n = data.length;

        for (int length = 1; length < n; length = length * 2) {
            mergePass(data, length);
        }
    }

    /**
     * 一趟归并
     *
     * @param data   待归并表
     * @param length 子表长度
     */
    private static void mergePass(int[] data, int length) {
        int n = data.length;

        // 归并 length 长的两相邻子表
        int i;
        for (i = 0; i + 2 * length - 1 < n; i = i + 2 * length) {
            merge(data, i, i + length - 1, i + 2 * length - 1);
        }

        // 归并剩余的子表（剩余一个不用归并，剩余两个第二个子表长度不够 length）
        if (i + length - 1 < n - 1) {
            merge(data, i, i + length - 1, n - 1);
        }
    }

    /**
     * 归并 data[low,mid] 和 data[mid+1,high]
     *
     * @param data
     * @param low
     * @param mid
     * @param high
     */
    private static void merge(int[] data, int low, int mid, int high) {
        int[] newData = new int[data.length];

        // data[low,mid] 的索引
        int i = low;

        // data[mid+1,high] 的索引
        int j = mid + 1;

        // newData 的索引
        int k = 0;

        // 合并第一段和第二段
        while (i <= mid && j <= high) {
            if (data[i] <= data[j]) {
                newData[k] = data[i];
                i++;
                k++;
            } else {
                newData[k] = data[j];
                j++;
                k++;
            }
        }

        // 复制第一段剩余部分
        while (i <= mid) {
            newData[k] = data[i];
            i++;
            k++;
        }

        // 复制第二段剩余部分
        while (j <= high) {
            newData[k] = data[j];
            j++;
            k++;
        }

        // 将 newData 复制到 data 中
        for (k = 0, i = low; i <= high; k++, i++) {
            data[i] = newData[k];
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

        mergeSort(data);

        System.out.println(Arrays.toString(data));
    }
}
