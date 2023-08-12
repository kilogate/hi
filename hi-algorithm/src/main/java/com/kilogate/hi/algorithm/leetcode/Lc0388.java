package com.kilogate.hi.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件的最长绝对路径
 * <p>
 * https://leetcode-cn.com/problems/longest-absolute-file-path/
 *
 * @author kilogate
 * @create 2022/4/20 12:09
 **/
public class Lc0388 {
    public static void main(String[] args) {
        Lc0388 lc0388 = new Lc0388();
        int ans = lc0388.lengthLongestPath("file1.txt\nfile2.txt\nlongfile.txt");
        System.out.println(ans);
    }

    public int lengthLongestPath(String input) {
        input = input + "\n";

        int ans = 0;
        Map<Integer, Integer> dirLength = new HashMap();
        StringBuilder sb = new StringBuilder();
        int depth = 0;
        for (char ch : input.toCharArray()) {
            if (ch == '\n') {
                String file = sb.toString();
                if (file.contains(".")) {
                    int length = depth == 0 ? file.length() : dirLength.get(depth - 1) + 1 + file.length();
                    ans = Math.max(ans, length);
                } else {
                    int length = depth == 0 ? file.length() : dirLength.get(depth - 1) + 1 + file.length();
                    dirLength.put(depth, length);
                }
                sb = new StringBuilder();
                depth = 0;
            } else if (ch == '\t') {
                depth++;
            } else {
                sb.append(ch);
            }
        }

        return ans;
    }
}
