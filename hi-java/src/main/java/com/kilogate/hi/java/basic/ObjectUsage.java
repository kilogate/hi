package com.kilogate.hi.java.basic;

import java.util.Arrays;
import java.util.Objects;

/**
 * Object 类的用法
 *
 * @author kilogate
 * @create 2020/8/8 上午11:05
 **/
public class ObjectUsage {
    public static void main(String[] args) {
        hashCodeUsage();
    }

    private static void equalsUsage() {
        // 基本类型
        System.out.println(5 == 6);

        // 引用类型
        System.out.println(Objects.equals(new String("a"), null));

        // 数组类型
        System.out.println(Arrays.equals(new String[]{"a", "c"}, new String[]{"a", "c"}));
    }

    private static void hashCodeUsage() {
        // 基本类型
        System.out.println(Integer.hashCode(1));

        // 引用类型
        System.out.println(Objects.hashCode(new String("abc")));

        // 数组类型
        System.out.println(Arrays.hashCode(new int[]{1, 2, 3}));

        // 组合类型
        System.out.println(Objects.hash(1, 2, 3, "a", "b", "c", new int[]{1, 2, 3}));
    }
}
