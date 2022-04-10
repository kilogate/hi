package com.kilogate.hi.algorithm.leetcode;

/**
 * 到达终点
 * <p>
 * https://leetcode-cn.com/problems/reaching-points/
 *
 * @author fengquanwei
 * @create 2022/4/10 15:59
 **/
public class Lc0780 {
    public static void main(String[] args) {
        Lc0780 lc0780 = new Lc0780();
        System.out.println(lc0780.reachingPoints(9, 5, 12, 8));
        System.out.println(lc0780.reachingPoints(1, 1, 3, 5));
        System.out.println(lc0780.reachingPoints(9, 10, 9, 19));
        System.out.println(lc0780.reachingPoints(1, 1, 10000, 1));
    }

    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) {
            return true;
        }

        if (sx > tx || sy > ty) {
            return false;
        }

        if (tx > ty) {
            int x = tx % ty + sx / ty * ty;
            return x == tx ? false : reachingPoints(sx, sy, x, ty);
        } else {
            int y = ty % tx + sy / tx * tx;
            return y == ty ? false : reachingPoints(sx, sy, tx, y);
        }
    }
}
