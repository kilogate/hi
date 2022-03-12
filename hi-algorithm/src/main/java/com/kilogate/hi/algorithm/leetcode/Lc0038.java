package com.kilogate.hi.algorithm.leetcode;

/**
 * 外观数列
 * <p>
 * https://leetcode-cn.com/problems/count-and-say/
 *
 * @author fengquanwei
 * @create 2022/2/14 15:49
 **/
public class Lc0038 {
    public static void main(String[] args) {
        Lc0038 lc0038 = new Lc0038();
        String result = lc0038.countAndSay(4);
        System.out.println(result);
    }

    public String countAndSay(int n) {
        String content = "1";

        if (n <= 1) {
            return content;
        }

        for (int i = 0; i < n - 1; i++) {
            content = getNextLine(content);
        }

        return content;
    }

    private String getNextLine(String content) {
        StringBuilder result = new StringBuilder();

        char[] chars = content.toCharArray();
        char lastChar = chars[0];
        int lastCount = 1;

        // 遍历所有字符
        for (int i = 1; i < chars.length; i++) {
            char currChar = chars[i];
            if (lastChar == currChar) {
                lastCount++;
                continue;
            }

            result.append(lastCount).append(lastChar);
            lastChar = currChar;
            lastCount = 1;
        }

        result.append(lastCount).append(lastChar);

        return result.toString();
    }
}
