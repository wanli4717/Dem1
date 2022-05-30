package com.company.thread;

public class ExchangePrint {
    public static void main(String[] args) {


        Object object = new Object();
        new Thread(()->{
            char a = 'A';
            synchronized (object){
                for(int i=0;i<26;i++){
                    System.out.println(a);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    a++;
                    object.notifyAll();
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        new Thread(()->{
            synchronized (object){
                for(int i=1;i<27;i++){
                    System.out.println(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    object.notifyAll();
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}