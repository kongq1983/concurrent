package com.kq.concurrent.join;

import java.util.concurrent.TimeUnit;

/**
 * Created by qikong on 2019/5/11.
 *
 * thread1执行完成 -> thread2执行  -> thread2执行完成 -> main结束
 *
 */
public class ThreadJoinDemo2 {


    public static void main(String[] args) throws Exception{
        System.out.printf(" %s start \n ",Thread.currentThread().getName());


        Runnable r = ()-> {
            System.out.printf(" %s start run \n ",Thread.currentThread().getName());

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("%s run end \n",Thread.currentThread().getName());

        };

        Thread t1 = new Thread(r,"thread-1");
        t1.start();
        //等待t1执行完成
        t1.join();


        Thread t2 = new Thread(r,"thread-2");
        t2.start();
        //等待t2执行完成
        t2.join();



        System.out.printf("%s end \n",Thread.currentThread().getName());
    }

}
