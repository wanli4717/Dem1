package com.company.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",2020);

            socket.getOutputStream().write("111".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

