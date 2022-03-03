package com.kilogate.hi.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 快乐数
 * <p>
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * 「快乐数」 定义为：
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/happy-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
