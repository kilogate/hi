package com.kilogate.hi.algorithm.leetcode;

/**
 * 最富有客户的资产总量
 * <p>
 * https://leetcode-cn.com/problems/richest-customer-wealth/
 *
 * @author kilogate
 * @create 2022/4/16 12:38
 **/
public class Lc1672 {
    public int maximumWealth(int[][] accounts) {
        int max = 0;
        for (int[] arr : accounts) {
            int sum = 0;
            for (int account : arr) {
                sum += account;
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}
