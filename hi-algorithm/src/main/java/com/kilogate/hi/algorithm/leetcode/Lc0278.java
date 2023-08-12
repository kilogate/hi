package com.kilogate.hi.algorithm.leetcode;

/**
 * 第一个错误的版本
 * <p>
 * https://leetcode-cn.com/problems/first-bad-version/
 *
 * @author kilogate
 * @create 2022/2/13 15:45
 **/
public class Lc0278 {
    private int badVersion = 1;

    public static void main(String[] args) {
        Lc0278 lc0278 = new Lc0278();
        lc0278.badVersion = 1702766719;

        int i = lc0278.firstBadVersion(2126753390);
        System.out.println(i);
    }

    public int firstBadVersion(int n) {
        if (n <= 0) {
            return -1;
        }

        int left = 1;
        int right = n;

        while (left < right) {
            int mid = (right - left) / 2 + left;

            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean isBadVersion(int version) {
        if (version >= badVersion) {
            return true;
        } else {
            return false;
        }
    }
}
