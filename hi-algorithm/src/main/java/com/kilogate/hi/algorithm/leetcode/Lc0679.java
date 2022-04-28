package com.kilogate.hi.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 24 点游戏
 * <p>
 * https://leetcode-cn.com/problems/24-game/
 *
 * @author fengquanwei
 * @create 2022/4/28 23:38
 **/
public class Lc0679 {
    public static void main(String[] args) {
        Lc0679 lc0679 = new Lc0679();
        boolean ans = lc0679.judgePoint24(new int[]{1, 2, 1, 2});
        System.out.println(ans);
    }

    public boolean judgePoint24(int[] cards) {
        List<Double> list = new ArrayList<>();
        for (int num : cards) {
            list.add((double) num);
        }
        return isPoint24(list, new ArrayList<>(), new int[]{0});
    }

    private boolean isPoint24(List<Double> list, List<String> plan, int[] idx) {
        if (list.size() == 1 || list.get(0) == Double.NaN) {
            printPlan(list, plan, idx);
            return Math.abs(list.get(0) - 24) < 1E-6;
        }

        for (int i = 0; i < list.size(); i++) { // 第一个数的下标
            for (int j = 0; j < list.size(); j++) { // 第二个数的下标
                if (i == j) {
                    continue; // 两数不能一样
                }

                double num = 0; // 新数
                for (int m = 0; m < 4; m++) { // 运算符的下标
                    plan.add(list.get(i) + "");
                    switch (m) {
                        case 0:
                            num = list.get(i) + list.get(j);
                            plan.add("+");
                            break;
                        case 1:
                            num = list.get(i) - list.get(j);
                            plan.add("-");
                            break;
                        case 2:
                            num = list.get(i) * list.get(j);
                            plan.add("*");
                            break;
                        case 3:
                            num = list.get(j) == 0 ? Double.NaN : list.get(i) / list.get(j);
                            plan.add("/");
                            break;
                    }
                    plan.add(list.get(j) + "");

                    List<Double> newList = new ArrayList<>();
                    newList.add(num);
                    for (int n = 0; n < list.size(); n++) {
                        if (n == i || n == j) {
                            continue;
                        }
                        newList.add(list.get(n));
                    }

                    if (isPoint24(newList, plan, idx)) {
                        return true;
                    }

                    // 删除这一次的选择
                    plan.remove(plan.size() - 1);
                    plan.remove(plan.size() - 1);
                    plan.remove(plan.size() - 1);
                }
            }
        }

        return false;
    }

    private void printPlan(List<Double> list, List<String> plan, int[] idx) {
        idx[0]++;
        System.out.println("Plan" + idx[0] + ":");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < plan.size(); i++) {
            if (i != 0 && i % 3 == 0) {
                sb.append(", ");
            }
            sb.append(plan.get(i)).append(" ");
        }
        sb.append(", ").append(list.get(0));

        System.out.println(sb);
    }
}
