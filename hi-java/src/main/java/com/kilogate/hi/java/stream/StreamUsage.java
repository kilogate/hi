package com.kilogate.hi.java.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流的用法
 *
 * @author kilogate
 * @create 2020/8/10 上午12:01
 **/
public class StreamUsage {
    public static void main(String[] args) {
        // ==================== 流的创建 ====================

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            list.add(i + "");
        }

        Stream<String> stream1 = list.stream();
        printStream("Collection.stream", stream1);

        Stream<String> parallelStream1 = list.parallelStream();
        printStream("Collection.parallelStream", parallelStream1);

        Stream<String> stream2 = Stream.of("a", "b", "c");
        printStream("Stream.of", stream2);

        Stream<Object> stream3 = Stream.empty();
        printStream("Stream.empty", stream3);

        Stream<Double> stream4 = Stream.generate(Math::random);
        printStream("Stream.generate", stream4);

        Stream<Integer> stream5 = Stream.iterate(0, n -> n + 1);
        printStream("Stream.iterate", stream5);

        String[] arr = {"a", "b", "c"};
        Stream<String> stream6 = Arrays.stream(arr, 0, 3);
        printStream("Arrays.stream", stream6);

        Stream<String> stream7 = Pattern.compile("\\PL+").splitAsStream("this is a test for create stream");
        printStream("Pattern.compile", stream7);

        try {
            Stream<String> stream8 = Files.lines(Paths.get("/Users/kilogate/a.txt"));
            printStream("Files.lines", stream8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ==================== 流的转换 ====================

        // ==================== 流的约简 ====================

    }

    private static <T> void printStream(String title, Stream<T> stream) {
        int size = 10;

        List<T> list = stream.limit(size + 1).collect(Collectors.toList());

        System.out.print(title + ": ");

        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                System.out.print(", ");
            }

            if (i < size) {
                System.out.print(list.get(i));
            } else {
                System.out.print("...");
            }
        }

        System.out.println();
    }

}
