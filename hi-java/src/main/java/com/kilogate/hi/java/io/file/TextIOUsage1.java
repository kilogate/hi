package com.kilogate.hi.java.io.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 文本输入输出的用法
 *
 * @author kilogate
 * @create 2020/8/29 17:32
 **/
public class TextIOUsage1 {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Student student1 = new Student(1L, "Lask", 28);
        Student student2 = new Student(2L, "Jeff", 30);

        Student[] students = new Student[]{student1, student2};
        String filename = "/tmp/students1.txt";

        // write students
        try (PrintWriter printWriter = new PrintWriter(filename, StandardCharsets.UTF_8.name());) {
            writeStudents(students, printWriter);
        }

        // read students
        try (Scanner scanner = new Scanner(new FileInputStream(filename), StandardCharsets.UTF_8.name())) {
            Student[] newStudents = readStudents(scanner);
            System.out.println(Arrays.toString(newStudents));
        }
    }

    private static void writeStudents(Student[] students, PrintWriter printWriter) {
        printWriter.println(students.length);

        for (Student student : students) {
            writeStudent(student, printWriter);
        }
    }

    private static void writeStudent(Student student, PrintWriter printWriter) {
        printWriter.printf("%s|%s|%s%n", student.getSno(), student.getName(), student.getAge());
    }

    private static Student[] readStudents(Scanner scanner) {
        int length = scanner.nextInt();
        scanner.nextLine();

        Student[] students = new Student[length];

        for (int i = 0; i < length; i++) {
            students[i] = readStudent(scanner);
        }

        return students;
    }

    private static Student readStudent(Scanner scanner) {
        String line = scanner.nextLine();
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
