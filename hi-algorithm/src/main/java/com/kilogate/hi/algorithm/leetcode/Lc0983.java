package com.kilogate.hi.algorithm.leetcode;

/**
 * 最低票价
 * <p>
 * https://leetcode-cn.com/problems/minimum-cost-for-tickets/
 *
 * @author kilogate
 * @create 2022/2/26 23:53
 **/
public class Lc0983 {
    public static void main(String[] args) {
        Lc0983 lc0983 = new Lc0983();
        int ans = lc0983.mincostTickets(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31}, new int[]{2, 7, 15});
        System.out.println(ans);
    }

    public int mincostTickets(int[] days, int[] costs) {
        int[] cache = new int[days.length];
        return getMinCost(days, costs, cache, 0);
    }

    private int getMinCost(int[] days, int[] costs, int[] cache, int i) {
        if (i == days.length) {
            return 0;
        }

        if (cache[i] != 0) {
            return cache[i];
        }

        int planA = costs[0] + getMinCost(days, costs, cache, getNextIndex(days, i, 1));
        int planB = costs[1] + getMinCost(days, costs, cache, getNextIndex(days, i, 7));
        int planC = costs[2] + getMinCost(days, costs, cache, getNextIndex(days, i, 30));

        int min = Math.min(Math.min(planA, planB), planC);
        cache[i] = min;
        return min;
    }

    private int getNextIndex(int[] days, int i, int d) {
        int max = days[i] + d;

        while (i < days.length && days[i] < max) {
            i++;
        }

        return i;
    }
}
