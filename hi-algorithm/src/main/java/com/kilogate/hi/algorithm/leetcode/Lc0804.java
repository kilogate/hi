package com.kilogate.hi.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 唯一摩尔斯密码词
 * <p>
 * https://leetcode-cn.com/problems/unique-morse-code-words/
 *
 * @author kilogate
 * @create 2022/4/10 15:31
 **/
public class Lc0804 {
    private static final String[] table = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    public static void main(String[] args) {
        System.out.println(new Lc0804().uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"}));
    }

    public int uniqueMorseRepresentations(String[] words) {
        Set<String> set = new HashSet();

        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (char ch : word.toCharArray()) {
                sb.append(table[ch - 'a']);
            }
            set.add(sb.toString());
        }

        return set.size();
    }
}
