package com.kilogate.hi.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 棒球比赛
 * <p>
 * https://leetcode-cn.com/problems/baseball-game/
 *
 * @author kilogate
 * @create 2022/3/26 11:54
 **/
public class Lc0682 {
    public static void main(String[] args) {
        Lc0682 lc0682 = new Lc0682();
        System.out.println(lc0682.calPoints(new String[]{"5", "2", "C", "D", "+"}));
    }

    public int calPoints(String[] ops) {
        if (ops == null || ops.length == 0) {
            return 0;
        }

        int sum = 0;
        List<Integer> points = new ArrayList<>();
        for (String op : ops) {
            int n = points.size();
            int num = 0;
            switch (op) {
                case "+":
                    num = points.get(n - 1) + points.get(n - 2);
                    sum += num;
                    points.add(num);
                    break;
                case "D":
                    num = points.get(n - 1) * 2;
                    sum += num;
                    points.add(num);
                    break;
                case "C":
                    num = points.get(n - 1);
                    sum -= num;
                    points.remove(n - 1);
                    break;
                default:
                    num = Integer.parseInt(op);
                    sum += num;
                    points.add(num);
            }
        }

        return sum;
    }
}
