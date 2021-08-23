package com.kq.concurrent.threadlocal;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ThreadLocalLeakDemo
 *
 * @author kq
 * @date 2021/8/23 23:43
 * @since 1.0.0
 */
public class ThreadLocalLeakDemo {

    static AtomicLong atomicLong = new AtomicLong();

    public static void main(String[] args) throws Exception{

        for(int i=0;i<5000000;i++) {

            new Thread(() -> {
                ThreadLocal<String> value = new ThreadLocal<String>();
                try {
//                    Thread.sleep(1000L);
                    value.set("milk11111111111111111111111111111111111111111" + atomicLong.incrementAndGet());
                    // null
                    System.out.println("get1=" + value.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "thread-2").start();

        }

        TimeUnit.SECONDS.sleep(100);

    }

}
