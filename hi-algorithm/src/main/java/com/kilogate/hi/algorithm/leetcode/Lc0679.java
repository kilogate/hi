package com.kilogate.hi.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        boolean ans = lc0679.judgePoint24(new int[]{6, 6, 8, 6});
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
                            num = Math.abs(list.get(j)) < 1E-6 ? Double.NaN : list.get(i) / list.get(j);
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

        Map<Double, List<String>> map = new HashMap<>();
        int i = 0;
        while (i < plan.size()) {
            double x = Double.parseDouble(plan.get(i++));
            String operation = plan.get(i++);
            double y = Double.parseDouble(plan.get(i++));

            StringBuilder sb = new StringBuilder();
            if (map.containsKey(x) && map.get(x).size() > 0) {
                sb.append("(" + map.get(x).get(0) + ")");
                map.get(x).remove(0);
            } else {
                sb.append((int) x + "");
            }
            sb.append(" " + operation + " ");
            if (map.containsKey(y) && map.get(y).size() > 0) {
                sb.append("(" + map.get(y).get(0) + ")");
                map.get(y).remove(0);
            } else {
                sb.append((int) y + "");
            }
            double res = calRes(x, operation, y);
            if (map.containsKey(res)) {
                map.get(res).add(sb.toString());
            } else {
                List<String> subPlanList = new ArrayList<>();
                subPlanList.add(sb.toString());
                map.put(res, subPlanList);
            }
        }

        System.out.println(map.get(list.get(0)).get(0) + " = " + list.get(0));
    }

    private double calRes(double x, String operation, double y) {
        switch (operation) {
            case "+":
                return x + y;
            case "-":
                return x - y;
            case "*":
                return x * y;
            case "/":
                return Math.abs(y) < 1E-6 ? Double.NaN : x / y;
        }

        return Double.NaN;
    }
}
