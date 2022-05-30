package com.company.syn;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaitNotify {

    private static Object lock = new Object();
    private static int i = 0;

    public void odd() {
        while (i < 10) {
            synchronized (lock) {
                if (i % 2 == 1) {
                    System.out.println(Thread.currentThread().getName() + "-" + i);
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    i++;
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void even() {
        while (i < 10) {
            synchronized (lock) {
                if (i % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + "-" + i);
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    i++;
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        WaitNotify waitNotify = new WaitNotify();

        Thread thread1 = new Thread(() -> waitNotify.odd(), "线程1");
        Thread thread2 = new Thread(() -> waitNotify.even(), "线程2");

        thread1.start();
        thread2.start();

        AtomicInteger atomicInteger = new AtomicInteger();

        AbstractQueuedSynchronizer abstractQueuedSynchronizer = new AbstractQueuedSynchronizer() {
        };
    }
}
