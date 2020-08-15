package com.kilogate.hi.java.stream;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Optional 的用法
 *
 * @author kilogate
 * @create 2020/8/15 下午2:10
 **/
public class OptionalUsage {
    public static void main(String[] args) {
        Optional<String> optional = Stream.of("A", "B", "C").filter(s -> s.startsWith("D")).findFirst();

        System.out.println(optional.orElse("Default"));
        System.out.println(optional.orElseGet(() -> "Default"));
//        System.out.println(optional.orElseThrow(RuntimeException::new));

        optional.ifPresent(System.out::println);

        Optional<Integer> lengthOptional = optional.map(String::length);
        System.out.println(lengthOptional.orElse(0));

//        System.out.println(optional.get());
        System.out.println(optional.isPresent());

        Optional<Double> optional1 = Optional.of(-4.0);
        Optional<Object> optional2 = Optional.ofNullable(null);
        Optional<Object> optional3 = Optional.empty();

        Optional<Double> flatMapOptional = Optional.of(4.0).flatMap(OptionalUsage::inverse).flatMap(OptionalUsage::square);
        System.out.println(flatMapOptional.orElse(0D));
    }

    private static Optional<Double> inverse(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

    private static Optional<Double> square(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }
}
