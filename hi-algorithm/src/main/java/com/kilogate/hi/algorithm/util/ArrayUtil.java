package com.kilogate.hi.algorithm.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * ArrayUtil
 *
 * @author fengquanwei
 * @create 2022/3/24 22:22
 **/
public class ArrayUtil {
    private static final Pattern ARRAY_PATTERN = Pattern.compile("\\[.*?\\]");

    public static void main(String[] args) {
        System.out.println(Arrays.toString(buildArray("[]")));
        System.out.println(Arrays.toString(buildArray("[100,200,100]")));

        int[][] res = buildBinaryArray("[[100,200,100],[200,50,200],[100,200,100]]");
        System.out.println(Arrays.deepToString(res));
    }

    public static int[] buildArray(String str) {
        if (str == null || str.length() < 2) {
            return null;
        }

        str = str.substring(1, str.length() - 1);
        if (str.length() == 0) {
            return new int[0];
        }

        return Stream.of(str.split(",")).mapToInt(Integer::parseInt).toArray();
    }

    public static int[][] buildBinaryArray(String str) {
        if (str == null || str.length() < 2) {
            return null;
        }

        str = str.substring(1, str.length() - 1);
        if (str.length() == 0) {
            return new int[0][];
        }

        Matcher matcher = ARRAY_PATTERN.matcher(str);
        List<int[]> arrList = new ArrayList<>();
        while (matcher.find()) {
            String array = matcher.group();
            arrList.add(buildArray(array));
        }

        return arrList.toArray(new int[0][]);
    }
}
