package com.kilogate.hi.java.io.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 文件工具类的用法
 *
 * @author kilgoate
 * @create 2020/8/30 01:26
 **/
public class FilesUsage {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/tmp/tst.txt");

        // 写文件
        Files.write(path, "Hello, World!\n".getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        // 写文件
        List<String> lines = Stream.of("Line1", "Line2", "Line3").collect(Collectors.toList());
        Files.write(path, lines, StandardOpenOption.APPEND);

        // 读文件
        byte[] allBytes = Files.readAllBytes(path);
        String string = new String(allBytes, StandardCharsets.UTF_8);
        System.out.println(string);

        // 读文件
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        System.out.println(allLines);

        // 输入输出流
        InputStream inputStream = Files.newInputStream(path);
        OutputStream outputStream = Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        // 读入器写出器
        BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
    }
}
