package com.kilogate.hi.java.stream;

import java.util.stream.Stream;

/**
 * 流的转换
 *
 * @author kilogate
 * @create 2020/8/15 上午11:45
 **/
public class StreamTransformUsage extends StreamBaseUsage {
    public static void main(String[] args) {
        printStream("Stream.filter", Stream.of("Lask", "Jennifer").filter(name -> name.length() > 4));
        printStream("Stream.map", Stream.of("Lask", "Jennifer").map(name -> name.toUpperCase()));
        printStream("Stream.flatMap", Stream.of("Lask", "Jennifer").flatMap(name -> Stream.of(name.split(""))));

        System.out.println();

        printStream("Stream.limit", Stream.of("abcdefg".split("")).limit(5));
        printStream("Stream.skip", Stream.of("abcdefg".split("")).skip(3));
        printStream("Stream.concat", Stream.concat(Stream.of("abcdefg".split("")), Stream.of("hijklmn".split(""))));

        System.out.println();

        printStream("Stream.distinct", Stream.of("aabbcc".split("")).distinct());
        printStream("Stream.sorted", Stream.of("cba".split("")).sorted());
        printStream("Stream.peek", Stream.of("cba".split("")).peek(System.out::println));
    }
}
