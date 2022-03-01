package com.kilogate.hi.algorithm.leetcode;

/**
 * 字符串解码
 * <p>
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
