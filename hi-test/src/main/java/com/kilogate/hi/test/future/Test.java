package com.kilogate.hi.test.future;

/**
 * Test
 *
 * @author kilogate
 * @create 2022/1/10 12:58
 **/
public class Test {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {5, 6, 7};
        double result = findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1 == null ? 0 : nums1.length;
        int length2 = nums2 == null ? 0 : nums2.length;
        int totalLength = length1 + length2;

        if (totalLength == 0) {
            return 0;
        }

        if (length1 == 0) {
            return length2 % 2 == 0 ? (nums2[length2 / 2 - 1] + nums2[length2 / 2]) / 2.0 : nums2[length2 / 2];
        }

        if (length2 == 0) {
            return length1 % 2 == 0 ? (nums1[length1 / 2 - 1] + nums1[length1 / 2]) / 2.0 : nums1[length1 / 2];
        }

        int currIndex = 0;
        int midIndex = totalLength / 2;

        int index1 = 0;
        int index2 = 0;

        int prev = 0;
        int curr = 0;

        while (currIndex <= midIndex) {
            if (index1 == length1) {
                prev = curr;
                curr = nums2[index2];

                currIndex++;
                index2++;

                continue;
            }

            if (index2 == length2) {
                prev = curr;
                curr = nums1[index1];

                currIndex++;
                index1++;

                continue;
            }

            if (nums1[index1] <= nums2[index2]) {
                prev = curr;
                curr = nums1[index1];

                currIndex++;
                index1++;
            } else {
                prev = curr;
                curr = nums2[index2];

                currIndex++;
                index2++;
            }
        }

        return totalLength % 2 == 0 ? (prev + curr) / 2.0 : curr;
    }
}
