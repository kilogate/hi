package com.kilogate.hi.java.io.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;

/**
 * 文件输入输出的用法
 *
 * @author kilogate
 * @create 2020/8/5 上午12:10
 **/
public class FileInputOutputUsage {
    public static void main(String[] args) throws FileNotFoundException {
        String filename = "/Users/kilogate/a.txt";

        // 写文件：PrintWriter
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(filename, true));
        printWriter.append(String.format("%s %s a%n", new Date(), Thread.currentThread()));
        printWriter.append(String.format("%s %s b%n", new Date(), Thread.currentThread()));
        printWriter.append(String.format("%s %s c%n", new Date(), Thread.currentThread()));
        printWriter.close();

        // 读文件：Scanner
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }
}
