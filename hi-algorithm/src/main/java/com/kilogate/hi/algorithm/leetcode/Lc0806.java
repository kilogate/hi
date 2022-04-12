package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;

/**
 * 写字符串需要的行数
 * <p>
 * https://leetcode-cn.com/problems/number-of-lines-to-write-string/
 *
 * @author fengquanwei
 * @create 2022/4/12 23:30
 **/
public class Lc0806 {
    public static void main(String[] args) {
        Lc0806 lc0806 = new Lc0806();
        int[] res = lc0806.numberOfLines(new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, "abcdefghijklmnopqrstuvwxyz");
        System.out.println(Arrays.toString(res));
    }

    public int[] numberOfLines(int[] widths, String s) {
        int lines = 1, count = 0;

        for (char ch : s.toCharArray()) {
            int incr = widths[ch - 'a'];
            if (count + incr <= 100) {
                count += incr;
            } else {
                count = incr;
                lines++;
            }
        }

        return new int[]{lines, count};
    }
}
