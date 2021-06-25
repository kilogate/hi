package com.kilogate.hi.lombok;

import lombok.extern.java.Log;

/**
 * LombokUsage
 * <p>
 * Lombok 使用 JSR 269 Pluggable Annotation Processing API（java6 开始支持）
 * Oracle 的 javac 支持 JSR 269
 * <p>
 * Maven 使用的编译器得是 Oracle javac
 * Idea 使用的编译器得是 Oracle javac
 * Idea 还得安装 Lombok Plugin，否则编译正常，但是显示报错
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
