package com.kilogate.hi.java.basic;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * LambdaUsage
 *
 * @author kilogate
 * @create 2021/12/12 13:32
 **/
public class LambdaUsage {
    public static void main(String[] args) throws InterruptedException {
//        Consumer<Object> consumer = System.out::println;
//        consumer = consumer.andThen(System.err::println);
//        repeat1(3, consumer);

        Function<String, String> function = str -> "S" + str.length() + str;
        function = function.compose(str -> str + "E" + str.length());
        repeat2(3, function);
    }

    private static void repeat1(int n, Consumer<Object> consumer) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            consumer.accept(i);
            Thread.sleep(1000);
        }
    }

    private static void repeat2(int n, Function<String, String> function) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            String apply = function.apply("#" + i);
            System.out.println("result: " + apply);
            Thread.sleep(1000);
        }
    }
}
