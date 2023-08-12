package com.kilogate.hi.algorithm.leetcode;

/**
 * 考试的最大困扰度
 * <p>
 * https://leetcode-cn.com/problems/maximize-the-confusion-of-an-exam/
 *
 * @author kilogate
 * @create 2022/3/30 00:15
 **/
public class Lc2024 {
    public static void main(String[] args) {
        Lc2024 lc2024 = new Lc2024();
        int res = lc2024.maxConsecutiveAnswers("TTTTTFFFFTTTTTTT", 2);
        System.out.println(res);
    }

    public int maxConsecutiveAnswers(String answerKey, int k) {
        int tCount = 0, fCount = 0, i = 0, j = 0, max = 0;

        while (j < answerKey.length()) {
            if (answerKey.charAt(j) == 'T') {
                tCount++;
            } else {
                fCount++;
            }

            if (k >= Math.min(tCount, fCount)) {
                max = Math.max(max, j - i + 1);
                j++;
                continue;
            }

            while (k < Math.min(tCount, fCount)) {
                if (answerKey.charAt(i) == 'T') {
                    tCount--;
                } else {
                    fCount--;
                }
                i++;
            }
            j++;
        }

        return max;
    }
}
