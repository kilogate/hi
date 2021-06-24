package com.kilogate.hi.lombok;

import lombok.extern.java.Log;

/**
 * LombokUsage
 *
 * @author kilogate
 * @create 2021/6/24 19:46
 **/
@Log
public class LombokUsage {
    public static void main(String[] args) {
        // 基本用法
        Student tom = Student.of(123, "Tom");
        tom.setSex("M");

        System.out.println(tom);

        // builder 模式
        Student bob = Student.builder()
                .sno(223)
                .name("Bob")
                .sex("M")
                .build();

        System.out.println(bob);

        log.info("main end");
    }
}
