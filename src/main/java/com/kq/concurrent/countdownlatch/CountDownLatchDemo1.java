package com.kq.concurrent.countdownlatch;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * CountDownLatchDemo1
 *
 * @author kq
 * @date 2019/5/23
 */
public class CountDownLatchDemo1 {

    public static void main(String[] args) throws Exception{

        Map<Thread,Integer> map = new HashMap<>();
        CountDownLatch latch = new CountDownLatch(10);

        for(int i=0;i<10;i++) {
            new Thread(()->{

                int second = new Random().nextInt(6)+1;
                System.out.println(Thread.currentThread().getName()+" sleep time = "+second);
                LockSupport.parkNanos(1000*1000*1000*second);

                Random r = new Random();
                int num = r.nextInt();

                map.put(Thread.currentThread(),num);
                latch.countDown();

            }).start();

        }

        latch.await();
        map.values().stream().forEach(System.out::println);
    }

}
