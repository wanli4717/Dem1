package com.company.syn;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ValSyn implements Runnable{

    private volatile int count = 20;

    Lock lock = new ReentrantLock();


    @Override
    public void run() {
        try {
            lock.lock();
            while (count > 0) {
                try {
                    Thread.sleep(20);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "------" + count--);
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ValSyn valSyn = new ValSyn();

        Thread thread1 = new Thread(valSyn);
        Thread thread2 = new Thread(valSyn);
        Thread thread3 = new Thread(valSyn);

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
