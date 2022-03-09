package com.kilogate.hi.algorithm.leetcode;

import java.util.*;

/**
 * N 皇后
 * <p>
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author fengquanwei
 * @create 2022/3/9 21:04
 **/
public class Lc0051 {
    public static void main(String[] args) {
        Lc0051 lc0051 = new Lc0051();
        List<List<String>> res = lc0051.solveNQueens(4);

        final int[] i = {1};
        res.stream().forEach(list -> {
            System.out.println("Solution" + (i[0]++) + ":");
            list.forEach(System.out::println);
            System.out.println();
        });
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();

        if (n <= 0) {
            return res;
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
        getSolutions(0, n, res, queens, aSet, bSet, cSet);

        return res;
    }

    private void getSolutions(int r, int n, List<List<String>> res, int[] queens, Set<Integer> aSet, Set<Integer> bSet, Set<Integer> cSet) {
        if (r == n) {
            res.add(buildSolution(queens));
            return;
        }

        for (int c = 0; c < n; c++) {
            if (aSet.contains(r + c) || bSet.contains(r - c) || cSet.contains(c)) {
                continue;
            }

            queens[r] = c;
            aSet.add(r + c);
            bSet.add(r - c);
            cSet.add(c);

            getSolutions(r + 1, n, res, queens, aSet, bSet, cSet);

            queens[r] = -1;
            aSet.remove(r + c);
            bSet.remove(r - c);
            cSet.remove(c);
        }
    }

    private List<String> buildSolution(int[] queens) {
        List<String> res = new ArrayList<>();

        for (int i = 0; i < queens.length; i++) {
            char[] line = new char[queens.length];
            Arrays.fill(line, '.');
            line[queens[i]] = 'Q';

            res.add(new String(line));
        }

        return res;
    }
}
