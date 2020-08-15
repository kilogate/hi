package com.kilogate.hi.java.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 流的创建
 *
 * @author kilogate
 * @create 2020/8/15 上午11:35
 **/
public class StreamCreationUsage extends StreamBaseUsage {
    private static final Pattern PL_PATTERN = Pattern.compile("\\PL+");

    public static void main(String[] args) {
        Stream<String> stream1 = generateList().stream();
        printStream("Collection.stream", stream1);

        Stream<String> parallelStream1 = generateList().parallelStream();
        printStream("Collection.parallelStream", parallelStream1);

        Stream<String> stream2 = Stream.of("a", "b", "c");
        printStream("Stream.of", stream2);

        Stream<Object> stream3 = Stream.empty();
        printStream("Stream.empty", stream3);

        Stream<Double> stream4 = Stream.generate(Math::random);
        printStream("Stream.generate", stream4);

        Stream<Integer> stream5 = Stream.iterate(0, n -> n + 1);
        printStream("Stream.iterate", stream5);

        Stream<String> stream6 = Arrays.stream(new String[]{"a", "b", "c"}, 0, 3);
        printStream("Arrays.stream", stream6);

        Stream<String> stream7 = PL_PATTERN.splitAsStream("this is a test for create stream");
        printStream("Pattern.compile", stream7);

        try {
            Stream<String> stream8 = Files.lines(Paths.get("/tmp/a.txt"));
            printStream("Files.lines", stream8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List generateList() {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            list.add(i + "");
        }

        return list;
    }
}
