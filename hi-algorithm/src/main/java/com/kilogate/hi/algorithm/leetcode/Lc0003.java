package com.kilogate.hi.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 无重复字符的最长子串
 * <p>
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * @author fengquanwei
 * @create 2022/2/19 13:43
 **/
public class Lc0003 {
    public static void main(String[] args) {
        Lc0003 lc0003 = new Lc0003();
        int result = lc0003.lengthOfLongestSubstring("abcabcd");
        System.out.println(result);
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Set<Character> set = new HashSet<>();

        // [i, j] 之间是无重复的字符集
        int i = 0, j = 0, result = 0;
        while (i < s.length()) {
            // 重复了删除第一个字符
            if (i != 0) {
                set.remove(s.charAt(i - 1));
            }

            // 不断向右移动
            while (j < s.length() && !set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                j++;
            }

            i++;
            result = Math.max(result, set.size());

            // j 到头了直接返回
            if (j == s.length()) {
                return result;
            }
        }

        return result;
    }
}
