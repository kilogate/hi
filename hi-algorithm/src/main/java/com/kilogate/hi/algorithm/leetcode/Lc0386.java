package com.kilogate.hi.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典序排数
 * <p>
 * https://leetcode-cn.com/problems/lexicographical-numbers/
 *
 * @author fengquanwei
 * @create 2022/4/18 14:27
 **/
public class Lc0386 {
    public static void main(String[] args) {
        Lc0386 lc0386 = new Lc0386();
        System.out.println(lc0386.lexicalOrder2(192));
        System.out.println(lc0386.lexicalOrder2(7));
        System.out.println(lc0386.lexicalOrder2(12));
        System.out.println(lc0386.lexicalOrder2(123));
        System.out.println(lc0386.lexicalOrder2(1234));
    }

    /**
     * 方法一
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder1(int n) {
        List<Integer> list = new ArrayList();

        for (int i = 1; i <= 9; i++) {
            doLexicalOrder(i, n, list);
        }

        return list;
    }

    /**
     * 方法二：深度优先搜索
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder2(int n) {
        List<Integer> list = new ArrayList();
        int num = 1;
        for (int i = 1; i <= n; i++) {
            list.add(num);
            if (num * 10 <= n) {
                num *= 10;
            } else {
                while (num % 10 == 9 || num + 1 > n) {
                    num /= 10;
                }
                num++;
            }
        }

        return list;
    }

    private void doLexicalOrder(int num, int max, List<Integer> list) {
        if (num > max) {
            return;
        }

        list.add(num);

        for (int i = 0; i <= 9; i++) {
            if (num * 10 + i > max) {
                break;
            }

            doLexicalOrder(num * 10 + i, max, list);
        }
    }
}
