package com.kq.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author kq
 * @date 2021-11-29 18:44
 * @since 2020-0630
 */
public class CountDownLatchDemo2 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);

        Runnable runnable = ()->{

            try {
                TimeUnit.SECONDS.sleep(20);
            }catch (Exception e){
                e.printStackTrace();
            }

            latch.countDown();

            System.out.println("latch.countDown()==================");
        };

        Thread t1 = new Thread(runnable);
        t1.start();

        Runnable runnable1 = ()->{

            try {
                TimeUnit.MINUTES.sleep(60);
            }catch (Exception e){
                e.printStackTrace();
            }

            latch.countDown();
        };

        Runnable await1 = ()->{

            try {
                latch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+",==============================");

        };

        Thread await1Thread = new Thread(await1,"await1Thread");
        await1Thread.start();

        Runnable await2 = ()->{

            try {
                latch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+",==============================");

        };

        Thread await2Thread = new Thread(await1,"await2Thread");
        await2Thread.start();


        try {

            latch.await();

            System.out.println("======================================");

//            TimeUnit.MINUTES.sleep(60);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
