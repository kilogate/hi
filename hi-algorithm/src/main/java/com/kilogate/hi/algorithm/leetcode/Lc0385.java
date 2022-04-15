package com.kilogate.hi.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 迷你语法分析器
 * <p>
 * https://leetcode-cn.com/problems/mini-parser/
 *
 * @author fengquanwei
 * @create 2022/4/15 17:51
 **/
public class Lc0385 {
    public static void main(String[] args) {
        Lc0385 lc0385 = new Lc0385();

        NestedInteger ni1 = lc0385.deserialize("123");
        System.out.println(ni1);
        NestedInteger ni2 = lc0385.deserialize("[123,456]");
        System.out.println(ni2);
        NestedInteger ni3 = lc0385.deserialize("[123,[456,789]]");
        System.out.println(ni3);
        NestedInteger ni4 = lc0385.deserialize("[[123,456],789]");
        System.out.println(ni4);
        NestedInteger ni5 = lc0385.deserialize("[[123,456,789]]");
        System.out.println(ni5);
    }

    public NestedInteger deserialize(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        if (str.charAt(0) != '[') {
            return new NestedInteger(Integer.valueOf(str));
        }

        NestedInteger ni = new NestedInteger();
        List<String> subStrList = getSubStrList(str.substring(1, str.length() - 1));
        for (String subStr : subStrList) {
            ni.add(deserialize(subStr));
        }

        return ni;
    }

    private List<String> getSubStrList(String str) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            if (str.charAt(i) == '[') {
                int listEndIndex = getListEndIndex(str, i);
                list.add(str.substring(i, listEndIndex + 1));
                i = listEndIndex + 2;
                continue;
            }

            int valEndIndex = getValEndIndex(str, i);
            list.add(str.substring(i, valEndIndex));
            i = valEndIndex + 1;
        }
        return list;
    }

    private int getListEndIndex(String str, int from) {
        int count = 1;
        for (int i = from + 1; i < str.length(); i++) {
            if (str.charAt(i) == '[') {
                count++;
            } else if (str.charAt(i) == ']') {
                count--;
                if (count == 0) {
                    return i;
                }
            }
        }

        return -1;
    }

    private int getValEndIndex(String str, int from) {
        for (int i = from + 1; i < str.length(); i++) {
            if (str.charAt(i) == ',') {
                return i;
            }
        }

        return str.length();
    }

    private static class NestedInteger {
        private Integer val;
        private List<NestedInteger> list;

        // Constructor initializes an empty nested list.
        private NestedInteger() {
            list = new ArrayList<>();
        }

        // Constructor initializes a single integer.
        private NestedInteger(int value) {
            val = value;
        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        private boolean isInteger() {
            return val != null;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        private Integer getInteger() {
            return val;
        }

        // Set this NestedInteger to hold a single integer.
        private void setInteger(int value) {
            val = value;
        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        private void add(NestedInteger ni) {
            list.add(ni);
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        private List<NestedInteger> getList() {
            return list;
        }

        @Override
        public String toString() {
            if (val != null) {
                return String.valueOf(val);
            }

            StringBuilder res = new StringBuilder();
            res.append("[");
            for (int i = 0; i < list.size(); i++) {
                res.append(list.get(i).toString());
                res.append(i == list.size() - 1 ? "" : ",");
            }
            res.append("]");

            return res.toString();
        }
    }
}
