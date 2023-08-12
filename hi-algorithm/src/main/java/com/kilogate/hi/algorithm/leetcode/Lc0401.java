package com.kilogate.hi.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二进制手表
 * <p>
 * https://leetcode-cn.com/problems/binary-watch/
 *
 * @author kilogate
 * @create 2022/3/12 14:46
 **/
public class Lc0401 {
    public static void main(String[] args) {
        Lc0401 lc0401 = new Lc0401();
        List<String> res = lc0401.readBinaryWatch(3);
        System.out.println(res);
    }

    public List<String> readBinaryWatch(int turnedOn) {
        if (turnedOn < 0 || turnedOn > 8) {
            return new ArrayList();
        }

        List<String> res = new ArrayList<>();

        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    String padding = m < 10 ? "0" : "";
                    res.add(h + ":" + padding + m);
                }
            }
        }

        return res;
    }
}
