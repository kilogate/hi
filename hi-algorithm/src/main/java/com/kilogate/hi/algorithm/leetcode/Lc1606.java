package com.kilogate.hi.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

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
        PriorityQueue<Integer> idle = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            idle.offer(i);
        }

        Queue<int[]> busy = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        int max = 0;
        int[] request = new int[k];

        for (int i = 0; i < arrival.length; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                int id = busy.poll()[1];
                idle.offer(i + ((id - i) % k + k) % k);
            }

            if (idle.isEmpty()) {
                continue;
            }

            int j = idle.poll() % k;
            request[j]++;
            max = Math.max(max, request[j]);
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
