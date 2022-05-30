package com.company.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class QQServce {

    public static void main(String[] args) {
        byte[] bytes = new byte[1024];
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(2020));

            Socket socket = serverSocket.accept();

            int read = socket.getInputStream().read(bytes);
            String str = new String(bytes);
            System.out.println(str);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
