package com.kilogate.hi.java.network;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * 套接字的用法
 *
 * @author kilogate
 * @create 2020/10/18 15:29
 **/
public class SocketUsage {
    public static void main(String[] args) throws IOException {
        usage2();
    }

    private static void usage1() throws IOException {
        try (Socket socket = new Socket("127.0.0.1", 12345);
             Scanner scanner = new Scanner(socket.getInputStream(), "UTF-8");
             PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                printWriter.println("received: " + line);
            }
        }
    }

    private static void usage2() throws IOException {
        try (Socket socket = new Socket()) {
            System.out.println(String.format("socket, isConnected: %s, isClosed: %s", socket.isConnected(), socket.isClosed()));

            socket.setSoTimeout(10000);
            socket.connect(new InetSocketAddress("127.0.0.1", 12345), 3000);

            System.out.println(String.format("socket, isConnected: %s, isClosed: %s", socket.isConnected(), socket.isClosed()));

            Scanner scanner = new Scanner(socket.getInputStream(), "UTF-8");
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                printWriter.println("received: " + line);
            }

            System.out.println(String.format("socket, isConnected: %s, isClosed: %s", socket.isConnected(), socket.isClosed()));
        }
    }
}
