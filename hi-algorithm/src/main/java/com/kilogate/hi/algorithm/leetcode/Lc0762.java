package com.kilogate.hi.algorithm.leetcode;

/**
 * 二进制表示中质数个计算置位
 *
 * @author fengquanwei
 * @create 2022/4/10 22:34
 **/
public class Lc0762 {
    public static void main(String[] args) {
        int ans = new Lc0762().countPrimeSetBits(100, 200);
        System.out.println(ans);
    }

    public int countPrimeSetBits(int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            if (((1 << Integer.bitCount(i)) & 665772) != 0) {
                ans++;
            }
        }
        return ans;
    }
}
