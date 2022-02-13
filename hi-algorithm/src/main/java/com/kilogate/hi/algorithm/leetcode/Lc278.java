package com.kilogate.hi.algorithm.leetcode;

/**
 * 第一个错误的版本
 * <p>
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 * <p>
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * <p>
 * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 *
 * @author fengquanwei
 * @create 2022/2/13 15:45
 **/
public class Lc278 {
    private int badVersion = 1;

    public static void main(String[] args) {
        Lc278 lc278 = new Lc278();
        lc278.badVersion = 1702766719;

        int i = lc278.firstBadVersion(2126753390);
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
