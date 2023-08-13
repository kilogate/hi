package com.kilogate.hi.junit.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

/**
 * Test3
 *
 * @author fengquanwei
 * @create 2023/8/13 21:31
 **/
@Slf4j
public class Test3 {
    @Test
    void case1() {
        Assertions.assertEquals(1, 1);
        Assertions.assertEquals(4, 4, "error message");
        Assertions.assertTrue(2 == 2, () -> "error message");

        Assertions.assertAll(() -> Assertions.assertEquals(1, 1),
                () -> Assertions.assertEquals(4, 4, "error message"));

        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
        Assertions.assertEquals("a message", exception.getMessage());

        Assertions.assertTimeout(Duration.ofMillis(200), () -> {
            Thread.sleep(100);
        });

        log.info("case1");
    }
}
