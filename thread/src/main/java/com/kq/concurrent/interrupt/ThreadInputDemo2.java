package com.kq.concurrent.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadInputDemo2 {

    public static void main(String[] args) throws Exception{

        AtomicInteger atomicInteger = new AtomicInteger(0);

        Runnable runnable = () ->{

            while (true){
                boolean interrupted = Thread.interrupted();
                if(interrupted) {
                    atomicInteger.incrementAndGet();
                    System.out.println("============================"+Thread.currentThread().getName()+" "+interrupted);
                }else {
                    if(atomicInteger.get()>0){
                        atomicInteger.incrementAndGet();
                    }
                    System.out.println("----------------------------"+Thread.currentThread().getName()+" "+interrupted);
                    if(atomicInteger.get()>5) break;
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
