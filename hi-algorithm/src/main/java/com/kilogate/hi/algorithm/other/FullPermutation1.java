package com.kilogate.hi.algorithm.other;

/**
 * 全排列：回溯法
 *
 * @author fengquanwei
 * @create 2022/2/9 14:29
 **/
public class FullPermutation1 {
    public static void main(String[] args) {
        printFullPermutation("1234");
    }

    public static void printFullPermutation(String content) {
        if (content == null || content.length() <= 1) {
            System.out.println(content);
            return;
        }

        doPrintFullPermutation("", content);
    }

    private static void doPrintFullPermutation(String prefix, String content) {
        if (content.length() == 1) {
            System.out.println(prefix + content);
            return;
        }

        for (int i = 0; i < content.length(); i++) {
            String newPrefix = prefix + content.charAt(i);
            String newContent = getContent(content, i);
            doPrintFullPermutation(newPrefix, newContent);
        }
    }

    private static String getContent(String content, int index) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < content.length(); i++) {
            if (i != index) {
                result.append(content.charAt(i));
            }
        }

        return result.toString();
    }
}
