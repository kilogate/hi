package com.kilogate.hi.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 自除数
 * <p>
 * https://leetcode-cn.com/problems/self-dividing-numbers/
 *
 * @author fengquanwei
 * @create 2022/3/31 12:52
 **/
public class Lc0728 {
    public static void main(String[] args) {
        Lc0728 lc0728 = new Lc0728();
        System.out.println(lc0728.selfDividingNumbers(1, 22));
    }

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList();

        for (int i = left; i <= right; i++) {
            if (isSelfDividNum(i)) {
                res.add(i);
            }
        }

        return res;
    }

    private boolean isSelfDividNum(int num) {
        int temp = num;
        while (temp != 0) {
            int n = temp % 10;
            if (n == 0 || num % n != 0) {
                return false;
            }
            temp /= 10;
        }

        return true;
    }
}
