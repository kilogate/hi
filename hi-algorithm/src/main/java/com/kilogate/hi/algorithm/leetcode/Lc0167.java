package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;

/**
 * 两数之和 II - 输入有序数组
 * <p>
 * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
 * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 * 你所设计的解决方案必须只使用常量级的额外空间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author fengquanwei
 * @create 2022/2/27 20:58
 **/
public class Lc0167 {
    public static void main(String[] args) {
        Lc0167 lc0167 = new Lc0167();
        int[] res = lc0167.twoSum(new int[]{2, 3, 5, 7, 8, 99}, 9);
        System.out.println(Arrays.toString(res));
    }

    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length <= 1) {
            return new int[]{-1, -1};
        }

        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) {
                return new int[]{l + 1, r + 1};
            } else if (sum < target) {
                l++;
            } else {
                r--;
            }
        }

        return new int[]{-1, -1};
    }
}
