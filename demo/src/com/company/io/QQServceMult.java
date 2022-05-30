package com.company.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class QQServceMult {

    public static void main(String[] args) {
        List<SocketChannel> sockets = new ArrayList<>();
        ByteBuffer bytes = ByteBuffer.allocate(1024);
        try {
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(2020));
            serverSocket.configureBlocking(false);
            // 阻塞
            while (true) {
                SocketChannel accept = serverSocket.accept();
                if (null == accept) {
                    System.out.println("暂时没有连接");
                } else {
                    accept.configureBlocking(false);

                    sockets.add(accept);
                    for (SocketChannel socket1 : sockets) {
                        int read = accept.read(bytes);
                        if (read != 0) {
                            System.out.println(bytes.toString());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
