package com.kq.concurrent.counter;

import java.util.concurrent.TimeUnit;

public class CounterTest {

    public static void main(String[] args) throws Exception{
        final Counter counter = new Counter();

        for(int i=0;i<6;i++) {
            Thread t = new Thread(()->{

                for(int j=0;j<10000;j++) {
                    counter.addIndex();
                }
                System.out.println(Thread.currentThread().getName()+" is end");

            });
            t.start();
        }

        TimeUnit.SECONDS.sleep(8);
        // 具体的i，不确定
        System.out.println(counter.i);

    }

}
