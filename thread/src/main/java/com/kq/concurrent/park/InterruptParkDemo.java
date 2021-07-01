package com.kq.concurrent.park;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * 这个例子需要进一步确认
 *
 * @author kq
 * @date 2021-07-01 14:00
 * @since 2020-0630
 */
public class InterruptParkDemo {


    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger();

        Runnable r = ()->{
            while (true) {
                System.out.println("------------------first----------------------"+atomicInteger.incrementAndGet()+" interrupt="+Thread.currentThread().isInterrupted());
                LockSupport.park();

                // 激活
                Thread.interrupted();
                System.out.println("----------------------------------------激活0");
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        Thread t = new Thread(r,"my-thread-1");
        t.start();

        try {
            TimeUnit.SECONDS.sleep(3);
            t.interrupt();
            TimeUnit.SECONDS.sleep(2);

            System.out.println("----------------------------------------激活");
            // 激活park
//            t.interrupted();

            TimeUnit.SECONDS.sleep(50);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
