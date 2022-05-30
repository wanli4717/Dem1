package com.company.syn;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is making product.");
        String product = "Made By " + Thread.currentThread().getName();
        try {
            queue.put(product);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}