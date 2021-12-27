package com.kilogate.hi.serialize.string;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * StringSerializer
 *
 * @author kilogate
 * @create 2021/12/27 15:33
 **/
@Slf4j
public class StringSerializer {
    public static byte[] serialize(String string) {
        if (string == null || string.length() == 0) {
            log.error("serialize fast fail, invalid param");
            return null;
        }

        return string.getBytes(StandardCharsets.UTF_8);
    }

    public static String deserialize(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            log.error("deserialize fast fail, invalid param");
            return null;
        }

        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        String data = "你好，中国！";

        byte[] bytes = serialize(data);
        log.info("bytes: {}", bytes);

        String string = deserialize(bytes);
        log.info("string: {}", string);
    }
}
