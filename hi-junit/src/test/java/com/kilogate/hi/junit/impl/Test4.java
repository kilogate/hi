package com.kilogate.hi.junit.impl;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Test4
 *
 * @author fengquanwei
 * @create 2023/8/13 21:44
 **/
@Slf4j
public class Test4 {
    @Test
    void case1() {
        // 子符串判断
        String s = "abcde";
        Assertions.assertThat(s).as("字符串判断，判断首尾及长度").startsWith("ab").endsWith("de").hasSize(5);

        // 数字判断
        Integer i = 50;
        Assertions.assertThat(i).as("数字判断,数字大小比较").isGreaterThan(10).isLessThan(100);

        // 日期判断
        Date date1 = new Date();
        Date date2 = new Date(date1.getTime() + 100);
        Date date3 = new Date(date1.getTime() - 100);
        Assertions.assertThat(date1).as("日期判断：日期大小比较").isBefore(date2).isAfter(date3);

        // list比较
        List<String> list = Arrays.asList("a", "b", "c", "d");
        Assertions.assertThat(list).as("list的首尾元素及长度").startsWith("a").endsWith("d").hasSize(4);

        // Map判断
        Map<String, Object> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        Assertions.assertThat(map).as("Map的长度及键值测试").hasSize(3).containsKeys("A", "B", "C");

        log.info("case1");
    }
}
