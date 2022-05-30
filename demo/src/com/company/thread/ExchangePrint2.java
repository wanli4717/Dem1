package com.company.thread;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ExchangePrint2 {


    public static void main(String[] args) throws InterruptedException {
        ReentrantLock rLock = new ReentrantLock();
        Condition condition = rLock.newCondition();
        Condition condition1 = rLock.newCondition();

        new Thread(()->{
            char a = 'A';
            try {
                for(int i=0;i<26;i++){
                    rLock.lock();
                    System.out.println(a);
                    Thread.sleep(10);
                    a++;
                    condition1.signal();
                    condition.await();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                rLock.tryLock();
            }
        }).start();


//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        Map<Character, Integer> map = new HashMap<>();
        new Thread(()->{
            try {
                for(int i=1;i<27;i++){
                    rLock.lock();
                    System.out.println(i);
                    Thread.sleep(10);
                    condition.signal();
                    condition1.await();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                rLock.tryLock();
            }
        }).start();


    }

    public void sort(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
    }
}
