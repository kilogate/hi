package com.kilogate.hi.serialize.jdk;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * JdkSerializer
 *
 * @author kilogate
 * @create 2021/12/27 14:06
 **/
@Slf4j
public class JdkSerializer {
    public static byte[] serialize(Object object) {
        if (object == null || !(object instanceof Serializable)) {
            log.error("serialize fast fail, invalid param");
            return null;
        }

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);) {
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            log.error("serialize error, object: {}", object, e);
            return null;
        }
    }

    public static Object deserialize(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            log.error("deserialize fast fail, invalid param");
            return null;
        }

        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);) {
            return objectInputStream.readObject();
        } catch (Exception e) {
            log.error("deserialize error, bytes: {}", bytes, e);
            return null;
        }
    }
}
