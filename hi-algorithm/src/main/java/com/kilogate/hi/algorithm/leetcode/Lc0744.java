package com.kilogate.hi.algorithm.leetcode;

/**
 * 寻找比目标字母大的最小字母
 * <p>
 * https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target/
 *
 * @author kilogate
 * @create 2022/4/11 23:17
 **/
public class Lc0744 {
    public static void main(String[] args) {
        System.out.println(new Lc0744().nextGreatestLetter(new char[]{'a', 'b', 'c', 'd', 'e'}, 'b'));
    }

    public char nextGreatestLetter(char[] letters, char target) {
        if (letters[letters.length - 1] <= target) {
            return letters[0];
        }

        int l = 0, r = letters.length - 1;
        while (l < r) {
            int m = (r - l) / 2 + l;

            if (letters[m] <= target) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        return letters[l];
    }
}
