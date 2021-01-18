package com.kq.concurrent.join;

import java.util.concurrent.TimeUnit;

/**
 * author:kq
 * @since 2020-01-18
 */
public class ThreadJoinDemo5 {


    public static void main(String[] args) throws Exception{
        Runnable r = ()->{
            System.out.println(Thread.currentThread().getName()+" is execute.");

            try {
                TimeUnit.SECONDS.sleep(5);
            }catch (Exception e ){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" is end.");
        };

        Thread t = new Thread(r);
        t.start();


        // 等待t完成
        t.join();
        System.out.println(Thread.currentThread().getName()+" is execute.");
        Thread t1 = new Thread(r);
        t1.start();

        TimeUnit.SECONDS.sleep(5);

    }


}
