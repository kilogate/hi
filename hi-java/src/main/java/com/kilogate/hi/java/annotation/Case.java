package com.kilogate.hi.java.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Cases.class)
public @interface Case {
    int value() default 0;
}
