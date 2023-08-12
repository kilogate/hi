package com.kilogate.hi.algorithm.leetcode;

/**
 * 旋转字符串
 *
 * @author kilogate
 * @create 2022/4/10 21:40
 **/
public class Lc0796 {
    public static void main(String[] args) {
        System.out.println(new Lc0796().rotateString("abcdefg", "defgabc"));
    }

    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (isRotateString(s, goal, i)) {
                return true;
            }
        }

        return false;
    }

    private boolean isRotateString(String s, String goal, int i) {
        for (int j = 0; j < goal.length(); j++) {
            if (s.charAt(i) != goal.charAt(j)) {
                return false;
            }

            i = (i + 1) % s.length();
        }

        return true;
    }
}
