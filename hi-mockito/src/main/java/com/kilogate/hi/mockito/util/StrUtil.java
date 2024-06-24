package com.kilogate.hi.mockito.util;

/**
 * DateUtil
 *
 * @author fengquanwei
 * @create 2024/6/24 17:51
 **/
public class StrUtil {
    public static int getStrLength(String str) {
        if (str == null) {
            return 0;
        }

        return str.length();
    }
}
