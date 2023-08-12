package com.kilogate.hi.algorithm.leetcode;

/**
 * 寻找两个正序数组的中位数
 * <p>
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 *
 * @author kilogate
 * @create 2022/3/28 00:23
 **/
public class Lc0004 {
    public static void main(String[] args) {
        Lc0004 lc0004 = new Lc0004();
        double res = lc0004.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
        System.out.println(res);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;

        int k = (m + n + 1) / 2;
        if ((m + n) % 2 == 1) {
            return getKthValue(nums1, nums2, k);
        } else {
            return (getKthValue(nums1, nums2, k) + getKthValue(nums1, nums2, k + 1)) / 2.0;
        }
    }

    private double getKthValue(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;

        // 已排除的值的下标
        int i = -1;
        int j = -1;

        // 寻找结果
        while (true) {
            if (i + 1 == m) {
                return nums2[j + k];
            }

            if (j + 1 == n) {
                return nums1[i + k];
            }

            if (k == 1) {
                return Math.min(nums1[i + 1], nums2[j + 1]);
            }

            int t = k / 2;
            int newI = Math.min(i + t, m - 1);
            int newJ = Math.min(j + t, n - 1);

            if (nums1[newI] <= nums2[newJ]) {
                k -= newI - i;
                i = newI;
            } else {
                k -= newJ - j;
                j = newJ;
            }
        }
    }
}
