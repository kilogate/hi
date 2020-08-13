package com.kilogate.hi.java.stream;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
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

//        List<String> list = new ArrayList<>();
//
//        for (int i = 0; i < 15; i++) {
//            list.add(i + "");
//        }
//
//        Stream<String> stream1 = list.stream();
//        printStream("Collection.stream", stream1);
//
//        Stream<String> parallelStream1 = list.parallelStream();
//        printStream("Collection.parallelStream", parallelStream1);
//
//        Stream<String> stream2 = Stream.of("a", "b", "c");
//        printStream("Stream.of", stream2);
//
//        Stream<Object> stream3 = Stream.empty();
//        printStream("Stream.empty", stream3);
//
//        Stream<Double> stream4 = Stream.generate(Math::random);
//        printStream("Stream.generate", stream4);
//
//        Stream<Integer> stream5 = Stream.iterate(0, n -> n + 1);
//        printStream("Stream.iterate", stream5);
//
//        String[] arr = {"a", "b", "c"};
//        Stream<String> stream6 = Arrays.stream(arr, 0, 3);
//        printStream("Arrays.stream", stream6);
//
//        Stream<String> stream7 = Pattern.compile("\\PL+").splitAsStream("this is a test for create stream");
//        printStream("Pattern.compile", stream7);
//
//        try {
//            Stream<String> stream8 = Files.lines(Paths.get("/Users/kilogate/a.txt"));
//            printStream("Files.lines", stream8);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // ==================== 流的转换 ====================

//        printStream("Stream.filter", Stream.of("Lask", "Jennifer").filter(name -> name.length() > 4));
//        printStream("Stream.map", Stream.of("Lask", "Jennifer").map(name -> name.toUpperCase()));
//        printStream("Stream.flatMap", Stream.of("Lask", "Jennifer").flatMap(name -> Stream.of(name.split(""))));
//        printStream("Stream.limit", Stream.of("abcdefg".split("")).limit(5));
//        printStream("Stream.limit", Stream.of("abcdefg".split("")).skip(3));
//        printStream("Stream.limit", Stream.concat(Stream.of("abcdefg".split("")), Stream.of("hijklmn".split(""))));
//        printStream("Stream.distinct", Stream.of("aabbcc".split("")).distinct());
//        printStream("Stream.sorted", Stream.of("cba".split("")).sorted());
//        printStream("Stream.peek", Stream.of("cba".split("")).peek(System.out::println));

        // ==================== 流的约简 ====================

//        System.out.println(String.format("Stream.count: %s", Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).count()));
//        System.out.println(String.format("Stream.max: %s", Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).max(String::compareTo).orElse("default")));
//        System.out.println(String.format("Stream.min: %s", Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).min(String::compareTo).orElse("default")));
//        System.out.println(String.format("Stream.findFirst: %s", Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).findFirst().orElse("default")));
//        System.out.println(String.format("Stream.findAny: %s", Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).findAny().orElse("default")));
//        System.out.println(String.format("Stream.anyMatch: %s", Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).anyMatch(name -> name.startsWith("J"))));
//        System.out.println(String.format("Stream.allMatch: %s", Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).allMatch(name -> name.startsWith("J"))));
//        System.out.println(String.format("Stream.noneMatch: %s", Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).noneMatch(name -> name.startsWith("J"))));
//
//        Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).forEach(System.out::println);
//
//        String[] array = Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).toArray(String[]::new);
//
//        List<String> list = Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).collect(Collectors.toList());
//        Set<String> set = Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).collect(Collectors.toSet());
//        LinkedHashSet<String> linkedHashSet = Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).collect(Collectors.toCollection(LinkedHashSet::new));
//
//        String joining1 = Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).collect(Collectors.joining());
//        String joining2 = Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).collect(Collectors.joining("_"));
//        String joining3 = Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).collect(Collectors.joining("_", "prefix", "suffix"));
//
//        IntSummaryStatistics summaryStatistics = Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).collect(Collectors.summarizingInt(String::length));
//        long count = summaryStatistics.getCount();
//        long sum = summaryStatistics.getSum();
//        double average = summaryStatistics.getAverage();
//        int max = summaryStatistics.getMax();
//        int min = summaryStatistics.getMin();

        Map<String, String> map1 = Stream.of("Tom", "Lask", "Jennifer").collect(Collectors.toMap(
                String::toLowerCase,
                String::toUpperCase));

        System.out.println(map1);

        Map<String, String> map2 = Stream.of("Tom", "Lask", "Jennifer", "lask").collect(Collectors.toMap(
                String::toLowerCase,
                String::toUpperCase,
                (existingValue, newValue) -> existingValue));

        System.out.println(map2);

        LinkedHashMap<String, String> linkedHashMap = Stream.of("Tom", "Lask", "Jennifer", "lask").collect(Collectors.toMap(
                String::toLowerCase,
                String::toUpperCase,
                (existingValue, newValue) -> existingValue,
                LinkedHashMap::new
        ));

        System.out.println(linkedHashMap);

        Map<String, Set<String>> map3 = Stream.of(Locale.getAvailableLocales()).collect(Collectors.toMap(
                Locale::getDisplayCountry,
                l -> Collections.singleton(l.getDisplayLanguage()),
                (a, b) -> {
                    Set<String> set = new HashSet<>(a);
                    set.addAll(b);
                    return set;
                }
        ));

        System.out.println(map3);

        ConcurrentMap<String, String> concurrentMap = Stream.of("Tom", "Lask", "Jennifer").collect(Collectors.toConcurrentMap(
                String::toLowerCase,
                String::toUpperCase));

        System.out.println(concurrentMap);
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
