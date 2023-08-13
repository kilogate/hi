package com.kilogate.hi.junit.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Test2
 *
 * @author fengquanwei
 * @create 2023/8/13 21:02
 **/
@Slf4j
public class Test2 {
    @Test
    @RepeatedTest(3)
    void case1() {
        log.info("case1");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "c"})
    void case2(String name) {
        log.info("case2: {}", name);
    }


    @ParameterizedTest
    @CsvSource({"1,One", "2,Two", "3,Three"})
    void case3(long id, String name) {
        log.info("case3, id: {}, name: {}", id, name);
    }

    @ParameterizedTest
    @MethodSource({"getNameList1", "getNameList2"})
    void case4(String name) {
        log.info("case4, name: {}", name);
    }

    private static List<String> getNameList1() {
        return Arrays.asList(new String[]{"111", "222", "333"});
    }

    private static List<String> getNameList2() {
        return Arrays.asList(new String[]{"444", "555", "666"});
    }

    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    void case5(String name) {
        log.info("case5, name: {}", name);
    }

    private static class MyArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of("a", "b", "c").map(Arguments::of);
        }
    }
}
