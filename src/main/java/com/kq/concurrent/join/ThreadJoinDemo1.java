package com.kq.concurrent.join;

import java.util.concurrent.TimeUnit;

/**
 * Created by qikong on 2019/5/11.
 */
public class ThreadJoinDemo1 {

    public static void main(String[] args) throws Exception{


        System.out.printf(" %s start \n ",Thread.currentThread().getName());


        Runnable r = ()-> {
            System.out.printf(" %s start \n ",Thread.currentThread().getName());

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        };

        Thread t1 = new Thread(r,"thread-1");
        t1.start();
        //等待t1执行完成
        t1.join();


        System.out.printf("%s end \n",Thread.currentThread().getName());






    }

}
