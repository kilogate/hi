package com.kilogate.hi.java.concurrent.collection;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 映射的用法
 * replaceAll
 * compute
 * merge
 *
 * @author kilogate
 * @create 2020/8/1 下午4:03
 **/
public class MapUsage {
    public static void main(String[] args) {
        Map<String, String> map = Stream.of("a", "b", "c").collect(Collectors.toMap(Function.identity(), Function.identity()));
        System.out.println(map);

        map.replaceAll((k, v) -> k + v);
        System.out.println(map);

        map.compute("a", (k, v) -> k + v);
        System.out.println(map);

        map.merge("a", "A", (k, v) -> k + v);
        map.merge("d", "D", (k, v) -> k + v);
        System.out.println(map);
    }
}
