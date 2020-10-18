package com.kilogate.hi.java.network;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

/**
 * 服务器套接字的用法
 *
 * @author kilogate
 * @create 2020/10/18 16:32
 **/
public class ServerSocketUsage {
    public static void main(String[] args) throws IOException {
        usage2();
    }

    private static void usage1() throws IOException {
        // 启动服务器，监听 12345 端口
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            // 等待连接
            try (Socket socket = serverSocket.accept()) {
                // 获取输入输出流
                try (Scanner scanner = new Scanner(socket.getInputStream(), "UTF-8");
                     PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true)) {
                    printWriter.println("Hello! Enter BYE to exit.");

                    boolean done = false;
                    while (!done && scanner.hasNextLine()) {
                        // 获取输入
                        String line = scanner.nextLine();
                        System.out.println("Server received: " + line);

                        // 打印输出
                        printWriter.println("Echo: " + line);

                        if (Objects.equals(line, "BYE")) {
                            done = true;
                        }
                    }
                }
            }
        }
    }

    private static void usage2() throws IOException {
        // 启动服务器，监听 12345 端口
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            // 等待连接
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new EchoRunnable(socket)).start();
            }
        }
    }

    private static class EchoRunnable implements Runnable {
        private Socket socket;

        public EchoRunnable(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            // 获取输入输出流
            try (Scanner scanner = new Scanner(socket.getInputStream(), "UTF-8");
                 PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true)) {
                printWriter.println("Hello! Enter BYE to exit.");

                boolean done = false;
                while (!done && scanner.hasNextLine()) {
                    // 获取输入
                    String line = scanner.nextLine();
                    System.out.println(Thread.currentThread() + " Server received: " + line);

                    // 打印输出
                    printWriter.println("Echo: " + line);

                    if (Objects.equals(line, "BYE")) {
                        done = true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
