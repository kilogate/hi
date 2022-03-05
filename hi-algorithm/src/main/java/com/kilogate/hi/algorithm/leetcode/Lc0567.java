package com.kilogate.hi.algorithm.leetcode;

import java.util.Arrays;

/**
 * 字符串的排列
 * <p>
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 * <p>
 * 提示：
 * 1 <= s1.length, s2.length <= 104
 * s1 和 s2 仅包含小写字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-in-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author fengquanwei
 * @create 2022/3/5 22:14
 **/
public class Lc0567 {
    public static void main(String[] args) {
        Lc0567 lc0567 = new Lc0567();
        boolean res = lc0567.checkInclusion("adc", "dcda");
        System.out.println(res);
    }

    public boolean checkInclusion(String str1, String str2) {
        if (str1 == null || str1.length() == 0) {
            return true;
        }

        if (str2 == null || str2.length() < str1.length()) {
            return false;
        }

        int[] stat1 = new int[26];
        int[] stat2 = new int[26];

        for (char c : str1.toCharArray()) {
            stat1[c - 'a']++;
        }

        for (int i = 0; i < str2.length(); i++) {
            stat2[str2.charAt(i) - 'a']++;

            if (i >= str1.length()) {
                stat2[str2.charAt(i - str1.length()) - 'a']--;
            }

            if (i >= str1.length() - 1 && Arrays.equals(stat1, stat2)) {
                return true;
            }
        }

        return false;
    }
}
