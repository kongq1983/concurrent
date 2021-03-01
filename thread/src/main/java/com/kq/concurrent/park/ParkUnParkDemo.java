package com.kq.concurrent.park;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by qikong on 2019/5/16.
 */
public class ParkUnParkDemo {

    private static AtomicInteger parkInt = new AtomicInteger(1);
    private static AtomicInteger unParkInt = new AtomicInteger(1);


    public static void main(String[] args) throws Exception{

        Thread thread = new Thread(()->{

            int num = parkInt.getAndIncrement();
            while(num<1000) {
                System.out.println(Thread.currentThread().getName() + " num=" + num);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (num % 5 == 0) {
                    System.out.println(Thread.currentThread().getName() + " start park.");
                    LockSupport.park();
                }
                num = parkInt.getAndIncrement();
            }

        });

        thread.setName("thread-1");

        thread.start();


        Thread thread2 = new Thread(()->{
            int num = unParkInt.getAndIncrement();

            while(num<1000) {

                System.out.println(Thread.currentThread().getName() + " num=" + num);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (num % 10 == 0) {
                    System.out.println(thread.getName() + " start unpark.");
                    LockSupport.unpark(thread);
                }
                num = unParkInt.getAndIncrement();
            }

        });

        thread2.setName("thread-2");
        thread2.start();





    }


}
