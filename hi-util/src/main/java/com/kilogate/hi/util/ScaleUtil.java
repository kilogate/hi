package com.kilogate.hi.util;

/**
 * 进制工具类
 *
 * @author kilogate
 * @create 2021/7/3 12:40
 **/
public class ScaleUtil {
    private static final int SCALE62 = 62;
    private static final String CHARS_FOR_SCALE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final int MAGIC = 1234567;

    public static String toScale62(Long num) {
        if (num == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        int remainder;
        while (num > SCALE62 - 1) {
            remainder = Long.valueOf(num % SCALE62).intValue();

            sb.append(CHARS_FOR_SCALE62.charAt(remainder));

            num = num / SCALE62;
        }

        sb.append(CHARS_FOR_SCALE62.charAt(Long.valueOf(num).intValue()));
        return sb.reverse().toString();
    }

    public static Long fromScale62(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        long num = 0;

        // 删除填充的前置 0 字符
        str = str.replace("^0*", "");

        int index;
        for (int i = 0; i < str.length(); i++) {
            index = CHARS_FOR_SCALE62.indexOf(str.charAt(i));

            num += (long) (index * (Math.pow(SCALE62, str.length() - i - 1)));
        }

        return num;
    }

    /**
     * 带魔法的62进制转换（num 不能大于 Integer.MAX_VALUE - MAGIC）
     *
     * @param num
     * @return
     */
    public static String toScale62WithMagic(Long num) {
        if (num == null) {
            return null;
        }

        return toScale62(Integer.MAX_VALUE - MAGIC - num);
    }

    /**
     * 带魔法的62进制转换（num 不能大于 Integer.MAX_VALUE - MAGIC）
     *
     * @param str
     * @return
     */
    public static Long fromScale62WithMagic(String str) {
        Long num = fromScale62(str);

        if (num == null) {
            return null;
        }

        return Integer.MAX_VALUE - MAGIC - num;
    }

    public static void main(String[] args) {
        for (long i = 0; i < Integer.MAX_VALUE; i += 10000000) {
            String str = toScale62WithMagic(i);
            long num = fromScale62WithMagic(str);

            System.out.printf("%s, %s, %s%n", i, str, num);
        }
    }
}
