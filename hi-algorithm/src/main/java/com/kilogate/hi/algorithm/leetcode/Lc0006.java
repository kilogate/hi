package com.kilogate.hi.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Z 字形变换
 * <p>
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author fengquanwei
 * @create 2022/2/19 22:29
 **/
public class Lc0006 {
    public static void main(String[] args) {
        Lc0006 lc0006 = new Lc0006();
        String res = lc0006.convert("ABCDEFG", 3);
        System.out.println(res);
    }

    public String convert(String s, int numRows) {
        if (s == null || s.length() <= 1 || numRows <= 1) {
            return s;
        }

        List<StringBuilder> list = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }

        int i = 0;
        boolean down = true;
        for (char c : s.toCharArray()) {
            list.get(i).append(c);

            i = down ? i + 1 : i - 1;

            if (i == 0 || i == numRows - 1) {
                down = !down;
            }
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder sb : list) {
            res.append(sb);
        }

        return res.toString();
    }
}
