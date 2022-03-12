package com.kilogate.hi.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 字母大小写全排列
 * <p>
 * https://leetcode-cn.com/problems/letter-case-permutation/
 *
 * @author fengquanwei
 * @create 2022/3/12 22:06
 **/
public class Lc0784 {
    public static void main(String[] args) {
        Lc0784 lc0784 = new Lc0784();
        List<String> res = lc0784.letterCasePermutation("a1b2");
        System.out.println(res);
    }

    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList();

        getPermutation(res, "", s, 0);

        return res;
    }

    private void getPermutation(List<String> res, String sub, String s, int i) {
        if (i == s.length()) {
            res.add(sub);
            return;
        }

        char c = s.charAt(i);
        if (c < 'A' || c > 'z') {
            getPermutation(res, sub + c, s, i + 1);
        } else {
            getPermutation(res, sub + c, s, i + 1);
            getPermutation(res, sub + convert(c), s, i + 1);
        }
    }

    private char convert(char c) {
        if (c <= 'Z') {
            return (char) (c + 32);
        } else {
            return (char) (c - 32);
        }
    }
}
