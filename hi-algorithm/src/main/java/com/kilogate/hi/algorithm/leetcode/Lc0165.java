package com.kilogate.hi.algorithm.leetcode;

/**
 * 比较版本号
 * <p>
 * https://leetcode-cn.com/problems/compare-version-numbers/
 *
 * @author fengquanwei
 * @create 2022/4/29 17:59
 **/
public class Lc0165 {
    public static void main(String[] args) {
        System.out.println(new Lc0165().compareVersion("001.1.0000.1", "1.1.0.1"));
    }

    public int compareVersion(String version1, String version2) {
        int m = version1.length(), n = version2.length();
        int i = 0, j = 0;

        while (i < m || j < n) {
            int x = 0;
            while (i < m && version1.charAt(i) != '.') {
                x = x * 10 + version1.charAt(i) - '0';
                i++;
            }
            i++;

            int y = 0;
            while (j < n && version2.charAt(j) != '.') {
                y = y * 10 + version2.charAt(j) - '0';
                j++;
            }
            j++;

            if (x > y) {
                return 1;
            } else if (x < y) {
                return -1;
            }
        }

        return 0;
    }
}
