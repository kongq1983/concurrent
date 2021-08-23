package com.kq.concurrent.park;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * ParkDemo
 *
 * @author kq
 * @date 2021/6/29 23:08
 * @since 1.0.0
 */
public class ParkDemo {

    public static void main(String[] args) {
        new ParkDemo().init();
        try {
            TimeUnit.SECONDS.sleep(60);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void init(){

        Thread t1 = new Thread();

        Runnable r = ()->{
            LockSupport.park(t1);
            while (true) {
                System.out.println(Thread.currentThread() + "============================");
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        };

        Thread t = new Thread(r,"thread-1");
        t.start();

        System.out.println("------------------1--------------");
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("------------------start unpark--------------");

        LockSupport.unpark(t);
        System.out.println("------------------2--------------");


    }

}
