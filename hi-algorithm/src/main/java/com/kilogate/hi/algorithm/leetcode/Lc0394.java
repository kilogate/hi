package com.kilogate.hi.algorithm.leetcode;

/**
 * 字符串解码
 * <p>
 * https://leetcode-cn.com/problems/decode-string
 *
 * @author fengquanwei
 * @create 2022/3/2 00:19
 */
public class Lc0394 {
    public static void main(String[] args) {
        Lc0394 lc0394 = new Lc0394();
        System.out.println(lc0394.decodeString("10[a]2[bc]"));
        System.out.println(lc0394.decodeString("3[a2[c]]"));
        System.out.println(lc0394.decodeString("2[abc]3[cd]ef"));
        System.out.println(lc0394.decodeString("abc3[cd]xyz"));
    }

    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            while (!Character.isDigit(s.charAt(i))) {
                res.append(s.charAt(i));

                if (++i == s.length()) {
                    return res.toString();
                }
            }

            StringBuilder countStr = new StringBuilder();
            while (Character.isDigit(s.charAt(i))) {
                countStr.append(s.charAt(i++));
            }
            i++;

            StringBuilder subStr = new StringBuilder();
            int n = 1;
            while (n != 0) {
                if (s.charAt(i) == ']') {
                    if (--n == 0) {
                        break;
                    }
                } else if (s.charAt(i) == '[') {
                    n++;
                }

                subStr.append(s.charAt(i++));
            }

            int count = Integer.parseInt(countStr.toString());
            res.append(concatString(count, decodeString(subStr.toString())));
        }

        return res.toString();
    }

    private String concatString(int count, String str) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < count; i++) {
            res.append(str);
        }

        return res.toString();
    }
}
