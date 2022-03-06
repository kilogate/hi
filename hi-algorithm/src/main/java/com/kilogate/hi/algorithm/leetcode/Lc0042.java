package com.kilogate.hi.algorithm.leetcode;

/**
 * 接雨水
 * <p>
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * @author fengquanwei
 * @create 2022/3/6 17:00
 **/
public class Lc0042 {
    public static void main(String[] args) {
        Lc0042 lc0042 = new Lc0042();
        int res = lc0042.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        System.out.println(res);
    }

    public int trap2(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax < rightMax) {
                ans += leftMax - height[left];
                ++left;
            } else {
                ans += rightMax - height[right];
                --right;
            }
        }
        return ans;
    }

    public int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }

        int[] left = new int[height.length];
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            max = Math.max(max, height[i]);
            left[i] = max;
        }

        int[] right = new int[height.length];
        max = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            max = Math.max(max, height[i]);
            right[i] = max;
        }

        int res = 0;
        for (int i = 1; i < height.length - 1; i++) {
            res += Math.max(Math.min(left[i], right[i]) - height[i], 0);
        }

        return res;
    }
}
