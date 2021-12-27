package com.kilogate.hi.java.basic;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * ResourceUsage
 *
 * @author kilogate
 * @create 2021/12/23 10:50
 **/
public class ResourceUsage {
    public static void main(String[] args) {
        test3();
    }

    // 获取 URL
    private static void test1() {
        // 1、Class.getResource
        // 以 / 开头从 classpath 下获取资源，不以 / 开头从当前目录下获取资源
        // 底层还是使用 ClassLoader().getResource

        // 2、ClassLoader().getResource
        // 以 / 开头不支持，不以 / 开头从 classpath 下获取资源

        // 当前目录
        // file:/Users/kilogate/IdeaProjects/hi/hi-java/target/classes/com/kilogate/hi/java/basic/
        URL url1 = ResourceUsage.class.getResource("");
        System.out.println(url1);

        // classpath
        // file:/Users/kilogate/IdeaProjects/hi/hi-java/target/classes/
        URL url2 = ResourceUsage.class.getResource("/");
        System.out.println(url2);

        // 当前目录下的 ResourceUsage.class
        // file:/Users/kilogate/IdeaProjects/hi/hi-java/target/classes/com/kilogate/hi/java/basic/ResourceUsage.class
        URL url3 = ResourceUsage.class.getResource("ResourceUsage.class");
        System.out.println(url3);

        // classpath 下没有 ResourceUsage.class
        // null
        URL url4 = ResourceUsage.class.getResource("/ResourceUsage.class");
        System.out.println(url4);

        // classpath 下的 /com/kilogate/hi/java/basic/ResourceUsage.class 文件
        // file:/Users/kilogate/IdeaProjects/hi/hi-java/target/classes/com/kilogate/hi/java/basic/ResourceUsage.class
        url4 = ResourceUsage.class.getResource("/com/kilogate/hi/java/basic/ResourceUsage.class");
        System.out.println(url4);

        // classpath
        // file:/Users/kilogate/IdeaProjects/hi/hi-java/target/classes/
        URL url5 = ResourceUsage.class.getClassLoader().getResource("");
        System.out.println(url5);

        // ClassLoader 不支持 / 开头的路径
        // null
        URL url6 = ResourceUsage.class.getClassLoader().getResource("/");
        System.out.println(url6);

        // classpath 下的 com/kilogate/hi/java/basic/ResourceUsage.class 文件
        // file:/Users/kilogate/IdeaProjects/hi/hi-java/target/classes/com/kilogate/hi/java/basic/ResourceUsage.class
        URL url7 = ResourceUsage.class.getClassLoader().getResource("com/kilogate/hi/java/basic/ResourceUsage.class");
        System.out.println(url7);

        // ClassLoader 不支持 / 开头的路径
        // null
        URL url8 = ResourceUsage.class.getClassLoader().getResource("/com/kilogate/hi/java/basic/ResourceUsage.class");
        System.out.println(url8);
    }

    // 获取 InputStream
    private static void test2() {
        InputStream inputStream = ResourceUsage.class.getResourceAsStream("/image/onepiece.jpg");

        if (inputStream == null) {
            System.out.println("null inputStream");
            return;
        }

        List<String> lines = readAllLines(inputStream);
        lines.forEach(System.out::println);
    }

    // 获取 imageIcon
    private static void test3() {
        URL url = ResourceUsage.class.getResource("/image/onepiece.jpg");

        if (url == null) {
            System.out.println("null url");
            return;
        }

        ImageIcon imageIcon = new ImageIcon(url);
        System.out.println(imageIcon);
    }

    private static List<String> readAllLines(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));) {
            List<String> result = new ArrayList<>();
            for (; ; ) {
                String line = reader.readLine();
                if (line == null)
                    break;
                result.add(line);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
