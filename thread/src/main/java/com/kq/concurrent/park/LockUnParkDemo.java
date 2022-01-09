package com.kq.concurrent.park;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author kq
 * @date 2022-01-06 13:49
 * @since 2020-0630
 */
public class LockUnParkDemo {

    public static void main(String[] args) throws Exception{

        // 如果是主线程，则一个都不会执行
//        LockSupport.unpark(Thread.currentThread());

        Runnable runnable = ()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("----------------------"+Thread.currentThread().getName());
            LockSupport.park();

            System.out.println("==========="+Thread.currentThread().getName()+", is execute.");
        };


        Thread t1 = new Thread(runnable,"t1");

        // t1还没启动 ，则一个都不会执行
//        LockSupport.unpark(t1);

        Thread t2 = new Thread(runnable,"t2");
        Thread t3 = new Thread(runnable,"t3");

        t1.start();
        // t1.park()不会阻塞  结论，只有在线程启动后，unpark()先于park()调用，第一个park()才不会阻塞
        LockSupport.unpark(t1);


        t2.start();
        t3.start();


        TimeUnit.MINUTES.sleep(10);


    }

}
