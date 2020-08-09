package com.kilogate.hi.java.concurrent.collection;

import java.util.Arrays;
import java.util.Random;

/**
 * 数组工具类的用法
 * parallelSort
 * parallelSetAll
 * parallelPrefix
 *
 * @author kilogate
 * @create 2020/8/1 下午4:06
 **/
public class ArraysUsage {
    public static void main(String[] args) {
        // 测试排序
//        for (int i = 1; i < 10000000; i *= 2) {
//            testSort(i);
//        }

        int[] arr = new Random().ints(100, 1, 100).toArray();
        Arrays.parallelSetAll(arr, i -> i);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        Arrays.parallelPrefix(arr, (left, right) -> left + right);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 测试排序
     */
    private static void testSort(int n) {
        int[] arr1 = new Random().ints(n).toArray();
        int[] arr2 = Arrays.copyOf(arr1, arr1.length);

        long t1 = System.currentTimeMillis();

        // 串行排序
        Arrays.sort(arr1);

        long t2 = System.currentTimeMillis();

        // 并发排序
        Arrays.parallelSort(arr2);

        long t3 = System.currentTimeMillis();

        System.out.println(String.format("n: %d, sort: %d, parallelSort: %d", n, t2 - t1, t3 - t2));
    }
}
