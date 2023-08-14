package com.kilogate.hi.junit.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * Test5
 *
 * @author fengquanwei
 * @create 2023/8/14 09:56
 **/
@Slf4j
public class Test5 {
    @ParameterizedTest
    @MethodSource
    public void case1(String name) {
        log.info("name: {}", name);
    }

    private static Stream<String> case1() {
        return Stream.of("a", "b", "c");
    }
}
