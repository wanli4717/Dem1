package com.company.aqs;

public class Test {

    static int count = 0;
    static LockBaseAQS myLock = new LockBaseAQS();

    public static void main(String[] args) throws InterruptedException{

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    // 进行上锁，同一时间，只允许一个线程创建累加计数
                    // 这块代码同一时间只能有一个线程进来(获取到锁的线程)
                    // 其他的线程在lock()方法上阻塞，等待获取到锁，再进来
                    myLock.lock();
                    for (int i = 0; i < 10000; i++) {
                        count++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 解锁
                    myLock.unLock();
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(count);
    }
}
