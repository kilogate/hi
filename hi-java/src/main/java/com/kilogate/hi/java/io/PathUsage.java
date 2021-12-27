package com.kilogate.hi.java.io;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 路径的用法
 *
 * @author kilogate
 * @create 2020/8/30 01:00
 **/
public class PathUsage {
    public static void main(String[] args) {
        Path path = Paths.get("");
        Path absolutePath = path.toAbsolutePath();
        System.out.println("path: " + path);
        System.out.println("absolutePath: " + absolutePath);

        Path path1 = absolutePath.resolve("a/b/c");
        System.out.println("path1: " + path1);

        Path path2 = absolutePath.resolve("/a/b/c");
        System.out.println("path2: " + path2);

        Path path3 = absolutePath.resolveSibling("a/b/c");
        System.out.println("path3: " + path3);

        Path path4 = absolutePath.resolveSibling("/a/b/c");
        System.out.println("path4: " + path4);

        Path path5 = absolutePath.relativize(Paths.get("/Users/kilogate/IdeaProjects/hi/a/b/c"));
        System.out.println("path5: " + path5);

        Path path6 = absolutePath.relativize(Paths.get("/Users/kilogate/IdeaProjects/hello/a/b/c"));
        System.out.println("path6: " + path6);

        Path path7 = Paths.get("/Users/kilogate/IdeaProjects/./hello/../hi/a/b/c").normalize();
        System.out.println("path7: " + path7);

        System.out.println(path.getParent());
        System.out.println(path.getFileName());
        System.out.println(path.getRoot());

        System.out.println(absolutePath.getParent());
        System.out.println(absolutePath.getFileName());
        System.out.println(absolutePath.getRoot());
    }
}
