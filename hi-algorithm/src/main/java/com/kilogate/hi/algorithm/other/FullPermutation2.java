package com.kilogate.hi.algorithm.other;

/**
 * 全排列：邻里互换法
 *
 * @author fengquanwei
 * @create 2022/2/9 15:12
 **/
public class FullPermutation2 {
    public static void main(String[] args) {
        printFullPermutation("1234");
    }

    public static void printFullPermutation(String content) {
        if (content == null || content.length() <= 1) {
            System.out.println(content);
            return;
        }

        doPrintFullPermutation(content, 0, content.length() - 1);
    }

    private static void doPrintFullPermutation(String content, int start, int end) {
        if (start >= end) {
            System.out.println(content);
            return;
        }

        for (int i = start; i <= end; i++) {
            String newContent = swapChars(content, start, i);
            doPrintFullPermutation(newContent, start + 1, end);
        }
    }

    private static String swapChars(String content, int a, int b) {
        char[] chars = content.toCharArray();

        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;

        return new String(chars);
    }
}
