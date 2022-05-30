package com.company.syn;

import java.util.concurrent.CountDownLatch;

public class CountDown {

    private static Integer i = 0;
    final static CountDownLatch countDown = new CountDownLatch(1);

    public void odd() {
        while (i < 10) {
            if (i % 2 == 1) {
                System.out.println(Thread.currentThread().getName() + "-" + i);
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i++;
                countDown.countDown();
            } else {
                try {
                    countDown.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void even() {
        while (i < 10) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + "-" + i);
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i++;
                countDown.countDown();
            } else {
                try {
                    countDown.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        CountDown countDown = new CountDown();

        Thread thread1 = new Thread(() -> countDown.odd(), "线程1");
        Thread thread2 = new Thread(() -> countDown.even(), "线程2");

        thread1.start();
        thread2.start();
    }

}