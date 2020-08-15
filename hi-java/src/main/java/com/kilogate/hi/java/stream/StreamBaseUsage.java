package com.kilogate.hi.java.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流的基本使用
 *
 * @author kilogate
 * @create 2020/8/15 上午11:46
 **/
public class StreamBaseUsage {
    protected static <T> void printStream(String title, Stream<T> stream) {
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
