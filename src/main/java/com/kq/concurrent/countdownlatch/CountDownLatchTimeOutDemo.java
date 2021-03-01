package com.kq.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CountDownLatchDemo
 *
 * @author kq
 * @date 2019/5/23
 */
public class CountDownLatchTimeOutDemo {

    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(10);

        for(int i=0;i<10;i++) {
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("======================"+Thread.currentThread().getName());
                }catch (Exception e) {
                    e.printStackTrace();
                }

                latch.countDown();

            }).start();
        }


        boolean result = latch.await(5, TimeUnit.SECONDS);

        System.out.println("result="+result);

    }

}
