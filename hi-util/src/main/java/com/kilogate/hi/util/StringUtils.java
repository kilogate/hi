package com.kilogate.hi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringUtils
 *
 * @author kilogate
 * @create 2020/4/8 下午4:39
 **/
public class StringUtils {
    public static final Pattern HALF_CHAR_PATTERN = Pattern.compile("[A-Za-z0-9\u0000-\u00FF\uFF1b]");
    public static final Pattern WHOLE_CHAR_PATTERN = Pattern.compile("[\u4E00-\u9FA5\uF900-\uFA2D\uFF00-\uFF1a\uFF1c-\uFFFF\u3002\u3001\u201c\u201d\u2018\u2019\u300a\u300b\u3008\u3009\u3010\u3011\u300e\u300F\u300c\u300d\uFe43\uFe44\u3014\u3015\u2026\u2014\uFe4F]");

    public static String toBinaryString(byte b) {
        return String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
    }

    public static String toBinaryString(int i) {
        return String.format("%32s", Integer.toBinaryString(i)).replace(' ', '0');
    }

    public static String toBinaryString(long l) {
        return String.format("%64s", Long.toBinaryString(l)).replace(' ', '0');
    }

    /**
     * 计算字符串长度
     */
    public static double calculateStringLength(String content) {
        if (content == null || content.length() == 0) {
            return 0;
        }

        double length = 0;

        // 英文、数字、半角符号、中文分号（\uFF1b）：长度计 0.5
        Matcher halfCharMatcher = HALF_CHAR_PATTERN.matcher(content);
        while (halfCharMatcher.find()) {
            length += 0.5;
        }

        // 中文、日文、全角符号（不包括中文分号）：长度计 1
        Matcher wholeCharMatcher = WHOLE_CHAR_PATTERN.matcher(content);
        while (wholeCharMatcher.find()) {
            length += 1;
        }

        return length;
    }

    public static void main(String[] args) {
        for (byte i = -10; i < 10; i++) {
            System.out.println(toBinaryString(i));
        }
    }
}
