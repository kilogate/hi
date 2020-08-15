package com.kilogate.hi.java.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 基本类型流的使用
 *
 * @author kilogate
 * @create 2020/8/15 下午5:29
 **/
public class PrimitiveTypeStreamUsage extends StreamBaseUsage {
    public static void main(String[] args) {
        IntStream intStream0 = IntStream.of(1, 2, 3);
        printStream("IntStream.of", intStream0);

        IntStream intStream1 = IntStream.generate(() -> new Random().nextInt());
        printStream("IntStream.generate", intStream1);

        IntStream intStream2 = IntStream.iterate(0, x -> x + 1);
        printStream("IntStream.iterate", intStream2);

        IntStream intStream3 = IntStream.range(5, 10);
        printStream("IntStream.range", intStream3);

        IntStream intStream4 = IntStream.rangeClosed(5, 10);
        printStream("IntStream.rangeClosed", intStream4);

        Stream<Integer> integerStream = IntStream.range(0, 10).boxed();
        IntStream intStream5 = integerStream.mapToInt(Integer::intValue);
        printStream("Strem.mapToInt", intStream5);

        int[] array = IntStream.of(1, 2, 3).toArray();
        System.out.println(Arrays.toString(array));

        System.out.println(IntStream.of(1, 2, 3).sum());
        System.out.println(IntStream.of(1, 2, 3).average());
        System.out.println(IntStream.of(1, 2, 3).max());
        System.out.println(IntStream.of(1, 2, 3).min());

        IntSummaryStatistics intSummaryStatistics = IntStream.of(1, 2, 3).summaryStatistics();
        System.out.println(intSummaryStatistics);

        IntStream ints1 = new Random().ints();
        printStream("Random().ints", ints1);

        IntStream ints2 = new Random().ints(3);
        printStream("Random().ints", ints2);

        IntStream ints3 = new Random().ints(1, 7);
        printStream("Random().ints", ints3);

        IntStream ints4 = new Random().ints(5, 1, 7);
        printStream("Random().ints", ints4);

        String sentence = "\uD835\uDD46 is the set of octonions.";
        System.out.println(sentence);
        IntStream intStream = sentence.codePoints();
        System.out.println(intStream.mapToObj(c -> String.format("%X ", c)).collect(Collectors.joining()));
    }
}
