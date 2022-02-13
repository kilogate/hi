package com.kilogate.hi.algorithm.other;

/**
 * 打印统计行信息
 *
 * 1
 * 11
 * 21
 * 1211
 * 111221
 * 312211
 *
 * @author fengquanwei
 * @create 2022/2/9 14:09
 **/
public class StatLine {
    public static void main(String[] args) {
        printStatLines("1", 10);
    }

    public static void printStatLines(String content, int lines) {
        if (content == null || content.length() == 0) {
            return;
        }

        for (int i = 0; i < lines; i++) {
            System.out.println(content);
            content = getNextLine(content);
        }
    }

    private static String getNextLine(String content) {
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
