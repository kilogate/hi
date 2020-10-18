package com.kilogate.hi.java.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * 可中断套接字的用法
 *
 * @author kilogate
 * @create 2020/10/18 22:26
 **/
public class SocketChannelUsage {
    public static void main(String[] args) throws IOException, InterruptedException {
        interruptible();
    }

    private static void blocking() throws InterruptedException {
        // 阻塞套接字客户端
        Thread blockingClientThread = new Thread(() -> {
            try (Socket socket = new Socket("127.0.0.1", 12345)) {
                Scanner scanner = new Scanner(socket.getInputStream(), "UTF-8");

                // 此处不可中断！！！
                while (scanner.hasNextLine()) {
                    System.out.println(String.format("%s [blockingClient] received: %s", Thread.currentThread(), scanner.nextLine()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(String.format("%s [blockingClient] exit", Thread.currentThread()));
        });

        // 启动客户端
        blockingClientThread.start();

        // 八秒后中断客户端
        Thread.sleep(8000);
        blockingClientThread.interrupt();

        System.out.println("interrupted!!!");
    }

    private static void interruptible() throws InterruptedException {
        // 可中断套接字客户端
        Thread interruptibleClientThread = new Thread(() -> {
            try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 12345))) {
                Scanner scanner = new Scanner(socketChannel, "UTF-8");

                // 此处可中断！！！
                while (scanner.hasNextLine()) {
                    System.out.println(String.format("%s [interruptiblyClient] received: %s", Thread.currentThread(), scanner.nextLine()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(String.format("%s [interruptiblyClient] exit", Thread.currentThread()));
        });

        // 启动客户端
        interruptibleClientThread.start();

        // 八秒后中断客户端
        Thread.sleep(8000);
        interruptibleClientThread.interrupt();

        System.out.println("interrupted!!!");
    }
}