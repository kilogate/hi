package com.kilogate.hi.algorithm.sort.insert;

import java.util.Arrays;

/**
 * 插入排序二：折半插入排序
 *
 * @author kilogate
 * @create 2021/6/13 16:31
 **/
public class BinInsertSort {
    /**
     * 折半插入排序
     *
     * @param datas
     */
    public static void binInsertSort(int[] datas) {
        if (datas == null || datas.length <= 1) {
            return;
        }

        // 向有序区折半插入第 i 个元素
        for (int i = 1; i < datas.length; i++) {
            // 本元素有序，下一个
            if (datas[i] >= datas[i - 1]) {
                continue;
            }

            // 折半查找找插入位置
            int temp = datas[i];
            int low = 0;
            int high = i - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (temp < datas[mid]) { // 左半区
                    high = mid - 1;
                } else { // 右半区（为了保证稳定性，相等时放到右半区）
                    low = mid + 1;
                }
            }

            // 集中进行元素后移
            for (int j = i - 1; j >= high + 1; j--) {
                datas[j + 1] = datas[j];
            }

            // 在 high + 1 位置处插入元素（high + 1 是上一次的 mid 位置）
            datas[high + 1] = temp;
        }
    }

    public static void main(String[] args) {
        //        int[] datas = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] datas = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};

        binInsertSort(datas);

        System.out.println(Arrays.toString(datas));
    }
}
