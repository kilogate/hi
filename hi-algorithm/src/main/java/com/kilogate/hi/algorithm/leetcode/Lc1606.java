package com.kilogate.hi.algorithm.leetcode;

import java.util.*;

/**
 * 找到处理最多请求的服务器
 * <p>
 * https://leetcode-cn.com/problems/find-servers-that-handled-most-number-of-requests/
 *
 * @author fengquanwei
 * @create 2022/3/31 00:25
 **/
public class Lc1606 {
    public static void main(String[] args) {
        Lc1606 lc1606 = new Lc1606();
        List<Integer> res = lc1606.busiestServers(3, new int[]{1, 2, 3, 4, 5}, new int[]{5, 2, 3, 3, 3});
        System.out.println(res);
    }

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> idle = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            idle.add(i);
        }

        Queue<int[]> busy = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        int max = 0;
        int[] request = new int[k];

        for (int i = 0; i < arrival.length; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                idle.add(busy.poll()[1]);
            }

            if (idle.isEmpty()) {
                continue;
            }

            Integer j = idle.ceiling(i % k);
            if (j == null) {
                j = idle.first();
            }

            request[j]++;
            max = Math.max(max, request[j]);
            idle.remove(j);
            busy.offer(new int[]{arrival[i] + load[i], j});
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (request[i] == max) {
                res.add(i);
            }
        }

        return res;
    }
}
