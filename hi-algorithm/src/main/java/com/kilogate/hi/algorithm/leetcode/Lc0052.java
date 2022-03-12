package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * N皇后 II
 * <p>
 * https://leetcode-cn.com/problems/n-queens-ii
 *
 * @author fengquanwei
 * @create 2022/3/9 22:04
 **/
public class Lc0052 {
    public static void main(String[] args) {
        Lc0052 lc0052 = new Lc0052();
        int count = lc0052.totalNQueens(4);
        System.out.println(count);
    }

    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }

        // 每一行皇后所在的列
        int[] queens = new int[n];
        Arrays.fill(queens, -1);

        // 正斜线，行列之和（r + c），取值范围：0 到 2n - 2
        Set<Integer> aSet = new HashSet<>();
        // 反斜线，行列只差（r - c），取值范围：负的 n- 1 到 正的 n - 1
        Set<Integer> bSet = new HashSet<>();
        // 列，取值范围（c）：0 到 n - 1
        Set<Integer> cSet = new HashSet<>();

        // 回溯法求所有解
        return getSolutions(0, n, queens, aSet, bSet, cSet);
    }

    private int getSolutions(int r, int n, int[] queens, Set<Integer> aSet, Set<Integer> bSet, Set<Integer> cSet) {
        if (r == n) {
            return 1;
        }

        int count = 0;

        for (int c = 0; c < n; c++) {
            if (aSet.contains(r + c) || bSet.contains(r - c) || cSet.contains(c)) {
                continue;
            }

            queens[r] = c;
            aSet.add(r + c);
            bSet.add(r - c);
            cSet.add(c);

            count += getSolutions(r + 1, n, queens, aSet, bSet, cSet);

            queens[r] = -1;
            aSet.remove(r + c);
            bSet.remove(r - c);
            cSet.remove(c);
        }

        return count;
    }
}
