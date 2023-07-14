package com.kilogate.hi.java.annotation;

public class Foo {
    @Test(desc = "测试用例m1")
    public static void m1() {

    }

    @Test
    public static void m2() {
        throw new RuntimeException();
    }

    @Case(1)
    @Case(2)
    @Case(3)
    public static void m3(int num) {
        System.out.printf("m3(%d)\n", num);
    }

    public static void m4() {
        throw new RuntimeException();
    }

    @Test
    public static void m5() {

    }

    @Test
    public static void m6() {
        throw new RuntimeException();
    }
}
