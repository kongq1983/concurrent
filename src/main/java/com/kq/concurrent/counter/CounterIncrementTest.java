package com.kq.concurrent.counter;

import java.util.concurrent.TimeUnit;

/**
 * 本例是线程安全的 也就是最终输出的值时对的
 */
public class CounterIncrementTest {

    public static void main(String[] args) throws Exception{
        final Counter counter = new Counter();

        for(int i=0;i<6;i++) {
            Thread t = new Thread(()->{

                for(int j=0;j<10000;j++) {
                    // 同步 这个打印时正常的  60000
//                    counter.addSyncIndex();
                    // 这个具体时什么值 不知道
                    counter.increment();
                }
                System.out.println(Thread.currentThread().getName()+" is end");

            });
            t.start();
        }

        TimeUnit.SECONDS.sleep(8);
        // 具体的i，不确定
        System.out.println(counter.get());

    }

}
