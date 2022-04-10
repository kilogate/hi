package com.kilogate.hi.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 计数质数
 * <p>
 * https://leetcode-cn.com/problems/count-primes/
 *
 * @author fengquanwei
 * @create 2022/4/10 22:01
 **/
public class Lc0204 {
    public static void main(String[] args) {
        Lc0204 lc0204 = new Lc0204();
        System.out.println(lc0204.countPrimes1(499979));
        System.out.println(lc0204.countPrimes2(499979));
    }

    /**
     * 埃氏筛
     *
     * @param n
     * @return
     */
    public int countPrimes1(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;

                for (int j = i + i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        return count;
    }

    /**
     * 线性筛
     *
     * @param n
     * @return
     */
    public int countPrimes2(int n) {
        List<Integer> primes = new ArrayList<>();
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }

            for (int j = 0; j < primes.size(); j++) {
                if (i * primes.get(j) >= n) {
                    break;
                }

                isPrime[i * primes.get(j)] = false;
                if (i % primes.get(j) == 0) {
                    break;
                }
            }
        }

        return primes.size();
    }
}
