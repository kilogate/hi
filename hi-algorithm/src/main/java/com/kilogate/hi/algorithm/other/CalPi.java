package com.kilogate.hi.algorithm.other;

/**
 * 计算圆周率
 *
 * @author kilogate
 * @create 2022/3/27 00:33
 **/
public class CalPi {
    public static void main(String[] args) {
        System.out.println(calPi());
    }

    public static double calPi() {
        double re = 0;

        for (int i = 1; i < 10000; i++) {
            re += ((i & 1) == 0 ? -1 : 1) * 1.0 / (2 * i - 1);
        }

        return re * 4;
    }
}
