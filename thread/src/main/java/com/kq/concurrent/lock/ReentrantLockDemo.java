package com.kq.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kq
 * @date 2021-06-26 9:51
 * @since 2020-0630
 */
public class ReentrantLockDemo {


    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        Runnable runnable = ()->{
            lock.lock();

            try {
                TimeUnit.SECONDS.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }

        };

        Thread t = new Thread(runnable);
        t.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            lock.lock();
            System.out.println("=================================================");

        } finally {
            lock.unlock();
        }


    }

}
