package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;

/**
 * 按奇偶排序数组 II
 * <p>
 * 给定一个非负整数数组 nums，  nums 中一半整数是 奇数 ，一半整数是 偶数 。
 * 对数组进行排序，以便当 nums[i] 为奇数时，i 也是 奇数 ；当 nums[i] 为偶数时， i 也是 偶数 。
 * 你可以返回 任何满足上述条件的数组作为答案 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author fengquanwei
 * @create 2022/3/3 01:01
 **/
public class Lc0922 {
    public static void main(String[] args) {
        Lc0922 lc0922 = new Lc0922();
        int[] res = lc0922.sortArrayByParityII(new int[]{2, 3, 1, 1, 4, 0, 0, 4, 3, 3});
        System.out.println(Arrays.toString(res));
    }

    public int[] sortArrayByParityII(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            boolean iMatch = i % 2 == nums[i] % 2;
            boolean jMatch = j % 2 == nums[j] % 2;
            if (iMatch && jMatch) {
                i++;
                j--;
            } else if (iMatch && !jMatch) {
                i++;
            } else if (!iMatch && jMatch) {
                j--;
            } else {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }

        return nums;
    }
}
