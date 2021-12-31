package com.kilogate.hi.serialize.protostaff;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * ProtostaffSerializer
 *
 * @author kilogate
 * @create 2021/12/27 19:20
 **/
@Slf4j
public class ProtostaffSerializer {
    public static byte[] serialize(Class clazz, Object object) {
        if (clazz == null || object == null) {
            log.error("serialize fast fail, invalid param");
            return null;
        }

        Schema schema = RuntimeSchema.getSchema(clazz);
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

        byte[] result;
        try {
            result = ProtostuffIOUtil.toByteArray(object, schema, buffer);
        } catch (Exception e) {
            log.error("serialize error, object: {}", object, e);
            return null;
        } finally {
            buffer.clear();
        }

        return result;
    }

    public static Object deserialize(Class clazz, byte[] bytes) {
        if (clazz == null || bytes == null || bytes.length == 0) {
            log.error("deserialize fast fail, invalid param");
            return null;
        }

        Schema schema = RuntimeSchema.getSchema(clazz);

        Object result;
        try {
            result = clazz.newInstance();
            ProtostuffIOUtil.mergeFrom(bytes, result, schema);
        } catch (Exception e) {
            log.error("deserialize error, bytes: {}", bytes, e);
            return null;
        }

        return result;
    }

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

        byte[] bytes = serialize(Student.class, student);
        log.info("bytes: {}", bytes);

        Object object = deserialize(Student.class, bytes);
        log.info("object: {}", object);
    }
}
