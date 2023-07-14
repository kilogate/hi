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
        Student3 bob = Student3.builder().no(123).name("Bob").build();
        Student4 tom = Student4.of(123, "Tom");

        log.info("main end");
    }
}
