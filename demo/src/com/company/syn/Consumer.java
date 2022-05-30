package com.company.syn;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            String product = queue.take();
            System.out.println(Thread.currentThread().getName() + " is consuming product." + "( " + product + " )");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}