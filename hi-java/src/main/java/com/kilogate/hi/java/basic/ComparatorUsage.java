package com.kilogate.hi.java.basic;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 比较器的用法
 *
 * @author kilogate
 * @create 2020/8/8 上午11:49
 **/
public class ComparatorUsage {
    public static void main(String[] args) {
        List<String> list = Stream.of("x", "y", "z", "hi", "a", "b", "c", "d", "e", null)
                .sorted(Comparator.nullsFirst(Comparator.comparing(String::length).reversed().thenComparing(Function.identity())))
                .collect(Collectors.toList());

        System.out.println(list);
    }
}
