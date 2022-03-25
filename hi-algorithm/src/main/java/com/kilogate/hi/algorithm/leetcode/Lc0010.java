package com.kilogate.hi.algorithm.leetcode;

/**
 * 正则表达式匹配
 * <p>
 * https://leetcode-cn.com/problems/regular-expression-matching/
 *
 * @author fengquanwei
 * @create 2022/3/26 00:00
 **/
public class Lc0010 {
    public static void main(String[] args) {
        Lc0010 lc0010 = new Lc0010();
        boolean res = lc0010.isMatch("aab", "c*a*b");
        System.out.println(res);
    }

    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        return isMatch(s, p, s.length() - 1, p.length() - 1);
    }

    private boolean isMatch(String s, String p, int i, int j) {
        if (i < 0) {
            if (j == -1) {
                return true;
            }

            return p.charAt(j) == '*' && isMatch(s, p, i, j - 2);
        }

        if (j < 0) {
            return false;
        }

        if (p.charAt(j) != '*') {
            if (!isEqual(s, p, i, j)) {
                return false;
            } else {
                return isMatch(s, p, i - 1, j - 1);
            }
        } else {
            if (!isEqual(s, p, i, j - 1)) {
                return isMatch(s, p, i, j - 2);
            } else {
                return isMatch(s, p, i, j - 2) || isMatch(s, p, i - 1, j);
            }
        }
    }

    private boolean isEqual(String s, String p, int i, int j) {
        if (p.charAt(j) == '.') {
            return true;
        }

        return s.charAt(i) == p.charAt(j);
    }
}
