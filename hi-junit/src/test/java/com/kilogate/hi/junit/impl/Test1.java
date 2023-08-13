package com.kilogate.hi.junit.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

/**
 * Test1
 *
 * @author fengquanwei
 * @create 2023/8/12 23:25
 **/
@Slf4j
@DisplayName("基本测试")
public class Test1 {
    @BeforeAll
    public static void beforeAll() {
        log.info("beforeAll...");
    }

    @AfterAll
    public static void afterAll() {
        log.info("afterAll...");
    }

    @BeforeEach
    void beforeEach() {
        log.info("beforeEach...");
    }

    @AfterEach
    void afterEach() {
        log.info("afterEach...");
    }

    @Test
    @DisplayName("第一个Case")
    void case1() {
        log.info("case1");
    }

    @Test
    @Disabled
    void case2() {
        log.info("case2");
    }
}
