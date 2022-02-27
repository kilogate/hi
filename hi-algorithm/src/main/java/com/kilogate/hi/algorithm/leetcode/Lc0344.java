package com.kilogate.hi.algorithm.leetcode;

/**
 * 反转字符串
 * <p>
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author fengquanwei
 * @create 2022/2/27 21:43
 **/
public class Lc0344 {
    public static void main(String[] args) {
        Lc0344 lc0344 = new Lc0344();
        char[] s = "Hello".toCharArray();
        lc0344.reverseString(s);
        System.out.println(new String(s));
    }

    public void reverseString(char[] s) {
        if (s == null || s.length <= 1) {
            return;
        }

        int n = s.length / 2;
        for (int i = 0; i < n; i++) {
            int j = s.length - 1 - i;

            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }
    }
}
