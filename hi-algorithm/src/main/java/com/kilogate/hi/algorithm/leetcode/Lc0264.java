package com.kilogate.hi.algorithm.leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * 丑数 II
 * <p>
 * https://leetcode-cn.com/problems/ugly-number-ii/
 *
 * @author fengquanwei
 * @create 2022/3/9 22:33
 **/
public class Lc0264 {
    public static void main(String[] args) {
        Lc0264 lc0264 = new Lc0264();
        System.out.println(lc0264.nthUglyNumber2(1407));
    }

    /**
     * 动规实现
     *
     * @param n
     * @return
     */
    public int nthUglyNumber2(int n) {
        if (n <= 0) {
            return -1;
        }

        int[] f = new int[n];
        f[0] = 1;

        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < n; i++) {
            f[i] = Math.min(Math.min(f[p2] * 2, f[p3] * 3), f[p5] * 5);

            if (f[i] == f[p2] * 2) {
                p2++;
            }
            if (f[i] == f[p3] * 3) {
                p3++;
            }
            if (f[i] == f[p5] * 5) {
                p5++;
            }
        }

        return f[n - 1];
    }

    /**
     * 最小堆实现
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        if (n <= 0) {
            return -1;
        }

        int[] arr = new int[]{2, 3, 5};

        Set<Long> set = new HashSet<>();
        set.add(1L);

        Queue<Long> queue = new PriorityQueue<>();
        queue.offer(1L);

        long res = queue.poll();
        while (n > 1) {
            for (int i : arr) {
                if (set.add(res * i)) {
                    queue.offer(res * i);
                }
            }

            res = queue.poll();
            n--;
        }

        return (int) res;
    }
}
