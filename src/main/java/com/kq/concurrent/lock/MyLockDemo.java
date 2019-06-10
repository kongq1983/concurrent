package com.kq.concurrent.lock;

import java.util.concurrent.TimeUnit;

/**
 * MyLockDemo
 *
 * @author kq
 * @date 2019-05-27
 */
public class MyLockDemo {

    static MyLock lock = new MyLock();

    public static void main(String[] args) throws Exception{



        for(int i=0;i<50;i++) {

            new Thread(() -> {

                lock.lock();
                System.out.println(Thread.currentThread().getName() + "获取锁!");

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lock.unlock();

            }).start();
        }

    }

}
