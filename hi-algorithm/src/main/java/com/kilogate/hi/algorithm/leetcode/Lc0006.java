package com.kilogate.hi.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Z 字形变换
 * <p>
 * https://leetcode-cn.com/problems/zigzag-conversion
 *
 * @author kilogate
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
