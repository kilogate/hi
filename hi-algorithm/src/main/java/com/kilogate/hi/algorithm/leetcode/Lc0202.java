package com.kilogate.hi.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 快乐数
 * <p>
 * https://leetcode-cn.com/problems/happy-number
 *
 * @author fengquanwei
 * @create 2022/3/3 23:15
 **/
public class Lc0202 {
    public static void main(String[] args) {
        Lc0202 lc0202 = new Lc0202();
        System.out.println(lc0202.isHappy(19));
        System.out.println(lc0202.isHappy(73));
    }

    public boolean isHappy(int num) {
        Set<Integer> cache = new HashSet<>();

        while (true) {
            if (num == 1) {
                return true;
            }

            if (cache.contains(num)) {
                return false;
            }

            cache.add(num);
            num = getNext(num);
        }
    }

    private int getNext(int num) {
        int res = 0;

        while (num != 0) {
            int n = num % 10;
            num /= 10;

            res += n * n;
        }

        return res;
    }
}
