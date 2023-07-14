package com.kilogate.hi.java.annotation;

import java.lang.reflect.Method;

public class Usage {
    public static void main(String[] args) throws ClassNotFoundException {
//        usageForTest();
        usageForCase();
    }

    private static void usageForTest() throws ClassNotFoundException {
        int pass = 0, failed = 0;

        Class clazz = Class.forName("com.kilogate.hi.java.annotation.Foo");
        for (Method method : clazz.getMethods()) {
            if (!method.isAnnotationPresent(Test.class)) {
                continue;
            }

            Test test = method.getAnnotation(Test.class);
            System.out.println(test.desc());

            try {
                method.invoke(null);
                pass++;
            } catch (Exception e) {
                failed++;
            }
        }

        System.out.printf("pass: %d, failed: %d\n", pass, failed);
    }

    private static void usageForCase() throws ClassNotFoundException {
        Class clazz = Class.forName("com.kilogate.hi.java.annotation.Foo");
        for (Method method : clazz.getMethods()) {
            if (!method.isAnnotationPresent(Cases.class)) {
                continue;
            }

            Case[] caseArray = method.getAnnotationsByType(Case.class); // 方法一
            Cases cases = method.getAnnotation(Cases.class); // 方法二
            for (Case c : cases.value()) {
                int v = c.value();
                try {
                    method.invoke(Foo.class, v);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
