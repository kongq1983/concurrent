package com.kq.ratelimter;

import java.util.concurrent.TimeUnit;

/**
 * @author kq
 * @date 2021-04-26 11:23
 * @since 2020-0630
 */
public class CounterTest {

    public static void main(String[] args) throws Exception{
        Counter counter = new Counter();

        Runnable runnable = ()->{

            for(int i=0;i<100000;i++) {
                boolean result = counter.tryAccquire();
                System.out.println(Thread.currentThread().getName()+" result=" + result);
            }
        };


        Thread[] ts = new Thread[20];

        for(int i=0;i<ts.length;i++) {
            ts[i] = new Thread(runnable,"thread-"+i);
            ts[i].start();
        }


        Runnable r1 = ()-> {

            while (true) {
                System.out.println(counter.getStaticMap());

                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                    ;
                }
            }
        };

        new Thread(r1,"thread-stat-map").start();

        TimeUnit.MINUTES.sleep(5);


    }

}
