package com.kilogate.hi.java.stream;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流的终止
 *
 * @author kilogate
 * @create 2020/8/15 上午11:53
 **/
public class StreamTerminationUsage extends StreamBaseUsage {
    public static void main(String[] args) {
        // 简单约简

        System.out.println(String.format("Stream.count: %s", Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).count()));
        System.out.println(String.format("Stream.max: %s", Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).max(String::compareTo).orElse("default")));
        System.out.println(String.format("Stream.min: %s", Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).min(String::compareTo).orElse("default")));
        System.out.println(String.format("Stream.findFirst: %s", Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).findFirst().orElse("default")));
        System.out.println(String.format("Stream.findAny: %s", Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).findAny().orElse("default")));
        System.out.println(String.format("Stream.anyMatch: %s", Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).anyMatch(name -> name.startsWith("J"))));
        System.out.println(String.format("Stream.allMatch: %s", Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).allMatch(name -> name.startsWith("J"))));
        System.out.println(String.format("Stream.noneMatch: %s", Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).noneMatch(name -> name.startsWith("J"))));

        System.out.println();

        // 收集结果

        Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).forEach(System.out::println);

        String[] array = Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).toArray(String[]::new);

        List<String> list = Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).collect(Collectors.toList());
        Set<String> set = Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).collect(Collectors.toSet());
        LinkedHashSet<String> linkedHashSet = Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).collect(Collectors.toCollection(LinkedHashSet::new));

        String joining1 = Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).collect(Collectors.joining());
        String joining2 = Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).collect(Collectors.joining("_"));
        String joining3 = Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).collect(Collectors.joining("_", "prefix", "suffix"));

        IntSummaryStatistics summaryStatistics = Stream.of("Tom,Lynn,Lask,Jennifer".split(",")).collect(Collectors.summarizingInt(String::length));
        long count = summaryStatistics.getCount();
        long sum = summaryStatistics.getSum();
        double average = summaryStatistics.getAverage();
        int max = summaryStatistics.getMax();
        int min = summaryStatistics.getMin();

        // 收集到映射表中

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
                    Set<String> union = new HashSet<>(a);
                    union.addAll(b);
                    return union;
                }
        ));

        System.out.println(map3);

        ConcurrentMap<String, String> concurrentMap = Stream.of("Tom", "Lask", "Jennifer").collect(Collectors.toConcurrentMap(
                String::toLowerCase,
                String::toUpperCase));

        System.out.println(concurrentMap);

        System.out.println();

        // 群组和分区

        Map<String, List<Locale>> stringListMap = Stream.of(Locale.getAvailableLocales()).collect(Collectors.groupingBy(Locale::getCountry));
        System.out.println(stringListMap);

        Map<Boolean, List<Locale>> booleanListMap = Stream.of(Locale.getAvailableLocales()).collect(Collectors.partitioningBy(l -> l.getLanguage().equals("en")));
        System.out.println(booleanListMap);

        System.out.println();

        // 下游收集器

        Map<String, Set<City>> setMap = generateCityStream().collect(Collectors.groupingBy(City::getState, Collectors.toSet()));
        System.out.println(setMap);

        Map<String, Long> countingMap = generateCityStream().collect(Collectors.groupingBy(City::getState, Collectors.counting()));
        System.out.println(countingMap);

        Map<String, Integer> summingIntMap = generateCityStream().collect(Collectors.groupingBy(City::getState, Collectors.summingInt(City::getPopulation)));
        System.out.println(summingIntMap);

        Map<String, Optional<City>> maxByMap = generateCityStream().collect(Collectors.groupingBy(City::getState, Collectors.maxBy(Comparator.comparing(City::getPopulation))));
        System.out.println(maxByMap);

        Map<String, String> mappingMap = generateCityStream().collect(Collectors.groupingBy(City::getState, Collectors.mapping(City::getName, Collectors.joining(","))));
        System.out.println(mappingMap);

        Map<String, IntSummaryStatistics> summaryStatisticsMap = generateCityStream().collect(Collectors.groupingBy(City::getState, Collectors.summarizingInt(City::getPopulation)));
        System.out.println(summaryStatisticsMap);
    }

    private static Stream<City> generateCityStream() {
        return Stream.of(
                new City("北京", "北京", 25000000),
                new City("广州", "广东", 10000000),
                new City("深圳", "广东", 10000000),
                new City("郑州", "河南", 10000000),
                new City("开封", "河南", 3000000),
                new City("洛阳", "河南", 3000000));
    }

    private static class City {
        private String name;
        private String state;
        private int population;

        public City(String name, String state, int population) {
            this.name = name;
            this.state = state;
            this.population = population;
        }

        public String getName() {
            return name;
        }

        public String getState() {
            return state;
        }

        public int getPopulation() {
            return population;
        }

        @Override
        public String toString() {
            return "City{" +
                    "name='" + name + '\'' +
                    ", state='" + state + '\'' +
                    ", population=" + population +
                    '}';
        }
    }
}
