package com.kilogate.hi.algorithm.sort.select;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author kilogate
 * @create 2021/6/14 16:30
 **/
public class HeapSort {
    /**
     * 堆排序
     *
     * @param datas
     */
    public static void heapSort(int[] datas) {
        if (datas == null || datas.length <= 1) {
            return;
        }

        int n = datas.length;

        // 第一个分支结点索引
        int k = n / 2 - 1;

        // 建立初始堆：从第一个分支结点开始，从下向上建立初始堆
        for (int i = k; i >= 0; i--) {
            sift(datas, i, n - 1);
        }

        // 反复重建堆（进行 n - 1 趟完成堆排序，每一趟归位一个元素）
        for (int i = n - 1; i >= 1; i--) {
            // 将最后一个元素和根交换，归位根元素
            swap(datas, 0, i);
            // 对 [1, i-1] 筛选，得到新的大根堆
            sift(datas, 0, i - 1);
        }
    }

    /**
     * 筛选（从上向下筛选，调整为大根堆）
     *
     * @param datas
     * @param low
     * @param high
     */
    private static void sift(int[] datas, int low, int high) {
        // 根结点索引
        int i = low;
        // 左孩子结点索引
        int j = 2 * i + 1;

        // 根结点的值
        int temp = datas[i];

        // 向下筛选
        while (j <= high) {
            // 让 j 指向值最大的孩子结点（j + 1 是右孩子结点索引）
            if (j + 1 <= high && datas[j] < datas[j + 1]) {
                j = j + 1;
            }

            // 根结点的值小于孩子结点的值
            if (temp < datas[j]) {
                // 交换根结点与孩子结点的值
                datas[i] = datas[j];

                // 筛选该孩子结点
                i = j;
                j = 2 * i + 1;
            } else {
                // 根结点的值最大，不用向下筛选了
                break;
            }
        }

        // 被筛选的结点放入最终的位置上
        datas[i] = temp;
    }

    private static void swap(int[] datas, int i, int j) {
        int temp = datas[i];
        datas[i] = datas[j];
        datas[j] = temp;
    }

    public static void main(String[] args) {
        int[] datas = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

        heapSort(datas);

        System.out.println(Arrays.toString(datas));
    }
}
