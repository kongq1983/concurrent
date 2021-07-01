package com.kq.concurrent.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadInputDemo2 {

    public static void main(String[] args) throws Exception{

        AtomicInteger atomicInteger = new AtomicInteger(0);
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        Runnable runnable = () ->{

            while (true){
                boolean interrupted = Thread.interrupted();
                if(interrupted) {
                    atomicInteger.set(0);
                    atomicBoolean.set(true);
//                    atomicInteger.incrementAndGet();
                    System.out.println("============================"+Thread.currentThread().getName()+" "+interrupted);
//                    break;
                }else {
                    atomicInteger.incrementAndGet();
                    System.out.println("----------------------------"+Thread.currentThread().getName()+" "+interrupted);
                    if(atomicInteger.get()>5 && atomicBoolean.get()) break;
                }

            }

        };

        Thread t = new Thread(runnable);
        t.start();

        Thread.sleep(1000);
        t.interrupt();

        TimeUnit.SECONDS.sleep(60);

    }

}
