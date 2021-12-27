package com.kilogate.hi.serialize.jdk;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * JdkSerializerUsage
 *
 * @author kilogate
 * @create 2021/12/27 14:25
 **/
@Slf4j
public class JdkSerializerUsage {
    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Student implements Serializable {
        private int sno;
        private String name;
        private Integer age;
    }

    public static void main(String[] args) {
        Student student = new Student(110, "Kilogate", 17);

        byte[] bytes = JdkSerializer.serialize(student);
        log.info("bytes: {}", bytes);

        Object object = JdkSerializer.deserialize(bytes);
        log.info("object: {}", object);
    }
}
