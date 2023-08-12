package com.kilogate.hi.algorithm.leetcode;

import com.kilogate.hi.algorithm.util.ArrayUtil;

/**
 * 盛最多水的容器
 * <p>
 * https://leetcode-cn.com/problems/container-with-most-water/
 *
 * @author kilogate
 * @create 2022/3/25 23:11
 **/
public class Lc0011 {
    public static void main(String[] args) {
        Lc0011 lc0011 = new Lc0011();
        int res = lc0011.maxArea(ArrayUtil.buildArray("[2,3,4,5,18,17,6]"));
        System.out.println(res);
    }

    public int maxArea(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }

        int res = 0, l = 0, r = height.length - 1;
        while (l < r) {
            int area = (r - l) * Math.min(height[l], height[r]);
            res = Math.max(res, area);
            if (height[l] <= height[r]) {
                l++;
            } else {
                r--;
            }
        }

        return res;
    }
}
