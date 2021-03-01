package com.kq.concurrent.executor;

import com.kq.concurrent.util.DateUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorAwaitDemo {


    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,5,
            5, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10));

    public static void main(String[] args) throws Exception{

        AtomicInteger atomicInteger = new AtomicInteger(1);

        Runnable runnable = ()-> {
            System.out.println(Thread.currentThread().getName()+" is run.");

            try {
                TimeUnit.SECONDS.sleep(atomicInteger.incrementAndGet());
            }catch (Exception e){
                e.printStackTrace();
            }

            System.out.println(DateUtil.getNowTime()+", "+Thread.currentThread().getName()+" is end.");

        };

        System.out.println(DateUtil.getNowTime()+", "+Thread.currentThread().getName()+" is start.===========");

        for(int i=0;i<10;i++) {
            threadPoolExecutor.execute(runnable);
        }

//        threadPoolExecutor.shutdown();

        boolean result = threadPoolExecutor.awaitTermination(5,TimeUnit.SECONDS);

        System.out.println(DateUtil.getNowTime()+" , awaitTermination="+result);

        System.out.println(DateUtil.getNowTime()+", "+Thread.currentThread().getName()+" is end.===========");



    }


}
