package com.kq.concurrent.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class ThreadInputDemo3 {

    public static void main(String[] args) throws Exception{

        AtomicInteger atomicInteger = new AtomicInteger(0);
        Object obj = new Object();

        Runnable runnable = () ->{

            while (true){

                try {
                    System.out.println(atomicInteger.get()+",========================");
//                    Thread.sleep(5000); // interrupt后  会抛InterruptedException异常
//                    synchronized (obj) {
//                        obj.wait(5000); // interrupt后  会抛InterruptedException异常
//                    }

                    LockSupport.park(); // 死循环了   不会抛异常

                    if(atomicInteger.incrementAndGet()>10)break;

                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        };

        Thread t = new Thread(runnable);
        t.start();

        Thread.sleep(2000);
        t.interrupt();

        TimeUnit.SECONDS.sleep(60);

    }

}
