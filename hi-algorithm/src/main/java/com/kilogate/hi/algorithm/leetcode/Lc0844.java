package com.kilogate.hi.algorithm.leetcode;

/**
 * 比较含退格的字符串
 * <p>
 * 给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 true 。# 代表退格字符。
 * 注意：如果对空文本输入退格字符，文本继续为空。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/backspace-string-compare
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author fengquanwei
 * @create 2022/3/4 00:14
 **/
public class Lc0844 {
    public static void main(String[] args) {
        Lc0844 lc0844 = new Lc0844();
        System.out.println(lc0844.backspaceCompare("nzp#o#g", "b#nzp#o#g"));
    }

    public boolean backspaceCompare(String str1, String str2) {
        boolean blank1 = str1 == null || str1.length() == 0;
        boolean blank2 = str2 == null || str2.length() == 0;

        if (blank1 && blank2) {
            return true;
        }

        if (blank1 || blank2) {
            return false;
        }

        int i = str1.length() - 1, j = str2.length() - 1;
        while (i >= 0 || j >= 0) {
            i = getPrevIndex(str1, i);
            j = getPrevIndex(str2, j);

            if (i < 0 || j < 0) {
                break;
            }

            if (str1.charAt(i) != str2.charAt(j)) {
                return false;
            }

            i--;
            j--;
        }

        if (i < 0 && j < 0) {
            return true;
        }

        return false;
    }

    private int getPrevIndex(String str, int i) {
        int skip = 0;
        while (i >= 0) {
            if (str.charAt(i) == '#') {
                skip++;
                i--;
            } else if (skip != 0) {
                skip--;
                i--;
            } else {
                return i;
            }
        }

        return i;
    }
}
