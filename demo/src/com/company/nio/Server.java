package com.company.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {

    private Selector selector;
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
    String str;

    public void start() throws IOException {
        // 打开服务器套接字通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(1111));

        // 创建选择器
        selector = Selector.open();
        // ssc注册到selector准备连接
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        // 当前线程一直循环
        while(!Thread.currentThread().isInterrupted()) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while(iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (!key.isValid()) {
                    continue;
                }
                if (key.isAcceptable()) {
                    accept(key);
                }
                if (key.isReadable()) {
                    read(key);
                }
                if (key.isWritable()) {
                    write(key);
                }
                iterator.remove();
            }
        }
    }

    private void accept(SelectionKey key) throws IOException{
        SocketChannel socketChannel = SocketChannel.open();
        this.readBuffer.clear();
        int numRead;
        try {
            numRead = socketChannel.read(this.readBuffer);
        } catch (Exception e) {
            key.channel();
            socketChannel.close();
            return;
        }

        str = new String(readBuffer.array(), 0, numRead);
        System.out.println(str);
        socketChannel.register(selector, SelectionKey.OP_WRITE);
    }

    private void read(SelectionKey key) throws IOException{
        SocketChannel channel = (SocketChannel)key.channel();
        System.out.println("write: " + str);

        sendBuffer.clear();
        sendBuffer.put(str.getBytes());
        sendBuffer.flip();
        channel.write(sendBuffer);

        //注册读操作 下一次进行读
        channel.register(selector, SelectionKey.OP_READ);
    }

    private void write(SelectionKey key) throws IOException{
        ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
        SocketChannel clientChannel = ssc.accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("a new client connected "+clientChannel.getRemoteAddress());
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Server start ...");
        new Server().start();
    }
}
