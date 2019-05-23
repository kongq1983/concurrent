package com.kq.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CountDownLatchDemo
 *
 * @author kq
 * @date 2019/5/23
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws Exception {
        AtomicInteger ato = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(10);

        for(int i=0;i<10;i++) {
            new Thread(()->{

                for(int j=0;j<10000;j++){
                    ato.getAndIncrement();
                }
                latch.countDown();

            }).start();
        }


        latch.await();

        System.out.println("end num = "+ato.get());

    }

}
