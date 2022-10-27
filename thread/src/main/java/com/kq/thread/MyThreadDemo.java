package com.kq.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author kq
 * @date 2022-10-24 17:08
 * @since 2020-0630
 */
public class MyThreadDemo {

    public static void main(String[] args) throws Exception{

        Runnable r = ()->{
            System.out.println(Thread.currentThread());

            Runnable r2 = ()->{
                System.out.println(Thread.currentThread());
            };

            Thread t2 = new Thread(r2,"my-child-thread-t2");

            t2.start();
        };

        Thread t = new Thread(r,"my-child-thread-t1");

        t.start();

        System.out.println(Thread.currentThread());
        TimeUnit.SECONDS.sleep(3);

//        Thread[main,5,main]
//        Thread[my-child-thread-t1,5,main]
//        Thread[my-child-thread-t2,5,main]
        // 5是它正在运行的thread的优先级

    }

}
