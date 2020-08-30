package com.kilogate.hi.java.io.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        // 创建目录
//        Path directory1 = Files.createDirectory(Paths.get("/tmp/a"));
        Path directories = Files.createDirectories(Paths.get("/tmp/a/b/c/d/e"));

        // 创建文件
//        Path file = Files.createFile(Paths.get("/tmp/a/tst.txt"));

        // 创建临时目录
        Path tempDirectory1 = Files.createTempDirectory("tmp");
        Path tempDirectory2 = Files.createTempDirectory(Paths.get("/tmp"), "tmp");

        // 创建临时文件
        Path tempFile1 = Files.createTempFile("tmp", ".txt");
        Path tempFile2 = Files.createTempFile(Paths.get("/tmp"), "tmp", ".txt");

        // 复制文件
        Path copy = Files.copy(path, Paths.get("/tmp/tst_copy.txt"), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);

        // 移动文件
        Path move = Files.move(copy, Paths.get("/tmp/tst_move.txt"), StandardCopyOption.ATOMIC_MOVE);

        // 删除文件
        Files.delete(move);
        boolean b = Files.deleteIfExists(move);

        // 获取文件信息

        boolean exists = Files.exists(path);
        boolean hidden = Files.isHidden(path);

        boolean readable = Files.isReadable(path);
        boolean writable = Files.isWritable(path);
        boolean executable = Files.isExecutable(path);

        boolean regularFile = Files.isRegularFile(path);
        boolean directory = Files.isDirectory(path);
        boolean symbolicLink = Files.isSymbolicLink(path);

        long size = Files.size(path);

        UserPrincipal owner = Files.getOwner(path);

        BasicFileAttributes basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
        FileTime creationTime = basicFileAttributes.creationTime();
        FileTime lastAccessTime = basicFileAttributes.lastAccessTime();
        FileTime lastModifiedTime = basicFileAttributes.lastModifiedTime();
        boolean regularFile1 = basicFileAttributes.isRegularFile();
        boolean directory1 = basicFileAttributes.isDirectory();
        boolean symbolicLink1 = basicFileAttributes.isSymbolicLink();
        long size1 = basicFileAttributes.size();
        Object fileKey = basicFileAttributes.fileKey();

        PosixFileAttributes posixFileAttributes = Files.readAttributes(path, PosixFileAttributes.class);
        UserPrincipal owner2 = posixFileAttributes.owner();
        GroupPrincipal group = posixFileAttributes.group();
        Set<PosixFilePermission> permissions = posixFileAttributes.permissions();

        // 访问目录中的项

        try (Stream<Path> list = Files.list(Paths.get(""))) {
            list.forEach(System.out::println);
        }

        try (Stream<Path> walk = Files.walk(Paths.get(""))) {
            walk.forEach(System.out::println);
        }

        try (Stream<Path> walk = Files.walk(Paths.get(""), 1)) {
            walk.forEach(System.out::println);
        }

        try (Stream<Path> find = Files.find(Paths.get("hi-java"), 10, (p, a) -> p.toFile().getName().endsWith(".java") && a.isRegularFile(), FileVisitOption.FOLLOW_LINKS)) {
            find.forEach(System.out::println);
        }

        // 使用目录流

        try (DirectoryStream<Path> paths = Files.newDirectoryStream(Paths.get(""), "*.xml")) {
            for (Path p : paths) {
                System.out.println(p);
            }
        }

        Set<FileVisitOption> options = new HashSet<>();
        options.add(FileVisitOption.FOLLOW_LINKS);

        FileVisitor fileVisitor = new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println(String.format("preVisitDirectory: %s", dir.toAbsolutePath()));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(String.format("visitFile: %s", file.toAbsolutePath()));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                System.out.println(String.format("visitFileFailed: %s", file.toAbsolutePath()));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println(String.format("postVisitDirectory: %s", dir.toAbsolutePath()));
                return FileVisitResult.CONTINUE;
            }
        };

        Files.walkFileTree(Paths.get("hi-java"), options, 10, fileVisitor);

        System.out.println();
    }
}
