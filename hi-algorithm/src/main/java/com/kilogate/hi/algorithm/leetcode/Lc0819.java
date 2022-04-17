package com.kilogate.hi.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 最常见的单词
 * <p>
 * https://leetcode-cn.com/problems/most-common-word/
 *
 * @author fengquanwei
 * @create 2022/4/17 15:09
 **/
public class Lc0819 {
    public static void main(String[] args) {
        String ans = new Lc0819().mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"});
        System.out.println(ans);
    }

    public String mostCommonWord(String paragraph, String[] banned) {
        String ans = "";
        Map<String, Integer> countMap = new HashMap();
        countMap.put(ans, 0);
        Set<String> bannedSet = Stream.of(banned).collect(Collectors.toSet());
        paragraph = paragraph + " ";

        StringBuilder sb = new StringBuilder();
        for (char ch : paragraph.toCharArray()) {
            if (Character.isLetter(ch)) {
                sb.append(ch);
                continue;
            }

            String word = sb.toString().toLowerCase();
            sb = new StringBuilder();

            if (word.length() > 0) {
                if (bannedSet.contains(word)) {
                    continue;
                }

                Integer count = countMap.getOrDefault(word, 0) + 1;
                countMap.put(word, count);

                if (count > countMap.get(ans)) {
                    ans = word;
                }
            }
        }

        return ans;
    }
}
