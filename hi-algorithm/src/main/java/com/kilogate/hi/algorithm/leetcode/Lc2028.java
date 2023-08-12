package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;

/**
 * 找出缺失的观测数据
 * <p>
 * https://leetcode-cn.com/problems/find-missing-observations/
 *
 * @author kilogate
 * @create 2022/2/26 23:13
 **/
public class Lc2028 {
    public static void main(String[] args) {
        Lc2028 lc2028 = new Lc2028();
        int[] res = lc2028.missingRolls(new int[]{3, 2, 4, 3}, 4, 2);
        System.out.println(Arrays.toString(res));
    }

    public int[] missingRolls(int[] rolls, int mean, int n) {
        int sum = 0;
        for (int roll : rolls) {
            sum += mean - roll;
        }
        sum += mean * n;

        if (sum < n || sum > n * 6) {
            return new int[0];
        }

        int[] res = new int[n];

        int quotient = sum / n;
        int remainder = sum % n;
        for (int i = 0; i < n; i++) {
            res[i] = i < remainder ? quotient + 1 : quotient;
        }

        return res;
    }
}
