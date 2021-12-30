package com.kq.concurrent.park;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author kq
 * @date 2021-11-23 17:06
 * @since 2020-0630
 */
public class ParkDemo1 {

    public static void main(String[] args) throws Exception{



//        Thread thread = new Thread();
//        Runnable r = ()-> {
//            LockSupport.unpark(thread);
//            LockSupport.park();
//            System.out.println("----------------------");
//        };
//
//        Thread thread2 = new Thread(r);
//
//        thread.start();

//        LockSupport.unpark(Thread.currentThread());

//        Thread.sleep(1000);
//
//        Thread.yield();
//
//
//        LockSupport.park();
//        LockSupport.unpark(Thread.currentThread());

        for (int try_count = 1, gclocker_stalled_count = 0; /* return or throw */; try_count += 1) {

            TimeUnit.SECONDS.sleep(1);

            System.out.println(try_count);


        }


    }

}
