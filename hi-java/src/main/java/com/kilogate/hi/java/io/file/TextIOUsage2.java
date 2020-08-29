package com.kilogate.hi.java.io.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * 文本输入输出的用法
 *
 * @author kilogate
 * @create 2020/8/29 17:32
 **/
public class TextIOUsage2 {
    public static void main(String[] args) throws IOException {
        Stream<Student> studentStream = Stream.of(new Student(1L, "Lask", 28), new Student(2L, "Jeff", 30));

        String filename = "/tmp/students2.txt";

        // write students
        try (PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename, true), StandardCharsets.UTF_8.name()))) {
            studentStream.forEach(student -> writeStudent(student, printWriter));
        }

        // read students
        try (Stream<String> lines = Files.lines(Paths.get(filename), StandardCharsets.UTF_8)) {
            lines.map(TextIOUsage2::readStudent).forEach(System.out::println);
        }
    }

    private static void writeStudent(Student student, PrintWriter printWriter) {
        printWriter.printf("%s|%s|%s%n", student.getSno(), student.getName(), student.getAge());
    }

    private static Student readStudent(String line) {
        String[] tokens = line.split("\\|");

        Long sno = Long.valueOf(tokens[0]);
        String name = tokens[1];
        Integer age = Integer.valueOf(tokens[2]);

        return new Student(sno, name, age);
    }

    private static class Student {
        private long sno;
        private String name;
        private int age;

        public Student() {
        }

        public Student(long sno, String name, int age) {
            this.sno = sno;
            this.name = name;
            this.age = age;
        }

        public long getSno() {
            return sno;
        }

        public void setSno(long sno) {
            this.sno = sno;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "sno=" + sno +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
