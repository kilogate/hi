package com.kilogate.hi.algorithm.leetcode;

/**
 * 给定数字能组成的最大时间
 * <p>
 * https://leetcode-cn.com/problems/largest-time-for-given-digits/
 *
 * @author kilogate
 * @create 2022/3/14 17:38
 **/
public class Lc0949 {
    public static void main(String[] args) {
        Lc0949 lc0949 = new Lc0949();
        String res = lc0949.largestTimeFromDigits(new int[]{5, 5, 5, 5});
        System.out.println(res);
    }

    public String largestTimeFromDigits(int[] arr) {
        if (arr == null || arr.length != 4) {
            return "";
        }

        String[] res = new String[]{""};
        int[] max = new int[]{-1};

        getTime(arr, res, max, 0);

        return res[0];
    }

    private void getTime(int[] arr, String[] res, int[] max, int i) {
        if (i == 4) {
            int minutes = getMinutes(arr);
            if (minutes > max[0]) {
                max[0] = minutes;
                res[0] = getSolution(arr);
            }
            return;
        }

        for (int j = i; j < 4; j++) {
            swap(arr, i, j);
            getTime(arr, res, max, i + 1);
            swap(arr, j, i);
        }
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private int getMinutes(int[] arr) {
        int hours = arr[0] * 10 + arr[1];
        if (hours > 23) {
            return -1;
        }

        int minutes = arr[2] * 10 + arr[3];
        if (minutes > 59) {
            return -1;
        }

        return hours * 60 + minutes;
    }

    private String getSolution(int[] arr) {
        StringBuilder res = new StringBuilder();
        res.append(arr[0]);
        res.append(arr[1]);
        res.append(":");
        res.append(arr[2]);
        res.append(arr[3]);
        return res.toString();
    }
}
